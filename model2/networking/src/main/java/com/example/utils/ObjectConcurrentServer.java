package com.example.utils;

import com.example.IService;
import com.example.objectProtocol.protocols.ObjectWorker;

import java.net.Socket;

public class ObjectConcurrentServer extends AbsConcurrentServer {

    private final IService service;

    public ObjectConcurrentServer(int port, IService service) {
        super(port);
        this.service = service;
    }

    @Override
    protected Thread createWorker(Socket client) {
        ObjectWorker clientObjectWorker = new ObjectWorker(service, client);
        return new Thread(clientObjectWorker);
    }
}
