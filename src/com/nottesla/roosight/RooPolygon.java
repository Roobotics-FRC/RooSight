package com.nottesla.roosight;


import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

/**
 * Created by tesla on 3/16/16.
 */


public class RooPolygon {
    private MatOfPoint2f polygon;

    public RooPolygon(MatOfPoint2f polygon) {
        this.polygon = polygon;
    }

    public MatOfPoint2f getPolygon() {
        return this.polygon;
    }

    public int getNumPoints() {
        return polygon.toArray().length;
    }

    public RooPoint[] getPoints() {
        MatOfPoint2f points = new MatOfPoint2f(this.polygon);
        RooPoint[] rooPoints = new RooPoint[getNumPoints()];
        Point pointsArr[] = this.polygon.toArray();
        for (int i = 0; i < rooPoints.length; ++i) {
            rooPoints[i] = new RooPoint(pointsArr[i]);
        }
        return rooPoints;
    }

    public boolean isConvex() {
        return Imgproc.isContourConvex(new MatOfPoint(this.polygon.toArray()));
    }

    public double getArea() {
        return Math.abs(Imgproc.contourArea(this.polygon));
    }

    public double getPerimeter() {
        return Math.abs(Imgproc.arcLength(new MatOfPoint2f(this.polygon.toArray()), true));
    }


    @Override
    public void finalize() throws Throwable {
        polygon.release();
        super.finalize();
    }
}
