import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("IP do servidor: ");
        String ip = teclado.nextLine().trim();

        System.out.print("Porta do servidor: ");
        int porta;
        try {
            porta = Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Porta inválida.");
            return;
        }

        System.out.print("Mensagem a enviar: ");
        String mensagem = teclado.nextLine();

        try (Socket socket = new Socket(ip, porta);
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado a " + ip + ":" + porta);
            saida.println(mensagem);
            System.out.println("Resposta: " + entrada.readLine());

        } catch (ConnectException e) {
            System.out.println("Conexão recusada: confira se o servidor está rodando e se IP/porta estão corretos.");
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}