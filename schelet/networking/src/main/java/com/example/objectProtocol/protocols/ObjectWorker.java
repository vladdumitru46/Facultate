package com.example.objectProtocol.protocols;

import com.example.IService;
import com.example.IServiceObserver;
import com.example.User;
import com.example.objectProtocol.interfaces.Request;
import com.example.objectProtocol.interfaces.Response;
import com.example.objectProtocol.requestClasses.LogInRequest;
import com.example.objectProtocol.requestClasses.LogOutRequest;
import com.example.objectProtocol.requestClasses.UpdateUserRequest;
import com.example.objectProtocol.responseClasses.ErrorResponse;
import com.example.objectProtocol.responseClasses.OkResponse;
import com.example.objectProtocol.responseClasses.UpdateUserResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectWorker implements Runnable, IServiceObserver {

    private final IService server;
    private final Socket connection;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private volatile boolean connected;

    public ObjectWorker(IService server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try {
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (connected) {
            try {
                Object request = inputStream.readObject();
                Object response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse((Response) response);
                }
            } catch (IOException | ClassNotFoundException e) {
                connected = false;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            inputStream.close();
            outputStream.close();
            connection.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendResponse(Response response) throws IOException {
        synchronized (outputStream) {
            outputStream.writeObject(response);
            outputStream.flush();
        }
    }

    private Object handleRequest(Request request) {
        Response response = null;
        if (request instanceof LogInRequest logInRequest) {
            User player = logInRequest.getUser();
            try {
                server.logIn(player.getUserName(), this);
                return new OkResponse();
            } catch (Exception e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof LogOutRequest logOutRequest) {
            User user = logOutRequest.getUser();
            try {
                server.logOut(user);
                return new OkResponse();
            } catch (Exception e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof UpdateUserRequest updateUserRequest) {
            User user = updateUserRequest.getUser();
            try {
                server.updateUser(user);
                return new OkResponse();
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        return response;
    }


    @Override
    public void updateTable(User user) {
        try {
            sendResponse(new UpdateUserResponse(user));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
