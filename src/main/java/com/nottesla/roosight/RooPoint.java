package com.nottesla.roosight;


import org.opencv.core.Point;

/**
 * Created by tesla on 3/16/16.
 */
public class RooPoint {
    private Point cvPoint;

    public RooPoint(double x, double y) {
        this.cvPoint = new Point(x, y);
    }

    public RooPoint(Point cvPoint) {
        this.cvPoint = cvPoint.clone();
    }

    public RooPoint(RooPoint rooPoint) {
        this(rooPoint.getX(), rooPoint.getY());
    }

    public double getX() {
        return cvPoint.x;
    }

    public double getY() {
        return cvPoint.y;
    }

    public Point getCvPoint() {
        return cvPoint;
    }

    @Override
    public String toString() {
        return String.format("RooPoint{%s}", cvPoint.toString());
    }
}
