package com.example.objectProtocol.protocols;

import com.example.IService;
import com.example.IServiceObserver;
import com.example.Users;
import com.example.objectProtocol.interfaces.Request;
import com.example.objectProtocol.interfaces.Response;
import com.example.objectProtocol.interfaces.UpdateResponse;
import com.example.objectProtocol.requestClasses.GetLoggedInUsers;
import com.example.objectProtocol.requestClasses.LogInRequest;
import com.example.objectProtocol.responseClasses.ErrorResponse;
import com.example.objectProtocol.responseClasses.GetLoggedInUsersResponse;
import com.example.objectProtocol.responseClasses.OkResponse;
import com.example.repository.RepoUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ObjectProxy implements IService {
    private final String host;
    private final int port;
    private IServiceObserver client;
    //    private IServiceObserverBoss clientBoss;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket connection;

    private final BlockingQueue<Response> responseBlockingQueue;
    private volatile boolean finished;

    public ObjectProxy(String host, int port) {
        this.host = host;
        this.port = port;
        responseBlockingQueue = new LinkedBlockingQueue<>();
    }


    private void closeConnection() {
        finished = true;
        try {
            inputStream.close();
            outputStream.close();
            connection.close();
            client = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Response readResponse() {
        Response response = null;
        try {
            response = responseBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void sendRequest(Request request) {
        try {
            outputStream.writeObject(request);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startReader() {
        Thread thread = new Thread(new ReaderThread());
        thread.start();
    }

    private void handleUpdate(UpdateResponse updateResponse) {

    }

    @Override
    public void logIn(String userName, IServiceObserver client) throws Exception {
        initializeConnection();
        RepoUser repoPlayer = new RepoUser();
        Users users = repoPlayer.findOneByUserName(userName);
        sendRequest(new LogInRequest(users));
        Response response = readResponse();
        if (response instanceof OkResponse) {
            this.client = client;
            return;
        }
        if (response instanceof ErrorResponse errorResponse) {
            closeConnection();
            throw new Exception(errorResponse.getMessage());
        }
    }

    @Override
    public List<Users> getLoggedInUsers(Users users) throws Exception {
        sendRequest(new GetLoggedInUsers(users));
        Response response = readResponse();
        if (response instanceof ErrorResponse err) {
            throw new Exception(err.getMessage());
        } else {
            GetLoggedInUsersResponse getLoggedInEmployeeResponse = (GetLoggedInUsersResponse) response;
            return getLoggedInEmployeeResponse.getList();
        }
    }

    private class ReaderThread implements Runnable {

        @Override
        public void run() {
            while (!finished) {
                try {
                    Object response = inputStream.readObject();
                    if (response instanceof UpdateResponse) {
                        handleUpdate((UpdateResponse) response);
                    } else {
                        try {
                            responseBlockingQueue.put((Response) response);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
