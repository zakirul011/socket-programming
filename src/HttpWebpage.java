import java.io.*;
import java.net.*;

import com.sun.net.httpserver.*;

public class HttpWebpage {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/html",  new HtmlHanlder());

            server.start();

            System.out.println("Server is running on the port : 8080");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}


class HtmlHanlder implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String html = """
            <!DOCTYPE html>
            <html>
                <body>

                <h1>The input formenctype attribute</h1>

                <p>The formenctype attribute specifies how the form data should be encoded when submitted.</p>

                <form action="/action_page_binary.asp" method="post">
                <label for="fname">First name:</label>
                <input type="text" id="fname" name="fname"><br><br>
                <input type="submit" value="Submit">
                <input type="submit" formenctype="multipart/form-data" value="Submit as Multipart/form-data">
                </form>

                </body>
            </html>
        """;

        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, html.length());

        OutputStream os = exchange.getResponseBody();
        os.write(html.getBytes());        
        
        os.close();
    }   
    
}