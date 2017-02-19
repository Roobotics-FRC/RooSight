package org.usfirst.frc.team4373.roosight;


import javafx.scene.image.Image;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by tesla on 3/16/16.
 */

public abstract class RooImage {

    protected Mat image;

    public RooImage(Mat image) {
        this.image = image;
    }


    public byte[] getBytes() {
        byte buffer[] = new byte[(int) this.getImage().elemSize() * (int) this.getImage().total()];
        this.getImage().get(0, 0, buffer);
        return buffer;
    }

    public Mat toMat(byte[] bytesImage) {
        Mat mat = new Mat();
        mat.put(0, 0, bytesImage);
        return mat;
    }

    public Mat resizeMat(int width, int height, Mat src) {
        Mat dst = new Mat();
        Imgproc.resize(src, dst, new Size(width, height));
        return dst;
    }

    public RooImage resize(int width, int height) {
        this.image = resizeMat(width, height, this.image);
        return this;
    }

    public Image toFXImage() {
        return new Image(new ByteArrayInputStream(this.getBytes()));
    }

    public BufferedImage toBufferedImage() throws IOException {
        return ImageIO.read(new ByteArrayInputStream(this.getBytes()));
    }

    public RooSerializableImage toSerializableImage() {
        return new RooSerializableImage(this.getBytes());
    }

    public RooImage(byte[] bytes) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        this.image = new Mat(bufferedImage.getWidth(), bufferedImage.getWidth(), CvType.CV_8UC3);
    }

    public Mat getImage() {
        return this.image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }

    public void writeToFile(String filename) {
        Imgcodecs.imwrite(filename, this.getImage());
    }

    public void blur(int amount) {
        if (amount < 0 || amount > 100) {
            throw new IllegalArgumentException("amount must be a percentage from 0-100");
        }
        int width = getImage().width() / 100 * amount;
        int height = getImage().width() / 100 * amount;
        Imgproc.blur(getImage(), getImage(), new Size(width, height));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
