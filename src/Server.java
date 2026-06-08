import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    public static void main(String[] args) {
        try{           
            // create server socket
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server listening...");
            
            // connect socket
            Socket socket = serverSocket.accept();
            System.out.println("connected : " + socket.getRemoteSocketAddress());


            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());            
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String sendMsg = "", recevMsg = "";
            
            Scanner input = new Scanner(System.in);

            while (true) {
                // receive msg
                recevMsg = dis.readUTF();
                System.out.println(socket.getRemoteSocketAddress() + " : " + recevMsg);

                // close chat
                if (recevMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                
                // send msg
                System.out.print("Your Message: ");
                sendMsg = input.nextLine();
                dos.writeUTF(sendMsg);
                dos.flush();     

                // close chat
                if (sendMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Server disconnected.");
                    break;
                }               
            }

            // close       
            serverSocket.close();
            input.close();          
            socket.close();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}