import socket

ip = input("IP de escuta (Enter para 0.0.0.0 = todas as interfaces): ").strip() or "0.0.0.0"

try:
    porta = int(input("Porta de escuta (1024-65535): ").strip())
except ValueError:
    print("Porta inválida.")
    raise SystemExit(1)

servidor = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Permite reiniciar o servidor logo após encerrar, sem erro "Address already in use"
servidor.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

try:
    servidor.bind((ip, porta))
except OSError as e:
    print(f"Não foi possível usar esse IP/porta: {e}")
    raise SystemExit(1)

servidor.listen(5)
print(f"Escutando em {ip}:{porta}... aguardando conexão.")

conexao, endereco = servidor.accept()
print(f"Cliente conectado: {endereco[0]}:{endereco[1]}")

with conexao, conexao.makefile("rw", encoding="utf-8", newline="\n") as arquivo:
    msg = arquivo.readline().strip()
    print(f"Recebido: {msg}")
    arquivo.write(f"Servidor recebeu: {msg}\n")
    arquivo.flush()

servidor.close()