//package udpser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class udpser {

    public static void main(String[] args) throws SocketException, IOException {
        //ATRIBUTES
        if (args.length < 1) {
            System.out.println("Not enough arguments!");
            System.exit(0);
        } else if (args.length > 1) {
            System.out.println("So many arguments!");
            System.exit(0);
        } else {
        String port = args[0];
        

        int supercounter = 0;

        //START
        System.out.println("Initializing server...");

        //NETWORK (RECEIVE PART)
            DatagramSocket socketUDP = new DatagramSocket(Integer.valueOf(port));
            while (true) {
	    byte[] buf1 = new byte[2048];
            DatagramPacket packet = new DatagramPacket(buf1, buf1.length);

            socketUDP.receive(packet);
            int clientport = packet.getPort();
            InetAddress address = packet.getAddress();
            int counter = 0;
            int b = 0;
            //System.out.println(b);

            while (buf1[b] != 0) {
                b++;

            }
            byte[] buf2 = new byte[b];

            b--;

            buf2[0] = buf1[0];
            for (b = b; b != 0; b--) {

                buf2[b] = buf1[b];

                //CALCULUS OF THE COUNTER    
            }

            for (int i = 0; i < buf2.length; i++) {

                counter = buf2[i] + counter;
                System.out.println(counter);

            }
            supercounter = counter + supercounter;
            if (supercounter >= 128) {
            	buf1[0]=Byte.valueOf(String.valueOf(-1));
            	byte[] buf = new byte[1];
            	buf[0] = buf1[0];
            	DatagramPacket result = new DatagramPacket(buf, buf.length, address, clientport);
            	socketUDP.send(result);
            	System.out.println("Counter is higher than 127, server can't manage that, we have sent a warning, server will be disconnected, it must be restart.");
            	break;
            }
	    System.out.println("Counter value is: "+supercounter);
            //NETWORK (SEND PART)
            buf1[0] = Byte.valueOf(String.valueOf(supercounter));

            byte[] buf = new byte[1];
            buf[0] = buf1[0];

            DatagramPacket result = new DatagramPacket(buf, buf.length, address, clientport);

            socketUDP.send(result);
            System.out.println("Package sent!");

        }
    }
   }
}


