package com.nottesla.roosight;

import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

/**
 * Created by tesla on 3/16/16.
 */
public class RooContour {
    private MatOfPoint contour;
    private Rect rect;

    public RooContour(MatOfPoint contour) {
        this.contour = contour;
        this.rect = Imgproc.boundingRect(contour);
    }

    public int getHeight() {
        return rect.height;
    }

    public int getWidth() {
        return rect.width;
    }

    public int getX() {
        return rect.x;
    }

    public int getY() {
        return rect.y;
    }

    public double getPerimeter() {
        return Math.abs(Imgproc.arcLength(new MatOfPoint2f(this.contour.toArray()), true));
    }

    public double getArea() {
        return Math.abs(Imgproc.contourArea(this.contour));
    }

    public MatOfPoint getContour() {
        return this.contour;
    }

    public synchronized RooPolygon approximatePoly(int accuracy) {
        MatOfPoint2f polygon = new MatOfPoint2f();
        Imgproc.approxPolyDP(new MatOfPoint2f(this.contour.toArray()), polygon, accuracy, true);
        return new RooPolygon(polygon);
    }

    @Override
    protected void finalize() throws Throwable {
        contour.release();
        super.finalize();
    }
}
