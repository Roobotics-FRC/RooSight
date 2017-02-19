package org.usfirst.frc.team4373.roosight.net;

/**
 * Created by derros on 2/18/17.
 */

import org.usfirst.frc.team4373.roosight.RooSerializableImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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

    private byte[] toByte(int number) {
        byte[] data = new byte[4];
        // int -> byte[]
        for (int i = 0; i < 4; ++i) {
            int shift = i << 3; // i * 8
            data[3-i] = (byte)((number & (0xff << shift)) >>> shift);
        }
        return data;
    }

    private byte[][] fragment(byte[] data) {
        int fb = data.length / 16;
        byte[][] b = new byte[16][fb];
        int counter = 0;
        for(byte[] by : b) {
            for(int i = counter; i < counter + fb; i ++) {
                by[i] = data[i];
            }
        }
        return b;
    }

    public void send(RooSerializableImage image) throws IOException {
        objectOut.writeObject(image);
        objectOut.flush();
        byte[] data = byteOut.toByteArray();
        // now we send length first
        int size = data.length;
        byte[] byteSize = toByte(size);
        DatagramPacket sizePacket = new DatagramPacket(byteSize, 4, host, port);
        udpSocket.send(sizePacket);
        DatagramPacket fragPacket = new DatagramPacket(toByte(16), 4, host, port);
        udpSocket.send(fragPacket);
        // now send the REAL CASE
        byte[][] b = fragment(data);
        int i = 0;
        for(byte[] by : b) {
            System.out.println("\tSend packet" + i + "\t" + by.length);
            DatagramPacket sendPacket = new DatagramPacket(by, by.length, host, port);
            udpSocket.send(sendPacket);
        }
        System.out.println("}}all packets sent.");
    }
}