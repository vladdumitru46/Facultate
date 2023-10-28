package com.example.utils;

import com.example.IService;
import com.example.protobuffProtocol.ProtoWorker;

import java.net.Socket;


public class ProtobuffConcurrentServer extends AbsConcurrentServer {
    private final IService chatServer;

    public ProtobuffConcurrentServer(int port, IService chatServer) {
        super(port);
        this.chatServer = chatServer;
        System.out.println("Chat- ChatProtobuffConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        ProtoWorker worker = new ProtoWorker(chatServer, client);
        return new Thread(worker);
    }
}