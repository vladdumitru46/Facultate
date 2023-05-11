package com.example.objectProtocol;

import com.example.*;

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

    @Override
    public void logInBoss(String email, String password, IServiceObserver client) throws Exception {
        initializeConnection();
        RepoBoss repoBoss = new RepoBoss();
        Boss boss = repoBoss.findByEmailAndPassword(email, password);
        sendRequest(new LogInRequestBoss(boss));
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
    public void logOutBoss(Boss boss, IServiceObserver client) throws Exception {
        sendRequest(new LogOutBossRequest(boss));
        Response response = readResponse();
        closeConnection();
        if (response instanceof ErrorResponse errorResponse) {
            throw new Exception(errorResponse.getMessage());
        }
    }

    @Override
    public void logOutEmployee(Employee employee, IServiceObserver client) throws Exception {
        sendRequest(new LogOutEmployeeRequest(employee));
        Response response = readResponse();
        closeConnection();
        if (response instanceof ErrorResponse errorResponse) {
            throw new Exception(errorResponse.getMessage());
        }
    }

    @Override
    public void logInEmployee(String email, String password, IServiceObserver client) throws Exception {
        initializeConnection();
        RepoEmployee repoEmployee = new RepoEmployee();
        Employee employee = repoEmployee.findByEmailAndPassword(email, password);
        sendRequest(new LogInRequestEmployee(employee));
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
    public List<Employee> getLoggedInEmployees(Boss boss) throws Exception {
        sendRequest(new GetLoggedInEmployeesRequest(boss));
        Response response = readResponse();
        if (response instanceof ErrorResponse err) {
            throw new Exception(err.getMessage());
        } else {
            GetLoggedInEmployeeResponse getLoggedInEmployeeResponse = (GetLoggedInEmployeeResponse) response;
            return getLoggedInEmployeeResponse.getList();
        }
    }

    @Override
    public void addTaskOfEmployees(TaskOfEmployee task) throws Exception {
        sendRequest(new SendTaskRequest(task));
        Response response = readResponse();
        if (response instanceof ErrorResponse err) {
            throw new Exception(err.getMessage());
        }
    }

    @Override
    public TaskOfEmployee updateTaskOfEmployees(TaskOfEmployee task) {
        return null;
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
            finished = true;
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
        if (updateResponse instanceof SendTaskResponse sendTaskResponse) {
            TaskOfEmployee task = sendTaskResponse.getTaskOfEmployee();
            try {
                client.receivedTask(task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (updateResponse instanceof EmployeeLoggedOutResponse employeeLoggedOutResponse) {
            Employee employee = employeeLoggedOutResponse.getEmployee();
            try {
                client.employeeLogOut(employee);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (updateResponse instanceof EmployeeLoggedInResponse employeeLoggedInResponse) {
            Employee employee = employeeLoggedInResponse.getEmployee();
            try {
                client.employeeLogIn(employee);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ReaderThread implements Runnable {

        @Override
        public void run() {
            while (!finished) {
                try {
                    Object response = inputStream.readObject();
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
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
