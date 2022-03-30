import jpysocket
import socket
import time

import NLP

host = 'localhost'  # Host Name
port = 1623  # Port Number
s = socket.socket()  # Create Socket
s.bind((host, port))  # Bind Port And Host
s.listen(100)  # Socket is Listening
x = 0
while True:
    print("Socket Is Listening....")
    try:
        connection, address = s.accept()  # Accept the Connection
        print("Connected To ", address)
        msgsend = jpysocket.jpyencode("Thank You For Connecting.")  # Encript The Msg
        connection.send(msgsend)  # Send Msg

        while True:
            data1 = connection.recv()  # Recieve msg
            data = jpysocket.jpydecode(data1)  # Decript msg
            print(data)
            if (data):
                print('---------',x,'-----------')
                if(data.strip("name")):
                    print(data)
                    nlp = NLP(data)
                    name = nlp.getName()
                    msgsend = jpysocket.jpyencode(name)
                    connection.send(msgsend)
                print(data)
                print('--------------------')
            else:
                print('no data from', address)



    finally:
        s.close()  # Close connection
        print("Connection Closed.")
