import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args) {
        try{
            // connect 
            Socket socket = new Socket("Localhost", 5000);
            System.out.println("Client connected...");

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String sendMsg = "", recevMsg = "";

            Scanner input = new Scanner(System.in);

            while (true) {

                // send msg
                System.out.print("Your Message: ");
                sendMsg = input.nextLine();
                dos.writeUTF(sendMsg);                
                dos.flush();

                // close chat
                if (sendMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                
                // receive msg
                recevMsg = dis.readUTF();
                System.out.println("Server : " + recevMsg);

                // close chat
                if (recevMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Server disconnected.");
                    break;
                }
            }

            // close
            socket.close();
            input.close();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}