import java.io.DataInputStream;
    import java.io.DataOutputStream;
    import java.net.*;
    import java.io.IOException;
    import java.io.OutputStream;
    import java.util.Arrays;
    import java.util.Scanner;

    class tpc1cli {


        public static void main(String[] args) throws SocketException, UnknownHostException, IOException {



            //ARGUMENTS CHECK
            if (args.length < 2) {
                System.out.println("Not enough arguments! The correct syntax is: tpc1cli IPADDRESS PORT");
                System.exit(0);
            } else if (args.length > 2) {
                System.out.println("So many arguments! The correct syntax is: tpc1cli IPADDRESS PORT");
                System.exit(0);
            } else {

                InetAddress address = InetAddress.getByName(args[0]);
                final int port = Integer.valueOf(args[1]);
                Socket socket =  new Socket(address,port);
              

                //INTERFACE
                System.out.println("INSTRUCTIONS:");
                System.out.println("-Type 0 to exit the program.\n" + "Type a number and press enter to send it, you will recieve the accumulator value");
                Scanner myInput = new Scanner(System.in);

                //EXECUTION OF THE PROGRAM
                int n = myInput.nextInt();
                while (n != 0) {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream());
                if (n == 0) {
                    System.out.println("Program exiting...");
                    return;
                }   

                               out.writeInt(n);
                               System.out.println(input.readInt());
                               n = myInput.nextInt();
      
                          
                       }
  

                }

                }


        private static void DataOutputStream(OutputStream outs) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void out(OutputStream outs) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
