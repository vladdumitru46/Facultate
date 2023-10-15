package com.example.utils;

import com.example.IService;
import com.example.objectProtocol.ClientObjectWorker;

import java.net.Socket;

public class ObjectConcurrentServer extends AbsConcurrentServer {

    private IService service;

    public ObjectConcurrentServer(int port, IService service) {
        super(port);
        this.service = service;
    }

    @Override
    protected Thread createWorker(Socket client) {
        ClientObjectWorker clientObjectWorker = new ClientObjectWorker(service, client);
        return new Thread(clientObjectWorker);
    }
}
