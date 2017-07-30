# -*- coding:utf8 -*-
# python3
from socket import *
import threading
import time


HOST = '127.0.0.1'
PORT = 5000
ADDR = (HOST, PORT)
BUFSIZ = 1024

tcp_client = socket(AF_INET, SOCK_STREAM)
tcp_client.connect(ADDR)


def send_message():
    while True:
        input_data = input(">>> ")
        if not input_data:
            break
        tcp_client.send(bytes(input_data, 'utf8'))
    tcp_client.close()


def recv_message():
    while True:
        get_data = tcp_client.recv(BUFSIZ)
        if get_data:
            print(get_data)

        time.sleep(1)


if __name__ == '__main__':
    recv_threading = threading.Thread(target=recv_message,args=())
    recv_threading.start()
    send_threading = threading.Thread(target=send_message,args=())
    send_threading.start()

