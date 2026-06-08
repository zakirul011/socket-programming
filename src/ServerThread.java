import java.io.*;
import java.net.*;

public class ServerThread {

    public static void main(String[] args) {
        try{           
            // create server socket
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server Thread listening...");
            
            while (true) {
                // connect socket
                Socket socket = serverSocket.accept();
                System.out.println("connected : " + socket.getRemoteSocketAddress());

                // create new thread
                ClientThread clientThread = new ClientThread(socket);
                Thread thread = new Thread(clientThread);
                thread.start();
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}

// Handle the every client thread
class ClientThread implements Runnable{
    Socket socket;

    ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());            
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            while (true) {
                // receive msg
                String msg = dis.readUTF();
                System.out.println(socket.getRemoteSocketAddress() + ": " + msg);

                // close chat
                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println("Connection closed.");
                    break;
                }
                
                // send msg
                dos.writeUTF("Received (" + msg + ")");
                dos.flush();                
            }

            // close                 
            socket.close();
            System.out.println("closed: " + socket.getRemoteSocketAddress());

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}