package org.usfirst.frc.team4373.roosight;

import org.opencv.core.Mat;

/**
 * Created by derros on 2/18/17.
 */
public class RooMultiCam {

    public RooCamera[] r;

    public RooMultiCam(int[] cameraNumbers) {
        r = new RooCamera[cameraNumbers.length];
        for(int i = 0; i < cameraNumbers.length; i++) {
            r[i] = new RooCamera(cameraNumbers[i]);
        }
    }

    public RooColorImage getFrame(int i) {
        return r[i].getFrame();
    }

    public boolean getState(int i) {
        return r[i].isCamOpen();
    }

    public void close() {
        for(RooCamera roo : r) {
            roo.close();
        }
    }

    public void finalize() {
        close();
    }
}
