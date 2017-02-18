package org.usfirst.frc.team4373.roosight;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 * Created by derros on 2/18/17.
 */
public class RooCamera {

    /**
     * RooCamera
     * Interfaces the actual camera through OpenCV VideoCapture and etc.
     * @author derros
     */

    private int cam;
    private VideoCapture vc;

    /**
     * RooCamera()
     * @param no Camera number (0, 1, 2, 3, etc.)
     */
    public RooCamera(int no) {
        cam = no;
        vc = new VideoCapture(no);
    }


    /**
     * getFrame()
     * returns a new frame from camera
     * @return Mat newFrame
     * @throws NullPointerException
     */
    public Mat getFrame() throws NullPointerException {
        if(vc.isOpened()) {
            Mat currFrame = new Mat();
            vc.read(currFrame);

            return currFrame;
        } else {
            throw new NullPointerException();
        }
    }


    /**
     * isCamOpen()
     * returns camera status
     * @return
     */
    public boolean isCamOpen() {
        return vc.isOpened();
    }



    /**
     * close()
     * call if cam is not needed anymore
     */
    public void close() {
        // do house-keeping work
        vc.release();
    }

    /**
     * finalize()
     * fool-proof method in case close() is not called
     */
    public void finalize() {
        // if close is not called
        close();
    }

}
