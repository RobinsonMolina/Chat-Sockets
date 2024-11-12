import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 3000;
    static Set<PrintWriter> clientWriters = new HashSet<>();// Almacena los clientes conectados

    public static void main(String[] args) {
        System.out.println("El servidor de chat está funcionando en el puerto " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                //System.out.println("Cliente conectado: " + clientSocket); // Para ver que clientes se conectan
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

