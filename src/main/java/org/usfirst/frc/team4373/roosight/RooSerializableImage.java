package org.usfirst.frc.team4373.roosight;

import java.io.Serializable;

/**
 * Created by derros on 2/18/17.
 */
public class RooSerializableImage implements Serializable {

    private byte[] imageData;

    public RooSerializableImage(byte[] data) {
        imageData = data;
    }

    public byte[] getImageData() {
        return imageData;
    }
}
