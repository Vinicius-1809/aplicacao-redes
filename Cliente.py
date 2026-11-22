import socket

ip = input("IP do servidor: ").strip()

try:
    porta = int(input("Porta do servidor: ").strip())
except ValueError:
    print("Porta inválida.")
    raise SystemExit(1)

mensagem = input("Mensagem a enviar: ")

try:
    with socket.create_connection((ip, porta), timeout=10) as sock:
        print(f"Conectado a {ip}:{porta}")
        with sock.makefile("rw", encoding="utf-8", newline="\n") as arquivo:
            arquivo.write(mensagem + "\n")
            arquivo.flush()
            print("Resposta:", arquivo.readline().strip())
except ConnectionRefusedError:
    print("Conexão recusada: confira se o servidor está rodando e se IP/porta estão corretos.")
except TimeoutError:
    print("Tempo esgotado: confira o IP e a rede do VirtualBox.")
except OSError as e:
    print(f"Erro: {e}")