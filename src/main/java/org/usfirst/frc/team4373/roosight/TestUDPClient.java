package org.usfirst.frc.team4373.roosight;

import org.opencv.core.Mat;
import org.usfirst.frc.team4373.roosight.net.RooUDPClient;

import java.io.IOException;

/**
 * Created by derros on 2/19/17.
 */
public class TestUDPClient {

    public static void main(String args[]) {
        System.load("/usr/local/Cellar/opencv3/3.2.0/share/OpenCV/java/libopencv_java320.dylib");
        System.out.println("Initializing UDP Client.");
        try {
            RooUDPClient client = new RooUDPClient("localhost", 4373);
            System.out.println("client initialized. Now initializing Mat.");
            RooCamera camera = new RooCamera(0);
            RooColorImage colorImage = camera.getFrame(640, 480);
            RooSerializableImage image = new RooSerializableImage(colorImage.getBytes());
            while (true) {
                try {
                    client.send(image);
                } catch (IOException e) {
                    System.out.println("Error error error!");
                    e.printStackTrace();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("UDPClient Initialization failed.");
            e.printStackTrace();
        }
    }
}
