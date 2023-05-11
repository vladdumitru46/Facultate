package com.example;

import com.example.interfaces.IRepoBoss;
import com.example.interfaces.IRepoEmployee;
import com.example.interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    private final IRepoBoss repoBoss;
    private final IRepoEmployee repoEmployee;
    private final IRepository<Task, Integer> repoTask;
    private final IRepository<TaskOfEmployee, Integer> repoTaskOfEmployee;

    private Map<Integer, IServiceObserver> observerMap;
    private Map<Integer, IServiceObserver> observerMapBoss;

    public Service(IRepoBoss repoBoss, IRepoEmployee repoEmployee, IRepository<Task, Integer> repoTask, IRepository<TaskOfEmployee, Integer> repoTaskOfEmployee) {
        this.repoBoss = repoBoss;
        this.repoEmployee = repoEmployee;
        this.repoTask = repoTask;
        this.repoTaskOfEmployee = repoTaskOfEmployee;
    }

    public void logInBoss(String email, String password, IServiceObserver client) throws Exception {
        Boss boss = repoBoss.findByEmailAndPassword(email, password);
        if (boss == null) {
            throw new Exception("Email or password invalid!");
        } else {
            observerMapBoss.put(boss.getId(), client);
        }
    }

    @Override
    public void logOutBoss(Boss boss, IServiceObserver client) {
        observerMapBoss.remove(boss.getId());
    }

    @Override
    public void logOutEmployee(Employee employee, IServiceObserver client) throws Exception {
        IServiceObserver localClient = observerMap.remove(employee.getId());
        if (localClient == null) {
            throw new Exception("Employee " + employee.getId() + " is not logged in!");
        }
        notifyBossEmployeeLogOut(employee);
    }

    public void logInEmployee(String email, String password, IServiceObserver client) throws Exception {
        Employee employee = repoEmployee.findByEmailAndPassword(email, password);
        if (employee != null) {
            if (observerMap.get(employee.getId()) != null) {
                throw new Exception("Employee already logged in!");
            }
            observerMap.put(employee.getId(), client);
            notifyBossEmployeeLogIn(employee);
        } else {
            throw new Exception("Email or password invalid!");
        }
    }

    private void notifyBossEmployeeLogIn(Employee employee) {
        List<Employee> employees = (List<Employee>) repoEmployee.findAll();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (var em : employees) {
            IServiceObserver client = observerMapBoss.get(em.getId());
            if (client != null) {
                executorService.execute(() -> {
                    try {
                        client.employeeLogIn(employee);
                    } catch (Exception e) {
                        System.out.println("Error on employee log in!");
                    }
                });
            }
        }
        executorService.shutdown();
    }

    private void notifyBossEmployeeLogOut(Employee employee) {
        List<Employee> employees = (List<Employee>) repoEmployee.findAll();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (var em : employees) {
            IServiceObserver client = observerMapBoss.get(em.getId());
            if (client != null) {
                executorService.execute(() -> {
                    try {
                        client.employeeLogOut(employee);
                    } catch (Exception e) {
                        System.out.println("Error on employee log in!");
                    }
                });
            }
        }
        executorService.shutdown();
    }

    @Override
    public List<Employee> getLoggedInEmployees(Boss boss) {
        List<Employee> employees = (List<Employee>) repoEmployee.findAll();
        List<Employee> result = new ArrayList<>();
        for (var em : employees) {
            if (observerMap.containsKey(em.getId())) {
                result.add(em);
            }
        }
        return result;
    }

    public Task addTask(Task task) {
        return repoTask.add(task);
    }

    public Task findTask(Integer id) {
        return repoTask.findOne(id);
    }

    public void addTaskOfEmployees(TaskOfEmployee task) throws Exception {
        IServiceObserver receiver = observerMap.get(task.getEmployeeId());
        if (receiver != null) {
            repoTaskOfEmployee.add(task);
            receiver.receivedTask(task);
        } else {
            throw new Exception("Employee " + task.getEmployeeId() + " is not logged in!");
        }
    }

    public TaskOfEmployee updateTaskOfEmployees(TaskOfEmployee task) {
        return repoTaskOfEmployee.update(task);
    }

    public Employee updateEmployee(Employee employee) {
        return repoEmployee.update(employee);
    }

    public Employee deleteEmployee(Integer id) {
        return repoEmployee.delete(id);
    }
}
