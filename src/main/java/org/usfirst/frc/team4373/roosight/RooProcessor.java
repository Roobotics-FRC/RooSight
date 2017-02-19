package org.usfirst.frc.team4373.roosight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tesla on 3/17/16.
 */
public class RooProcessor {
    private RooConfig config;

    public RooProcessor(RooConfig config) {
        this.config = config;
    }

    public RooConfig getConfig() {
        return config;
    }

    public void setConfig(RooConfig config) {
        this.config = config;
    }

    public RooBinaryImage processImage(RooColorImage rooImage) {
        RooBinaryImage hsl = rooImage.hslThreshold(
                config.getHslHMin(),
                config.getHslHmax(),
                config.getHslSMin(),
                config.getHslSMax(),
                config.getHslLmin(),
                config.getHslLmax()
        );
        RooBinaryImage hsv = rooImage.hsvThreshold(
                config.getHsvHMin(),
                config.getHsvHmax(),
                config.getHsvSMin(),
                config.getHsvSMax(),
                config.getHsvVMin(),
                config.getHsvVmax()
        );
        RooBinaryImage rgb = rooImage.rgbThreshold(
                config.getrMin(),
                config.getrMax(),
                config.getgMin(),
                config.getgMax(),
                config.getbMin(),
                config.getbMax()
        );
        RooBinaryImage hslHsv = hsl.bitwiseAnd(hsv);
        RooBinaryImage hslHsvRgb = hslHsv.bitwiseAnd(rgb);
        hslHsvRgb.blur(config.getBlur());
        return hslHsvRgb;
    }

    public RooContour[] findContours(RooBinaryImage rooImage) {
        RooContour[] contours = rooImage.findContours();
        if (contours == null) {
            return null;
        }
        List<RooContour> matchedContours = new ArrayList<>(Arrays.asList(contours));
        Iterator<RooContour> contourIterator = matchedContours.iterator();
        while (contourIterator.hasNext()) {
            RooContour contour = contourIterator.next();
            int width = contour.getWidth();
            if (width < config.getMinWidth() || width > config.getMaxWidth()) {
                contourIterator.remove();
                continue;
            }
            double height = contour.getHeight();
            if (height < config.getMinHeight() || height > config.getMaxHeight()) {
                contourIterator.remove();
                continue;
            }
            double perimeter = contour.getPerimeter();
            if (perimeter < config.getMinPerimeter() || perimeter > config.getMaxPerimeter()) {
                contourIterator.remove();
                continue;
            }
            double area = contour.getArea();
            if (area < config.getMinArea() || area > config.getMaxArea()) {
                contourIterator.remove();
                continue;
            }
            int x = contour.getX();
            if (x < config.getMinX() || x > config.getMaxX()) {
                contourIterator.remove();
                continue;
            }
            int y = contour.getY();
            if (y < config.getMinY() || y > config.getMaxY()) {
                contourIterator.remove();
                continue;
            }
        }
        RooContour[] ret = new RooContour[matchedContours.size()];
        matchedContours.toArray(ret);
        System.gc();
        return ret;
    }
}
