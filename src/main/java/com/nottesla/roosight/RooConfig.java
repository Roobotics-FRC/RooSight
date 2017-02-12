package com.nottesla.roosight;

import java.io.*;

/**
 * Created by tesla on 3/17/16.
 */
public class RooConfig implements Serializable {
    private static final String DEFAULT_FILE = "config.vis";

    public static RooConfig load(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        RooConfig ret = (RooConfig) objectInputStream.readObject();
        objectInputStream.close();
        return ret;
    }

    public static RooConfig load(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        RooConfig ret = load(fileInputStream);
        fileInputStream.close();
        return ret;
    }

    public static RooConfig load(String file) throws IOException, ClassNotFoundException {
        return load(new File(file));
    }

    public static RooConfig load() throws ClassNotFoundException {
        try {
            return load(DEFAULT_FILE);
        } catch (IOException ignored) {
            return new RooConfig();
        }
    }

    private int hsvHMin, hsvSMin, hsvVMin;
    private int hsvHmax, hsvSMax, hsvVmax;
    private int hslHMin, hslSMin, hslLmin;
    private int hslHmax, hslSMax, hslLmax;
    private int rMin, gMin, bMin;
    private int rMax, gMax, bMax;
    private int minWidth, minHeight, minPerimeter, minArea;
    private int maxWidth, maxHeight, maxPerimeter, maxArea;
    private int minX, maxX, minY, maxY;

    public RooConfig() {
        this.hsvHMin = 0;
        this.hsvHmax = 180;
        this.hsvSMin = 0;
        this.hsvSMax = 255;
        this.hsvVMin = 0;
        this.hsvVmax = 255;
        this.hslHMin = 0;
        this.hslHmax = 180;
        this.hslSMin = 0;
        this.hslSMax = 255;
        this.hslLmin = 0;
        this.hslLmax = 255;
        this.rMin = 0;
        this.rMax = 255;
        this.gMin = 0;
        this.gMax = 255;
        this.bMin = 0;
        this.bMax = 255;
        this.minWidth = -1;
        this.maxWidth = Integer.MAX_VALUE;
        this.minHeight = -1;
        this.maxHeight = Integer.MAX_VALUE;
        this.minPerimeter = -1;
        this.maxPerimeter = Integer.MAX_VALUE;
        this.minArea = -1;
        this.maxArea = Integer.MAX_VALUE;
        this.minX = Integer.MIN_VALUE;
        this.maxX = Integer.MAX_VALUE;
        this.minY = Integer.MIN_VALUE;
        this.maxY = Integer.MAX_VALUE;
    }

    public void setHSV(int hMin, int hMax, int sMin, int sMax, int vMin, int vMax) {
        setHsvHMin(hMin);
        setHsvHmax(hMax);
        setHsvSMin(sMin);
        setHsvSMax(sMax);
        setHsvVMin(vMin);
        setHsvVmax(vMax);
    }

    public void setHSL(int hMin, int hMax, int sMin, int sMax, int lMin, int lMax) {
        setHslHMin(hMin);
        setHslHmax(hMax);
        setHslSMin(sMin);
        setHslSMax(sMax);
        setHslLmin(lMin);
        setHslLmax(lMax);
    }

    public void setRGB(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax) {
        setrMin(rMin);
        setrMax(rMax);
        setgMin(gMin);
        setgMax(gMax);
        setbMin(bMin);
        setgMax(bMax);
    }

    public int getHsvHMin() {
        return hsvHMin;
    }

    public void setHsvHMin(int hsvHMin) {
        this.hsvHMin = hsvHMin;
    }

    public int getHsvSMin() {
        return hsvSMin;
    }

    public void setHsvSMin(int hsvSMin) {
        this.hsvSMin = hsvSMin;
    }

    public int getHsvVMin() {
        return hsvVMin;
    }

    public void setHsvVMin(int hsvVMin) {
        this.hsvVMin = hsvVMin;
    }

    public int getHsvHmax() {
        return hsvHmax;
    }

    public void setHsvHmax(int hsvHmax) {
        this.hsvHmax = hsvHmax;
    }

    public int getHsvSMax() {
        return hsvSMax;
    }

    public void setHsvSMax(int hsvSMax) {
        this.hsvSMax = hsvSMax;
    }

    public int getHsvVmax() {
        return hsvVmax;
    }

    public void setHsvVmax(int hsvVmax) {
        this.hsvVmax = hsvVmax;
    }

    public int getHslHMin() {
        return hslHMin;
    }

    public void setHslHMin(int hslHMin) {
        this.hslHMin = hslHMin;
    }

    public int getHslSMin() {
        return hslSMin;
    }

    public void setHslSMin(int hslSMin) {
        this.hslSMin = hslSMin;
    }

    public int getHslLmin() {
        return hslLmin;
    }

    public void setHslLmin(int hslLmin) {
        this.hslLmin = hslLmin;
    }

    public int getHslHmax() {
        return hslHmax;
    }

    public void setHslHmax(int hslHmax) {
        this.hslHmax = hslHmax;
    }

    public int getHslSMax() {
        return hslSMax;
    }

    public void setHslSMax(int hslSMax) {
        this.hslSMax = hslSMax;
    }

    public int getHslLmax() {
        return hslLmax;
    }

    public void setHslLmax(int hslLmax) {
        this.hslLmax = hslLmax;
    }

    public int getrMin() {
        return rMin;
    }

    public void setrMin(int rMin) {
        this.rMin = rMin;
    }

    public int getgMin() {
        return gMin;
    }

    public void setgMin(int gMin) {
        this.gMin = gMin;
    }

    public int getbMin() {
        return bMin;
    }

    public void setbMin(int bMin) {
        this.bMin = bMin;
    }

    public int getrMax() {
        return rMax;
    }

    public void setrMax(int rMax) {
        this.rMax = rMax;
    }

    public int getgMax() {
        return gMax;
    }

    public void setgMax(int gMax) {
        this.gMax = gMax;
    }

    public int getbMax() {
        return bMax;
    }

    public void setbMax(int bMax) {
        this.bMax = bMax;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMinPerimeter() {
        return minPerimeter;
    }

    public void setMinPerimeter(int minPerimeter) {
        this.minPerimeter = minPerimeter;
    }

    public int getMinArea() {
        return minArea;
    }

    public void setMinArea(int minArea) {
        this.minArea = minArea;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxPerimeter() {
        return maxPerimeter;
    }

    public void setMaxPerimeter(int maxPerimeter) {
        this.maxPerimeter = maxPerimeter;
    }

    public int getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(int maxArea) {
        this.maxArea = maxArea;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }

    public void save(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        save(fileOutputStream);
        fileOutputStream.close();
    }

    public void save(String file) throws IOException {
        save(new File(file));
    }

    public void save() throws IOException {
        save(DEFAULT_FILE);
    }
}
