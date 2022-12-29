
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class ClientManagment implements Runnable {
    
    
           
    ClientManagment() {
                
               
           }
            
    public void run(Socket socket) throws IOException {
            
            String message;
            int counter = 0;
            while (true) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            try {
             message = input.readUTF();
                String[] message_div = new String[0];
                message_div = message.split(" ");
                
            
            int a;
           
            for (int i = 0; i <message_div.length ; i++) {
                        a = Integer.parseInt(message_div[i]);
                       
                        counter = counter+a;
                        System.out.println(counter);
                        
                    }
          
            
            
           
           output.writeInt(counter);
      
            
        
            
        }catch (Exception e) {
            counter = 0;
           // i=0;
            //supercounter = 0;
            System.out.println("Client disconnected.");
            break;
            
        }
           
    }
    }

    @Override
    public void run() {
        
    }

    

    
    

   
  

    
    
    
}
