import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
//import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

public class tcp1ser {

    public static void main(String[] args) throws SocketException, IOException {
        //ATRIBUTES
        if (args.length < 1) {
            System.out.println("Wrong syntax! The correct syntax is: tpc1ser PORT");
            System.exit(0);
        } else if (args.length > 1) {
            System.out.println("So many arguments! The correct syntax is: tpc1ser PORT");
            System.exit(0);
        } else {
            String port = args[0];
            
            
            int counter = 0;
            
            
            //START
            System.out.println("Initializing server...");


            //NETWORK (RECEIVE PART)

           
            ServerSocket socketTCP = new ServerSocket(Integer.valueOf(port));

            //loop {
            //try {
while (true) {
            Socket socket = socketTCP.accept();
            //DataInputStream input1 = new DataInputStream(socket.getInputStream());
            
            
           // System.out.println(i);
    try {
            ClientManagment c=new ClientManagment();
            Thread t=new Thread(c);
            t.start();
            c.run(socket);
        
    } catch(Exception e) {
        
    }
        } }
    }
    
    
}
        


