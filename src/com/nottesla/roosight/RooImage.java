package com.nottesla.roosight;


import javafx.scene.image.Image;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by tesla on 3/16/16.
 */
public abstract class RooImage {
    protected Mat image;

    public RooImage(Mat image) {
        Mat copy = new Mat(image.rows(), image.cols(), image.type());
        image.copyTo(copy);
        this.image = copy;
    }


    public byte[] getBytes() {
        byte buffer[] = new byte[(int) this.getImage().elemSize() * (int) this.getImage().total()];
        this.getImage().get(0, 0, buffer);
        return buffer;
    }

    public Image toFXImage() {
        return new Image(new ByteArrayInputStream(this.getBytes()));
    }

    public BufferedImage toBufferedImage() throws IOException {
        return ImageIO.read(new ByteArrayInputStream(this.getBytes()));
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
}
