import java.io.*;
import java.net.*;

import com.sun.net.httpserver.*;

public class HttpPaintext {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/text",  new TextHandler());

            server.start();

            System.out.println("Server is running on the port : 8080");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}


class TextHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String text = "Hi, This is a simple text...";

        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        exchange.sendResponseHeaders(200, text.length());

        OutputStream os = exchange.getResponseBody();
        os.write(text.getBytes());        
        
        os.close();
    }   
    
}