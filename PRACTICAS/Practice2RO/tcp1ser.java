import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
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
           
        
            System.out.println("Initializing server...");



            ServerSocket socketTCP = new ServerSocket(Integer.valueOf(port));

while (true) {
            Socket socket = socketTCP.accept();

    while (true) {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            
 
            counter=input.readInt()+counter;
            System.out.println(counter);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeInt(counter);
        
            
        }catch (Exception e) {
            counter = 0;
            System.out.println("Client disconnected.");
            break;
            
        }
        
}
        } }
    }
}
        


