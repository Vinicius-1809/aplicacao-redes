import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.0.10", 5000); // IP da máquina do servidor

        PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader entrada = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));

        saida.println("Olá, servidor!");
        System.out.println(entrada.readLine());

        socket.close();
    }
}