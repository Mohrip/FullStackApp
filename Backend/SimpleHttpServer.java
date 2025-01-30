//package FullStackApp.Backend;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new StaticFileHandler());

        server.createContext("/api/message", new ApiHandler());

        server.setExecutor(null); 
        server.start();
        System.out.println("Server started on port 8080");
    }

    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String filePath = "/Users/mohammed/Desktop/FullStackApp/my-vue-app/dist" + (exchange.getRequestURI().getPath().equals("/") ? "/index.html" : exchange.getRequestURI().getPath());
            System.out.println("Serving file: " + filePath); 
            if (Files.exists(Paths.get(filePath))) {
                byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
                exchange.sendResponseHeaders(200, fileBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(fileBytes);
                os.close();
            } else {
                System.out.println("File not found: " + filePath); 
                exchange.sendResponseHeaders(404, -1); 
            }
        }
    }

    static class ApiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = "{\"message\": \"Hello from Java backend there is a lot to do!\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }
}