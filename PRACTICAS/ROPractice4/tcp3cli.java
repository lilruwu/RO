import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.net.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Scanner;

class tcp3cli {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {

        //ARGUMENTS CHECK
        if (args.length < 2) {
            System.out.println("Not enough arguments! The correct syntax is: tpc1cli IPADDRESS PORT -u(optional)");
            System.exit(0);
        } else if (args.length > 3) {
            System.out.println("So many arguments! The correct syntax is: tpc1cli IPADDRESS PORT -u(optional)");
            System.exit(0);
        } else {

            InetAddress address = InetAddress.getByName(args[0]);
            final int port = Integer.valueOf(args[1]);
            if (args.length == 3) {
                if (args[2].equals("-u")) {
                    udp(address, port);
                }
            }

            //Socket socket = new Socket(address, port);
            //SocketAddress sockaddr = new InetSocketAddress(address, port);
            //socket.connect(sockaddr,10000);
           
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress(address, port));
            ByteBuffer buf = ByteBuffer.allocate(1024);
       
            String n_div[] = new String[0];
            //DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //DataInputStream input = new DataInputStream(socket.getInputStream());
            // int array[]=new int[10000];

            //INTERFACE
            System.out.println("INSTRUCTIONS:");
            System.out.println("-Type 0 to exit the program.\n" + "Type a number and press enter to send it, you will recieve the accumulator value");
            do {
                Scanner myInput = new Scanner(System.in);

                //EXECUTION OF THE PROGRAM
                while (true) {
                String n = myInput.nextLine();
                n = n.trim();
                n_div = n.split(" ");

                if (n_div[0].equals("0")) {
                    System.out.println("Program exiting...");
                    sc.finishConnect();
                    System.exit(0);
                }
               
                buf.clear();
                int alpha = Integer.parseInt(n);
                buf.putInt(alpha);
                buf.flip();
           
             //System.out.println("A " + new String(buf.array()));
           
           
                sc.write(buf);
                buf.clear();
               
                int a = sc.read(buf);
             
                if (a == -1) {
                    break;
                }
               
               
                System.out.println("Your result is: " + new String(buf.array()));
                //buf.clear();
            }
           
            }while (true);

        }
    }

    private static void DataOutputStream(OutputStream outs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void out(OutputStream outs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void udp(InetAddress address, final int port) throws SocketException, IOException {

       
      //  int timeout = 10000;
        String string_result;
        ByteBuffer buf = ByteBuffer.allocate(1024);
         InetSocketAddress serverAddress = new InetSocketAddress(address,
        port);
        //ARGUMENTS CHECK
        System.out.println("UDP MODE INITIALIZED");
        System.out.println("Write a number and press ENTER to send it.\n" + "Write '0' to close the client");
        Scanner myInput = new Scanner(System.in);

        //EXECUTION OF THE PROGRAM
        while (true) {
            DatagramChannel client = DatagramChannel.open();
            client.bind(null);
           
            String m = myInput.nextLine();

            if (m.equals("0")) {
                System.out.println("Program exiting...");
                System.exit(0);
            }

            //NETWORK (SEND PART)
          
            int beta = Integer.parseInt(m);
            //System.out.println("Esto es beta: "+ beta);
            buf.clear();
            buf.putInt(beta);
            //System.out.println("Esto es bufer: "+ new String(buf.array()));
            buf.flip();
            client.send(buf,serverAddress);
            buf.clear();
            
            System.out.println("Package sent! Waiting for result...");

          
           client.receive(buf);
           
           string_result = new String(buf.array());
           buf.flip();

                 System.out.println("Your result is: " + (string_result));

        }
    }

}
