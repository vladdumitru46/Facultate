package com.example.protobuffProtocol;

import com.example.Buyers;
import com.example.EmployeeAtOffice;
import com.example.IService;
import com.example.IServiceObserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ProtoWorker implements Runnable, IServiceObserver {

    private final IService server;
    private final Socket connection;
    private InputStream inputStream;
    private OutputStream outputStream;
    private volatile boolean connected;

    public ProtoWorker(IService server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try {
            outputStream = connection.getOutputStream();
            inputStream = connection.getInputStream();
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ticketSold(Buyers buyers) throws Exception {
        try {
            sendResponse(ProtoUtils.createNewSellTicketResponse(buyers));
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void run() {
        while (connected) {
            try {
                Protobufs.Request request = Protobufs.Request.parseDelimitedFrom(inputStream);
                Protobufs.Response response = handleRequest(request);
                if (response != null) {
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            inputStream.close();
            outputStream.close();
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendResponse(Protobufs.Response response) throws IOException {
        response.writeDelimitedTo(outputStream);
        outputStream.flush();
    }

    private Protobufs.Response handleRequest(Protobufs.Request request) {
        Protobufs.Response response = null;
        switch (request.getType()) {
            case LogIn -> {
                EmployeeAtOffice employeeAtOffice = ProtoUtils.getEmployee(request);
                System.out.println("Email " + employeeAtOffice.getEmail());
                try {
                    server.logInEmployee(employeeAtOffice.getEmail(), employeeAtOffice.getPassword(), this);
                    return ProtoUtils.createOkResponse();
                } catch (Exception e) {
                    connected = false;
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case LogOut -> {
                EmployeeAtOffice employeeAtOffice = ProtoUtils.getEmployee(request);
                try {
                    server.logOut(employeeAtOffice);
                    connected = false;
                    return ProtoUtils.createOkResponse();
                } catch (Exception e) {
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case SellTicketsRequest -> {
                Buyers buyers = ProtoUtils.getBuyers(request);
                try {
                    server.sellTickets(buyers.getShowName(), buyers.getBuyerName(), buyers.getNoTicketsBought());
                    return ProtoUtils.createOkResponse();
                } catch (Exception e) {
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
        }
        return response;
    }
}
