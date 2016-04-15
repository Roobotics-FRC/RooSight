package com.nottesla.roosight;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tesla on 3/16/16.
 */
public class RooBinaryImage extends RooImage {

    public RooBinaryImage(Mat mat) {
        super(mat);
        if (mat.channels() != 1) {
            throw new IllegalArgumentException("nChannels != 1");
        }
    }

    public RooBinaryImage bitwiseOr(RooBinaryImage otherImage) {
        Mat out = new Mat(otherImage.getImage().rows(), otherImage.getImage().cols(), otherImage.getImage().type());
        Core.bitwise_or(this.getImage(), otherImage.getImage(), out);
        return new RooBinaryImage(out);
    }

    public RooBinaryImage bitwiseAnd(RooBinaryImage otherImage) {
        Mat out = new Mat(otherImage.getImage().rows(), otherImage.getImage().cols(), otherImage.getImage().type());
        Core.bitwise_or(this.getImage(), otherImage.getImage(), out);
        return new RooBinaryImage(out);
    }

    public RooBinaryImage bitwiseNot() {
        Mat out = new Mat(getImage().rows(), getImage().cols(), getImage().type());
        Core.bitwise_not(getImage(), out);
        return new RooBinaryImage(out);
    }

    public RooBinaryImage bitwiseXor(RooBinaryImage otherImage) {
        Mat out = new Mat(otherImage.getImage().rows(), otherImage.getImage().cols(), otherImage.getImage().type());
        Core.bitwise_xor(this.getImage(), otherImage.getImage(), out);
        return new RooBinaryImage(out);
    }

    public RooContour[] findContours() {
        Mat temp = new Mat(getImage().rows(), getImage().cols(), getImage().type());
        List<MatOfPoint> contours = new ArrayList<>(5);
        Imgproc.findContours(temp, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_TC89_KCOS);
        ArrayList<RooContour> results = new ArrayList<>(contours.size());
        contours.forEach(matOfPoint -> {
            synchronized (results) {
                results.add(new RooContour(matOfPoint));
            }
        });
        RooContour[] array = new RooContour[results.size()];
        return results.toArray(array);
    }
}
