import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Aguardando conexão...");
        Socket cliente = servidor.accept();

        BufferedReader entrada = new BufferedReader(
            new InputStreamReader(cliente.getInputStream()));
        PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);

        String msg = entrada.readLine();
        System.out.println("Recebido: " + msg);
        saida.println("Servidor recebeu: " + msg);

        cliente.close();
        servidor.close();
    }
}