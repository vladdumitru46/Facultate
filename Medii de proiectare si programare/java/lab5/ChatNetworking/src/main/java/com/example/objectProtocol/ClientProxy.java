package com.example.objectProtocol;

import com.example.*;
import com.example.repositoryes.EmployeeRepo;
import com.example.repositoryes.interfaces.RepoEmployee;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientProxy implements IService {

    private final String host;
    private final int port;

    private IServiceObserver client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private final BlockingQueue<Response> responseBlockingQueue;

    private volatile boolean finished;

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
        responseBlockingQueue = new LinkedBlockingQueue<Response>();
    }

    public void logInEmployee(String email, String password, IServiceObserver client) throws Exception {
        initializeConnection();
        RepoEmployee employee = new EmployeeRepo(new Properties());
        EmployeeAtOffice employeeAtOffice = employee.findOneByEmailAndPassword(email, password);
        sendRequest(new LogInRequest(employeeAtOffice));
        Response response = readResponse();
        if (response instanceof OKResponse) {
            this.client = client;
            return;
        }
        if (response instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) response;
            closeConnection();
            throw new Exception(errorResponse.getMessage());
        }
    }

    public void sellTickets(String showName, String buyerName, Integer noOFTickets) throws Exception {
        Buyers buyer = new Buyers(buyerName, noOFTickets, showName);
        sendRequest(new SellTicketsRequest(buyer));
        Response response = readResponse();
        if (response instanceof ErrorResponse err) {
            throw new Exception(err.getMessage());
        }
    }

    @Override
    public List<Show> searchArtistByDate(LocalDate date) {
        return null;
    }

    @Override
    public Show findOneShow(String showName) {
        return null;
    }

    public void updateShow(Show show) throws Exception {
        sendRequest(new UpdateShowRequest(show));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) response;
            throw new Exception(errorResponse.getMessage());
        }
    }

    @Override
    public EmployeeAtOffice findEmployeeByEmailAndPassword(String email, String password) throws Exception {
        sendRequest(new FindEmployeeRequest(email, password));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) response;
            throw new Exception(errorResponse.getMessage());
        }
        FindEmployeeResponse findEmployeeResponse = (FindEmployeeResponse) response;
        EmployeeAtOffice employeeAtOffice = findEmployeeResponse.getEmployeeAtOffice();
        return employeeAtOffice;
    }

    public void logOut(EmployeeAtOffice employeeAtOffice) throws Exception {
        sendRequest(new LogOutRequest(employeeAtOffice));
        Response response = readResponse();
        closeConnection();
        if (response instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) response;
            throw new Exception(errorResponse.getMessage());
        }
    }

    public List<Show> getAllShows(EmployeeAtOffice employeeAtOffice) throws ChatException {
        sendRequest(new GetAllShowsRequest(employeeAtOffice));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new ChatException(err.getMessage());
        } else {
            GetShowsResponse getAllShowsResponse = (GetShowsResponse) response;
            List<Show> listShows = getAllShowsResponse.getList();
            return listShows;
        }
    }

    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
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
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startReader() {
        Thread thread = new Thread(new ReaderThread());
        System.out.println("start reader");
        thread.start();
    }


    private void handleUpdate(UpdateResponse updateResponse) {
        if (updateResponse instanceof NewSellTicketResponse) {
            NewSellTicketResponse newSellTicketResponse = (NewSellTicketResponse) updateResponse;
            Buyers buyers = newSellTicketResponse.getBuyers();
            try {
                client.ticketSold(buyers);
                System.out.println("handle update proxy");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ReaderThread implements Runnable {
        public void run() {
            while (!finished) {
                try {
                    Object response = input.readObject();
                    if (response instanceof UpdateResponse) {
                        System.out.println("run in proxy");
                        handleUpdate((UpdateResponse) response);
                    } else {
                        try {
                            responseBlockingQueue.put((Response) response);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
