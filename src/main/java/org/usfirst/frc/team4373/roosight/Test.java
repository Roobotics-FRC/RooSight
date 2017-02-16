package org.usfirst.frc.team4373.roosight;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.awt.*;
import java.io.File;
import java.util.*;

/**
 * Created by tesla on 4/15/16.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        java.util.List list = Arrays.asList(args);
        File file = null;

        if (list.indexOf("-file") > -1) {
            file = new File((String) list.get(list.indexOf("-file") + 1));
        }

        if (file != null) {
            int index = -1;
            if (list.indexOf("-hsv") > -1) {
                index = list.indexOf("-hsv");
                processHSV(file, (String) list.get(index + 1));
            } else if (list.indexOf("-hsl") > -1) {
                index = list.indexOf("-hsl");
                processHSL(file, (String) list.get(index + 1));
            } else if (list.indexOf("-rgb") > -1) {
                index = list.indexOf("-rgb");
                processRGB(file, (String) list.get(index + 1));
            }
        }
    }

    public static void processHSV(File file, String params) {
        RooColorImage colorImage = new RooColorImage(file.getAbsolutePath());
        RooConfig config = new RooConfig();
        String[] paramArray = params.split(",");
        if (paramArray.length == 6) {
            config.setHSV(new Integer(paramArray[0]), new Integer(paramArray[1]), new Integer(paramArray[2]), new Integer(paramArray[3]), new Integer(paramArray[4]), new Integer(paramArray[5]));
            writeConfigured(colorImage, config, file);
        }
    }

    public static void processHSL(File file, String params) {
        RooColorImage colorImage = new RooColorImage(file.getAbsolutePath());
        RooConfig config = new RooConfig();
        String[] paramArray = params.split(",");
        if (paramArray.length == 6) {
            config.setHSL(new Integer(paramArray[0]), new Integer(paramArray[1]), new Integer(paramArray[2]), new Integer(paramArray[3]), new Integer(paramArray[4]), new Integer(paramArray[5]));
            writeConfigured(colorImage, config, file);
        }
    }

    public static void processRGB(File file, String params) {
        RooColorImage colorImage = new RooColorImage(file.getAbsolutePath());
        RooConfig config = new RooConfig();
        String[] paramArray = params.split(",");
        if (paramArray.length == 6) {
            config.setRGB(new Integer(paramArray[0]), new Integer(paramArray[1]), new Integer(paramArray[2]), new Integer(paramArray[3]), new Integer(paramArray[4]), new Integer(paramArray[5]));
            writeConfigured(colorImage, config, file);
        }
    }

    public static void writeConfigured(RooColorImage colorImage, RooConfig config, File file) {
        config.setMinArea(1000);
        config.setMinPerimeter(300);
        RooProcessor rooProcessor = new RooProcessor(config);
        RooBinaryImage thresh = rooProcessor.processImage(colorImage);
        thresh.blur(1);
        RooContour[] contours = rooProcessor.findContours(thresh);
        colorImage.drawContours(contours, new RooColor(Color.RED), 2);
        colorImage.markContours(contours, new RooColor(Color.RED), 1);
        colorImage.writeToFile(file.getAbsolutePath() + ".out.jpg");
    }

    public static void process(File file) {
        if (!file.getName().endsWith(".jpg") || file.getName().endsWith(".jpg.jpg")) {
            return;
        }
        System.out.println(file.getName());
        System.out.printf("Usage: %d/%d (%d%%)", Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), Runtime.getRuntime().totalMemory(), (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * 100 / Runtime.getRuntime().totalMemory());
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
