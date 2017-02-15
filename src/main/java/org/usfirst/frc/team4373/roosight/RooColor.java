package org.usfirst.frc.team4373.roosight;

import org.opencv.core.Scalar;

import java.awt.*;

/**
 * Created by tesla on 3/16/16.
 */
public class RooColor {
    private Scalar scalar;
    private Color color = null;

    private RooColor(Scalar scalar, Color color) {
        this(scalar);
        this.color = color;
    }

    private RooColor(Scalar scalar) {
        this.scalar = scalar;
    }

    public RooColor(int r, int g, int b) {
        this(new Scalar(b, g, r, 0));
    }

    public RooColor(Color color) {
        this(color.getRed(), color.getGreen(), color.getBlue());
    }

    public Color toColor() {
        if (color != null) {
            color = new Color((int) scalar.val[0], (int) scalar.val[1], (int) scalar.val[2]);
        }
        return color;
    }

    public Scalar getScalar() {
        return scalar;
    }
}
