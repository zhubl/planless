# -*- coding:utf8 -*-
# python3
from socket import *
import threading


HOST = '127.0.0.1'
PORT = 5000
ADDR = (HOST, PORT)
BUFSIZ = 1024

tcp_server = socket(AF_INET, SOCK_STREAM)
tcp_server.bind(ADDR)
tcp_server.listen(5)

client_list = []


def accept_connection():
    while True:
        tcp_client, from_addr = tcp_server.accept()
        client_list.append(tcp_client)
        handle_message("[{}] join in chat room".format(from_addr))
        wait_message(tcp_client, from_addr)


def handle_message(msg):
    for client in client_list:
        client.send(bytes(msg, "utf8"))


def _wait(client, addr):
    while True:
        data = client.recv(BUFSIZ)
        if not data:
            client.close()
            client_list.pop(client_list.index(client))
            handle_message("[{}]: disconnected".format(addr))
            break
        handle_message("[{}]: {}".format(addr, data))


def wait_message(client, addr):
    wait_threading = threading.Thread(target=_wait, args=(client, addr))
    wait_threading.start()


if __name__ == '__main__':
    connect_threading = threading.Thread(target=accept_connection, args=())
    connect_threading.start()
