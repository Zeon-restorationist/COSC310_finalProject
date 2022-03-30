import socket
import sys
import jpysocket

# Create a TCP/IP socket
sock = socket.socket()

# Bind the socket to the port
server_address = ('127.0.0.1', 8080)
print('starting up on {} port {}'.format(*server_address))
sock.bind(server_address)

# Listen for incoming connections
sock.listen(10)

while True:
    # Wait for a connection
    print('waiting for a connection')
    #connection, client_address = sock.accept()
    try:
        connection, client_address = sock.accept()
        print('connection from', client_address)
        # Receive the data in small chunks and retransmit it
        while True:

            data1 = connection.recv(1024)
            data = jpysocket.jpydecode(data1)
            print(data)
            message = "hello to you"
            msg = str.encode(message)
            print('sending data back to the client')

            connection.sendall(msg)

            if len(data) > 1:
                print("data:" , msg)
            else:
                print('no data from', client_address)
    finally:
        # Clean up the connection
        connection.close()