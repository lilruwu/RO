import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class tcp3ser {

    public static void main(String[] args) throws SocketException, IOException {
        //ATRIBUTES
        if (args.length < 1) {
            System.out.println("Wrong syntax! The correct syntax is: tpc1ser PORT");
            System.exit(0);
        } else if (args.length > 1) {
            System.out.println("So many arguments! The correct syntax is: tpc1ser PORT");
            System.exit(0);
        } else {
            int port = Integer.valueOf(args[0]);

            int counterudp = 0;
            int counter = 0;

            //START
            System.out.println("Initializing server...");

            //NETWORK (RECEIVE PART)
            //udpser(port);
            //ServerSocket socketTCP = new ServerSocket(Integer.valueOf(port));
            ServerSocketChannel tcpserver = ServerSocketChannel.open();
            //serversocket.configureBlocking(false);
            tcpserver.socket().bind(new InetSocketAddress(port));
            DatagramChannel udpserver = DatagramChannel.open();
            udpserver.socket().bind(new InetSocketAddress(port));

            tcpserver.configureBlocking(false);
            udpserver.configureBlocking(false);

            Selector selector = Selector.open();

            tcpserver.register(selector, SelectionKey.OP_ACCEPT);
            udpserver.register(selector, SelectionKey.OP_READ);

            ByteBuffer buf = ByteBuffer.allocate(1024);
            //buf.clear();

            while (true) {

                try {
                    selector.select();

                    Set keys = selector.selectedKeys();

                    for (Iterator i = keys.iterator(); i.hasNext();) {

                        SelectionKey key = (SelectionKey) i.next();
                        i.remove();

                        Channel c = (Channel) key.channel();
			 
                        //TCP
                        if (key.isAcceptable() && c == tcpserver) {
                            System.out.println("TCP REGISTERED");
                             
                            counter=0;
                            
                            SocketChannel client = tcpserver.accept();
                            client.configureBlocking(false);
                           //while (true) { 
                            key = client.register(key.selector(), SelectionKey.OP_READ);
                            key.attach(counter);
                            
                       
                       
                        	    //}
                                    
                            //UDP
                         
                        
                        
                        }else if (key.isReadable() && c == udpserver) {
                           
                            System.out.println("UDP STARTED");
                            ByteBuffer buf2 = ByteBuffer.allocate(1024);
                            buf2.clear();
                            buf.clear();
                            //NETWORK (RECEIVE PART)
                           
                            //DatagramSocket socketUDP = new DatagramSocket(Integer.valueOf(port));
                           
                                //buf.clear();
                                SocketAddress socketUDP = udpserver.receive(buf);
                               
                                //DatagramPacket packet = new DatagramPacket(buf1, buf1.length);

                                //socketUDP.receive(packet);
                                //int clientport = packet.getPort();
                                //InetAddress address = packet.getAddress();

                                //String m = new String(packet.getData());
                               
                                int a = buf.getInt(0);
                                buf.clear();
                                //System.out.println("ESTO ES A "+ a);
                                counterudp = a + counterudp;
                                System.out.println(counterudp);
                                String counterudpstring = Integer.toString(counterudp);
                               
                               
                                buf2.put(counterudpstring.getBytes());
                                buf2.flip();
                                //DatagramPacket result = new DatagramPacket(buf2, buf2.length, address, clientport);
                                //socketUDP.send(result);
                                    udpserver.send(buf2, socketUDP);
                                    buf2.clear();
                                    buf.clear();
                                    System.out.println("Package sent!");
                               
                               

                           
                        } else if (key.isReadable()){
                        
                        	    counter= ((Integer)key.attachment()).intValue();
                        	    SocketChannel client = (SocketChannel) key.channel();

				     buf.clear();
				     while(client.read(buf) > 0) {
                        	     
                                    //String a = new String(buf.array());
                                    //buf.clear();
				     int a = buf.getInt(0);
                                    //System.out.println(a);
                                  
                                    counter = counter + a; 
                                    key.attach(counter);
                                    System.out.println("Accumulator: "+ counter);
                                    
                                    String counterS = Integer.toString(counter);
                                   
                                    //SEND
                                    buf.clear();
                                    buf.flip();
                                    buf.clear();
                                    buf.put(counterS.getBytes());
                                    
                                    buf.flip();
                                    
                                    client.write(buf);
                                    buf.position(0);
                                    }
                                    
                                    } 
                        	     
                     }
                } catch (IOException e) {
                    System.out.println("Client Disconnected");
                    System.exit(0);
                }
               
               
               
            }
        }
    }
}
