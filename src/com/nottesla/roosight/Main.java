package com.nottesla.roosight;

import org.opencv.core.Core;

/**
 * Created by tesla on 4/15/16.
 */
public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        RooColorImage colorImage = new RooColorImage("/Users/tesla/Downloads/images/0.jpg");
        RooConfig config = new RooConfig();
        config.setMinArea(400);
        config.setMinPerimeter(300);
        config.setRGB(0, 250, 100, 255, 20, 255);
        RooProcessor rooProcessor = new RooProcessor(config);
        RooBinaryImage thresh = rooProcessor.processImage(colorImage);
        RooContour[] contours = rooProcessor.findContours(thresh);
        System.out.println(contours.length);
        colorImage.drawContours(contours, new RooColor(0, 0, 255), 2);
        colorImage.writeToFile("/tmp/processed.jpg");
    }
}
