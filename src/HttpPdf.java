import java.io.*;
import java.net.*;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpPdf {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);


            server.createContext("/pdf",  new PdfHandler());

            server.start();

            System.out.println("Server is running on the port : 8080");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}


class PdfHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        exchange.getResponseHeaders().set("Content-Type", "application/pdf");
        
        File file = new File("solution.pdf");

        byte [] bytearray = Files.readAllBytes(file.toPath());

        exchange.sendResponseHeaders(200, file.length());

        OutputStream os = exchange.getResponseBody();
        os.write(bytearray);        
        
        os.close();
    }   
    
}