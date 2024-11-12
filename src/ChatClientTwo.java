import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClientTwo {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 3000;
    private static String name = "Cliente";

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor de chat en " + SERVER_ADDRESS + ":" + PORT);

            System.out.println("Bienvenido al chat");
            System.out.print("¿Cómo te llamas?: ");
            name = scanner.nextLine();

            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer del servidor.");
                }
            });
            readThread.start();

            System.out.println("Escribe tus mensajes:");
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                out.println(name + ": " + message);
            }
        } catch (IOException e) {
            System.out.println("Error de conexión al servidor.");
            e.printStackTrace();
        }
    }
}
