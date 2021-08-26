//
// Created by gid on 26/08/2021.
//

#ifndef MYAPPREMOTECOM_KLIENT_H
#define MYAPPREMOTECOM_KLIENT_H
#pragma once
#include <string>
#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string>
using namespace std;

class klient {
public:
    int connectToServer(string hostname, int port);

    void sendData(int sock, string data);

    string recvData(int sock);

    void disconnectFromServer(int sock);

    void disconnectFromServer();

    string recvData();

    void sendData(string data);
};


#endif //MYAPPREMOTECOM_KLIENT_H
