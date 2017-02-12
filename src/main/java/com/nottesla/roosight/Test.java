package com.nottesla.roosight;

import org.opencv.core.Core;

import java.awt.*;
import java.io.File;

/**
 * Created by tesla on 4/15/16.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary("opencv_imgproc.3.2.0");
        System.loadLibrary("opencv_imgcodecs.3.2");
        System.loadLibrary("opencv_videoio.3.2");
        System.loadLibrary("opencv_highgui.3.2.0");
//        process(new File("/tmp/images/0.jpg"));
        File files[] = new File("/tmp/images").listFiles();
        for (int i = 0; i < files.length; ++i) {
            process(files[i]);
        }
    }

    public static void process(File file) {
        if (!file.getName().endsWith(".jpg") || file.getName().endsWith(".jpg.jpg")) {
            return;
        }
        System.out.println(file.getName());
        System.out.printf("Usage: %d/%d (%d%%)\n", Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), Runtime.getRuntime().totalMemory(), (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * 100 / Runtime.getRuntime().totalMemory());
        RooColorImage colorImage = new RooColorImage(file.getAbsolutePath());
        RooConfig config = new RooConfig();
        config.setMinArea(400);
        config.setMinPerimeter(300);
        config.setRGB(0, 220, 55, 255, 25, 240);
        config.setHSL(37, 100, 30, 255, 150, 255);
        config.setHSV(37, 100, 30, 255, 50, 255);
        RooProcessor rooProcessor = new RooProcessor(config);
        RooBinaryImage thresh = rooProcessor.processImage(colorImage);
        thresh.blur(1);
        RooContour[] contours = rooProcessor.findContours(thresh);
        colorImage.drawContours(contours, new RooColor(Color.RED), 2);
        colorImage.markContours(contours, new RooColor(Color.RED), 1);
        colorImage.writeToFile(file.getAbsolutePath() + ".jpg");
    }
}
