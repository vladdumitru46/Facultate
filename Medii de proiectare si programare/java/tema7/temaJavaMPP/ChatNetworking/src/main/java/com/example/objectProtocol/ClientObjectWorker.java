package com.example.objectProtocol;

import com.example.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;


public class ClientObjectWorker implements Runnable, IServiceObserver {

    private final IService server;
    private final Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream outputStream;

    private volatile boolean connected;

    public ClientObjectWorker(IService service, Socket connection) {
        this.server = service;
        this.connection = connection;
        try {
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
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
            input.close();
            outputStream.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("ERROR" + e);
        }
    }

    private void sendResponse(Response response) throws IOException {
        synchronized (outputStream) {
            System.out.println("sendResponse Object");
            outputStream.writeObject(response);
            outputStream.flush();
        }
    }

    private Object handleRequest(Request request) {
        Response response = null;
        if (request instanceof LogInRequest) {
            LogInRequest logInRequest = (LogInRequest) request;
            EmployeeAtOffice employeeAtOffice = logInRequest.getEmployeeAtOffice();
            try {
                server.logInEmployee(employeeAtOffice.getEmail(), employeeAtOffice.getPassword(), this);
                return new OKResponse();
            } catch (Exception e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }

        if (request instanceof LogOutRequest) {
            LogOutRequest logOutRequest = (LogOutRequest) request;
            EmployeeAtOffice employeeAtOffice = logOutRequest.getEmployeeAtOffice();
            try {
                server.logOut(employeeAtOffice);
                connected = false;
                return new OKResponse();
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        if (request instanceof SellTicketsRequest) {
            SellTicketsRequest sellTicketsRequest = (SellTicketsRequest) request;
            Buyers buyers = sellTicketsRequest.getBuyers();
            try {
                server.sellTickets(buyers.getShowName(), buyers.getBuyerName(), buyers.getNoTicketsBought());
                return new OKResponse();
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof UpdateShowRequest) {
            UpdateShowRequest updateShowRequest = (UpdateShowRequest) request;
            Show show = updateShowRequest.getShow();
            try {
                server.updateShow(show);
                return new OKResponse();
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof GetAllShowsRequest) {
            System.out.println();
            GetAllShowsRequest getAllShowsRequest = (GetAllShowsRequest) request;
            EmployeeAtOffice employeeAtOffice = getAllShowsRequest.getEmployeeAtOffice();
            try {
                List<Show> list = server.getAllShows(employeeAtOffice);
                return new GetShowsResponse(list);
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        return response;
    }

    public void ticketSold(Buyers buyers) throws Exception {
        try {
            System.out.println("tivketSold ObjectWorker");
            sendResponse(new SellTicketResponse(buyers));
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}
