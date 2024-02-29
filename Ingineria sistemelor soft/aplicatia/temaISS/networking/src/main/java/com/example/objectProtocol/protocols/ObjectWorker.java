package com.example.objectProtocol.protocols;

import com.example.*;
import com.example.dto.TaskOfEmployeeDTO;
import com.example.interfaces.IRepoTaskOfEmployee;
import com.example.objectProtocol.interfaces.Request;
import com.example.objectProtocol.interfaces.Response;
import com.example.objectProtocol.requestClasses.*;
import com.example.objectProtocol.responseClasses.*;
import com.example.repository.RepoTaskOfEmployee;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ObjectWorker implements Runnable, IServiceObserver, IServiceObserverBoss {

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
        if (request instanceof LogInRequestBoss logInRequestBoss) {
            Boss boss = logInRequestBoss.getBoss();
            try {
                server.logInBoss(boss.getEmail(), boss.getPassword(), this);
                return new OkResponse();
            } catch (Exception e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof LogInRequestEmployee logInRequestEmployee) {
            Employee employee = logInRequestEmployee.getEmployee();
            try {
                server.logInEmployee(employee.getEmail(), employee.getPassword(), this);
                return new OkResponse();
            } catch (Exception e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }

        if (request instanceof LogOutBossRequest logOutBoss) {
            Boss boss = logOutBoss.getBoss();
            try {
                server.logOutBoss(boss, this);
                connected = false;
                return new OkResponse();
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof LogOutEmployeeRequest logOutEmployee) {
            Employee employee = logOutEmployee.getEmployee();
            try {
                server.logOutEmployee(employee);
                connected = false;
                return new OkResponse();
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        if (request instanceof GetLoggedInEmployeesRequest getLoggedInEmployeesRequest) {
            Boss boss = getLoggedInEmployeesRequest.getBoss();
            try {
                List<EmployeeAndArrivalTime> list = server.getLoggedInEmployees(boss);
                return new GetLoggedInEmployeeResponse(list);
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof GetTasksOfEmployeesRequest getTasksOfEmployeesRequest) {
            Boss boss = getTasksOfEmployeesRequest.getBoss();
            try {
                List<TaskOfEmployeeDTO> list = server.getTasksOfEmployeesDTO(boss);
                return new GetTasksOfEmployeesResponse(list);
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        if (request instanceof SendTaskRequest sendTaskRequest) {
            TaskOfEmployee task = sendTaskRequest.getTask();
            try {
                server.addTaskOfEmployees(task);
                return new OkResponse();
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof UpdatePerformancesTableRequest updatePerformancesTableRequest) {
            TaskOfEmployeeDTO task = updatePerformancesTableRequest.getTask();
            IRepoTaskOfEmployee taskOfEmployeeRepo = new RepoTaskOfEmployee();
            TaskOfEmployee taskOfEmployee = taskOfEmployeeRepo.findTaskOfEmployeeByEmployeeIdAndTaskId(task.getEmployeeId(), task.getTaskId());
            taskOfEmployee.setTaskStatus(TaskStatus.valueOf(task.getStatus()));
            try {
                System.out.println("task status: " + taskOfEmployee.getTaskStatus());
                server.updateTaskOfEmployees(taskOfEmployee);
                return new OkResponse();
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        return response;
    }

    @Override
    public void employeeLogIn(Employee employee) {
        try {
            sendResponse(new EmployeeLoggedInResponse(employee));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void employeeLogOut(Employee employee) {
        try {
            sendResponse(new EmployeeLoggedOutResponse(employee));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void receivedTask(TaskOfEmployee taskOfEmployee) {
        try {
            sendResponse(new SendTaskResponse(taskOfEmployee));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePerformancesTable(TaskOfEmployeeDTO taskOfEmployee) {
        try {
            sendResponse(new UpdatePerformancesTableResponse(taskOfEmployee));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}