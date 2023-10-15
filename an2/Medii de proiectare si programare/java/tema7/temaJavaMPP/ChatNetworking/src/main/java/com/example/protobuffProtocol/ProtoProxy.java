package com.example.protobuffProtocol;

import com.example.*;
import com.example.repositoryes.EmployeeRepo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProtoProxy implements IService {

    private final String host;
    private final int port;

    private IServiceObserver client;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket connection;

    private final BlockingQueue<Protobufs.Response> responseBlockingQueue;
    private volatile boolean finished;

    public ProtoProxy(String host, int port) {
        this.host = host;
        this.port = port;
        responseBlockingQueue = new LinkedBlockingQueue<>();
    }


    @Override
    public void logInEmployee(String email, String password, IServiceObserver client) throws Exception {
        initializeConnection();
        EmployeeRepo employeeRepo = new EmployeeRepo(new Properties());
        EmployeeAtOffice employeeAtOffice = employeeRepo.findOneByEmailAndPassword(email, password);
        sendRequest(ProtoUtils.createLogInRequest(employeeAtOffice));
        Protobufs.Response response = readResponse();
        System.out.println("fupa response");
        if (response.getType() == Protobufs.Response.Type.Ok) {
            this.client = client;
            return;
        }
        if (response.getType() == Protobufs.Response.Type.Error) {
            String errorText = ProtoUtils.getError(response);
            closeConnection();
            throw new Exception(errorText);
        }
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

    private Protobufs.Response readResponse() {
        Protobufs.Response response = null;
        try {
            response = responseBlockingQueue.take();
            System.out.println("am luat response");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void sendRequest(Protobufs.Request request) {
        try {
            request.writeDelimitedTo(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            outputStream = connection.getOutputStream();
            outputStream.flush();
            inputStream = connection.getInputStream();
            finished = false;
            startRender();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void startRender() {
        Thread tw = new Thread(new ReaderThread());
        tw.start();
    }

    private void handleUpdate(Protobufs.Response updateResponse) {
        if (updateResponse.getType() == Protobufs.Response.Type.SellTicketsResponse) {
            Buyers buyers = ProtoUtils.getBuyers(updateResponse);
            try {
                client.ticketSold(buyers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ReaderThread implements Runnable {
        public void run() {
            System.out.println("e in run?" + finished);
            while (!finished) {
                System.out.println("while>");
                try {
                    System.out.println("intrii aici?");
                    Protobufs.Response response = Protobufs.Response.parseDelimitedFrom(inputStream);
                    System.out.println("response received " + response);

                    if (isUpdateResponse(response.getType())) {
                        handleUpdate(response);
                    } else {
                        try {
                            responseBlockingQueue.put(response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error " + e);
                }
            }
        }
    }

    private boolean isUpdateResponse(Protobufs.Response.Type type) {
        return type == Protobufs.Response.Type.SellTicketsResponse;
    }

    @Override
    public void logOut(EmployeeAtOffice employeeAtOffice) throws Exception {
        sendRequest(ProtoUtils.createLogOutRequest(employeeAtOffice));
        Protobufs.Response response = readResponse();
        closeConnection();
        if (response.getType() == Protobufs.Response.Type.Error) {
            String errortext = ProtoUtils.getError(response);
            throw new Exception(errortext);
        }
    }

    @Override
    public List<Show> searchArtistByDate(LocalDate date) {
        return null;
    }

    @Override
    public List<Show> getAllShows(EmployeeAtOffice employeeAtOffice) throws ChatException {
        return null;
    }

    @Override
    public void sellTickets(String showName, String buyerName, Integer noOFTickets) throws Exception {
        sendRequest(ProtoUtils.createSellTicketRequest(new Buyers(buyerName, noOFTickets, showName)));
        Protobufs.Response response = readResponse();
        if (response.getType() == Protobufs.Response.Type.Error) {
            String errortext = ProtoUtils.getError(response);
            throw new Exception(errortext);
        }
    }

    @Override
    public Show findOneShow(String showName) {
        return null;
    }

    @Override
    public void updateShow(Show show) throws Exception {

    }

    @Override
    public EmployeeAtOffice findEmployeeByEmailAndPassword(String email, String password) throws Exception {
        return null;
    }
}
