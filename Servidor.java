import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("IP de escuta (Enter para 0.0.0.0 = todas as interfaces): ");
        String ip = teclado.nextLine().trim();
        if (ip.isEmpty()) {
            ip = "0.0.0.0";
        }

        System.out.print("Porta de escuta (1024-65535): ");
        int porta;
        try {
            porta = Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Porta inválida.");
            return;
        }

        try (ServerSocket servidor = new ServerSocket(porta, 50, InetAddress.getByName(ip))) {
            System.out.println("Escutando em " + ip + ":" + porta + "... aguardando conexão.");

            try (Socket cliente = servidor.accept();
                 BufferedReader entrada = new BufferedReader(
                         new InputStreamReader(cliente.getInputStream()));
                 PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true)) {

                System.out.println("Cliente conectado: "
                        + cliente.getInetAddress().getHostAddress()
                        + ":" + cliente.getPort());

                String msg = entrada.readLine();
                System.out.println("Recebido: " + msg);
                saida.println("Servidor recebeu: " + msg);
            }
        } catch (BindException e) {
            System.out.println("Não foi possível usar esse IP/porta (porta em uso ou IP que não pertence a esta máquina).");
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}