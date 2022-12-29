//package client;

import java.net.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class udpcli {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {

        //ATRIBUTES
        int i = 0;
        int narray[] = new int[10000];
        int timeout = 10000;

        //ARGUMENTS CHECK
        if (args.length < 2) {
            System.out.println("Not enough arguments!");
            System.exit(0);
        } else if (args.length > 2) {
            System.out.println("So many arguments!");
            System.exit(0);
        } else {

            InetAddress address = InetAddress.getByName(args[0]);
            final int port = Integer.valueOf(args[1]);

            //INTERFACE
            System.out.println("INSTRUCTIONS:");
            System.out.println("-Type 0 to exit the program.\n" + "-Type a string of numbers to send, EX:[1 2 5 6 7 8...] all in a row, in different rows or one by one (0-127).\n" + "-After typing all the numbers you want to send type 0 to send them.");
            Scanner myInput = new Scanner(System.in);

            //EXECUTION OF THE PROGRAM
            int n = myInput.nextInt();
            if (n>=128) {
            	System.out.println("Program can't manage numbers higher than 127.");
            	return;
            
            } else if (n == 0) {
                System.out.println("Program exiting...");
                return;
            }

            while (n != 0) {
                narray[i] = n;

                n = myInput.nextInt();
                if (n>=128) {
            	System.out.println("Program can't manage numbers higher than 127.");
            	return;
            	}
                i++;

            }
            
            if (n == 0) {
                System.out.println("Numbers got!");

                String[] array = new String[i];
                byte[] buf = new byte[i];

                if (narray[i] == 0) {

                    i--;
                    int a = i;

                    array[0] = String.valueOf(narray[0]);
                    buf[0] = Byte.valueOf(array[0]);
                    for (i = i; i != 0; i--) {
                        array[a] = String.valueOf(narray[i]);
                        buf[a] = Byte.valueOf(array[a]);
                        a--;

                    }

                    //NETWORK (SEND PART)
                    DatagramSocket socketUDP = new DatagramSocket();
                    socketUDP.setSoTimeout(timeout);

                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
                    socketUDP.send(packet);
                    System.out.println("Package sent! Waiting for result...");

                    //NETWORK (RECEIVE PART)
                    DatagramPacket result = new DatagramPacket(buf, buf.length, address, port);
                    while (true) {
                        try {
                            socketUDP.receive(result);
                            byte[] f = new byte[1];
                            f[0] = result.getData()[0];
                            if (f[0]==(-1)) {
                            System.out.println("SERVER'S COUNTER IS HIGHER OR EQUAL THAN 128, SERVER CAN'T MANAGE NUMBERS HIGHER THAN THAT!! SERVER HAS BEEN DISCONNECTED.");
                            break;
                            }
                            System.out.println("Your result is: " + (f[0]));
                            System.out.println("Closing application...");
                            socketUDP.close();
                            break;
                        } catch (SocketTimeoutException s) {
                            System.out.println("Timeout!");
                            break;
                        }

                    }

                }

            }

        }

    }
}
