package org.usfirst.frc.team4373.roosight.kryo;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.sun.javaws.exceptions.InvalidArgumentException;
import sun.plugin2.message.Message;

import java.io.IOException;

/**
 * Created by derros on 5/22/18.
 */
public class SampleKryoServer {

    private Server kryoServer;
    private int tcpPort;
    private int udpPort;
    private Listener callbackInvoker;
    private Callback<?> callback;

    /**
     * SampleKryoServer constructor accepts a Kryo server socket object (dependency injection),
     * a TCP port and a UDP port.
     *
     * @param kryoServer The Server instance that contains a socket in Kryo
     * @param tcpPort    The TCP port we're binding to
     * @param udpPort    The UDP port we're binding to
     */
    public SampleKryoServer(Server kryoServer, int tcpPort, int udpPort) {
        this.kryoServer = kryoServer;
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
        this.callbackInvoker = new Listener() {
            public void received(Connection connection, Object object) {
                if (object != null && callback != null) {

                    callback.receive(object);
                }
            }
        };
    }

    /**
     * Starts listening on both the TCP and UDP ports.
     *
     * @throws IOException From Server#bind
     */
    public void startServer() throws IOException {
        kryoServer.start();
        kryoServer.bind(this.tcpPort, this.udpPort);
    }

    public void setListener(Listener listener) {
        this.kryoServer.addListener(listener);
    }

    public void setCallback(Callback<?> callback) {
        this.callback = callback;
    }

    public abstract class Callback {

        public abstract void receive(Object o);

        public abstract void reply(Connection kryoConnection);

    }

}
