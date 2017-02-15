package org.usfirst.frc.team4373.roosight;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tesla on 3/16/16.
 */
public class RooColorImage extends RooImage {
    public RooColorImage(byte[] bytes) throws IOException {
        super(bytes);
    }

    public RooColorImage(String filePath) {
        this(Imgcodecs.imread(filePath));
    }

    public RooColorImage(Mat image) {
        super(image);
        if (image.channels() < 3) {
            throw new IllegalArgumentException("nChannels < 3");
        }
    }

    public RooBinaryImage hslThreshold(int hMin, int hMax, int lMin, int lMax, int sMin, int sMax) {
        Mat hls = new Mat(this.getImage().rows(), this.getImage().cols(), CvType.CV_8UC3);
        Imgproc.cvtColor(this.getImage(), hls, Imgproc.COLOR_BGR2HLS);
        Mat threshold = new Mat(this.getImage().rows(), this.getImage().cols(), CvType.CV_8UC1);
        Core.inRange(hls, new Scalar(hMin, lMin, sMin), new Scalar(hMax, lMax, sMax), threshold);
        hls.release();
        return new RooBinaryImage(threshold);
    }

    public RooBinaryImage hsvThreshold(int hMin, int hMax, int sMin, int sMax, int vMin, int vMax) {
        Mat hsv = new Mat(this.getImage().rows(), this.getImage().cols(), CvType.CV_8UC3);
        Imgproc.cvtColor(this.getImage(), hsv, Imgproc.COLOR_BGR2HSV);
        Mat threshold = new Mat(this.getImage().rows(), this.getImage().cols(), CvType.CV_8UC1);
        Core.inRange(hsv, new Scalar(hMin, sMin, vMin), new Scalar(hMax, sMax, vMax), threshold);
        hsv.release();
        return new RooBinaryImage(threshold);
    }

    public RooBinaryImage rgbThreshold(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax) {
        Mat threshold = new Mat(this.getImage().rows(), this.getImage().cols(), CvType.CV_8UC1);
        Core.inRange(this.getImage(), new Scalar(bMin, gMin, rMin), new Scalar(bMax, gMax, rMax), threshold);
        return new RooBinaryImage(threshold);
    }

    public void drawContour(RooContour contour, RooColor color, int thickness) {
        ArrayList<MatOfPoint> contourList = new ArrayList<>(1);
        contourList.add(contour.getContour());
        Imgproc.drawContours(this.getImage(), contourList, 0, color.getScalar(), thickness);
    }

    public void drawContours(RooContour[] contours, RooColor color, int thickness) {
        for (int i = 0; i < contours.length; ++i) {
            drawContour(contours[i], color, thickness);
        }
    }

    public void drawMarker(RooPoint point, RooColor color, int size, int thickness, int type) {
        Imgproc.drawMarker(this.getImage(), point.getCvPoint(), color.getScalar(), type, size, thickness, Imgproc.LINE_4);
    }

    public void markContour(RooContour contour, RooColor color, int thickness) {
        int size = (contour.getWidth() + contour.getHeight()) / 2;
        drawMarker(contour.getCenter(), color, size, thickness, Imgproc.MARKER_CROSS);
    }

    public void markContours(RooContour[] contours, RooColor color, int thickness) {
        for (int i = 0; i < contours.length; ++i) {
            markContour(contours[i], color, thickness);
        }
    }

    public void drawRect(RooPoint point1, RooPoint point2, RooColor color, int thickness) {
        Imgproc.rectangle(this.getImage(), point1.getCvPoint(), point2.getCvPoint(), color.getScalar(), thickness);
    }

    public void drawCircle(RooPoint center, int radius, RooColor color, int thickness) {
        Imgproc.circle(this.getImage(), center.getCvPoint(), radius, color.getScalar(), thickness);
    }

    public void drawLine(RooPoint point1, RooPoint point2, RooColor color, int thickness) {
        Imgproc.line(this.getImage(), point1.getCvPoint(), point2.getCvPoint(), color.getScalar(), thickness);
    }

    public void drawPolygon(RooPolygon polygon, RooColor color, int thickness) {
        MatOfPoint matOfPoint = new MatOfPoint(polygon.getPolygon().toArray());
        RooContour contour = new RooContour(matOfPoint);
        drawContour(contour, color, thickness);
        matOfPoint.release();
    }

    public void drawPoint(RooPoint point, RooColor color, int thickness) {
        Imgproc.circle(this.getImage(), point.getCvPoint(), thickness, color.getScalar());
    }

    public void drawPoints(RooPoint[] points, RooColor color, int thickness) {
        for (int i = 0; i < points.length; i++) {
            drawPoint(points[i], color, thickness);
        }
    }

    public void drawPolygons(RooPolygon[] polygons, RooColor color, int thickness) {
        for (int i = 0; i < polygons.length; ++i) {
            if (polygons[i] != null) {
                drawPolygon(polygons[i], color, thickness);
            }
        }
    }

    public void drawRect(int x, int y, int width, int height, RooColor color, int thickness) {
        Imgproc.rectangle(this.getImage(), new Point(x, y), new Point(x + width, y + height), color.getScalar(), thickness);
    }
}
