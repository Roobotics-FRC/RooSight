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
        RooPoint[] rooPoints = new RooPoint[getNumPoints()];
        Point pointsArr[] = this.polygon.toArray();
        for (int i = 0; i < rooPoints.length; ++i) {
            rooPoints[i] = new RooPoint(pointsArr[i]);
        }
        return rooPoints;
    }

    public boolean isConvex() {
        MatOfPoint matOfPoint = new MatOfPoint(this.polygon.toArray());
        boolean ret = Imgproc.isContourConvex(matOfPoint);
        matOfPoint.release();
        return ret;
    }

    public double getArea() {
        return Math.abs(Imgproc.contourArea(this.polygon));
    }

    public double getPerimeter() {
        MatOfPoint2f matOfPoint2f = new MatOfPoint2f(this.polygon.toArray());
        double ret = Math.abs(Imgproc.arcLength(matOfPoint2f, true));
        matOfPoint2f.release();
        return ret;
    }


    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.gc();
    }
}
