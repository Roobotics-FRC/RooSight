package org.usfirst.frc.team4373.roosight.net;

/**
 * Created by derros on 2/18/17.
 */
import org.usfirst.frc.team4373.roosight.RooSerializableImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class RooUDPClient {
    private InetAddress host;
    private int port;
    private DatagramSocket udpSocket;
    private ByteArrayOutputStream byteOut;
    private ObjectOutputStream objectOut;

    public RooUDPClient(String host, int port) throws IOException {
        this.host = InetAddress.getByName(host);
        this.port = port;
        udpSocket = new DatagramSocket();
        byteOut = new ByteArrayOutputStream();
        objectOut = new ObjectOutputStream(byteOut);
    }

    public void send(RooSerializableImage image) {
        try {
            objectOut.writeObject(image);
            byte[] data = byteOut.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, host, port);
            udpSocket.send(sendPacket);
            System.out.println("Message sent from client");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}