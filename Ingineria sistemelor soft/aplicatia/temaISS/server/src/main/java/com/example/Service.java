package com.example;

import com.example.interfaces.IRepoBoss;
import com.example.interfaces.IRepoEmployee;
import com.example.interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    private final IRepoBoss repoBoss;
    private final IRepoEmployee repoEmployee;
    private final IRepository<Task, Integer> repoTask;
    private final IRepository<TaskOfEmployee, Integer> repoTaskOfEmployee;

    private final IRepository<EmployeeAndArrivalTime, Integer> employeeAndArrivalTimeIntegerIRepository;

    private final Map<Integer, IServiceObserver> observerMap;
    private final Map<Integer, IServiceObserverBoss> observerMapBoss;

    public Service(IRepoBoss repoBoss, IRepoEmployee repoEmployee, IRepository<Task, Integer> repoTask, IRepository<TaskOfEmployee, Integer> repoTaskOfEmployee, IRepository<EmployeeAndArrivalTime, Integer> employeeLogInTime) {
        this.repoBoss = repoBoss;
        this.repoEmployee = repoEmployee;
        this.repoTask = repoTask;
        this.repoTaskOfEmployee = repoTaskOfEmployee;
        this.employeeAndArrivalTimeIntegerIRepository = employeeLogInTime;
        observerMapBoss = new ConcurrentHashMap<>();
        observerMap = new ConcurrentHashMap<>();
    }

    public Boss getBossByEmailAndPassword(String email, String password) {
        return repoBoss.findByEmailAndPassword(email, password);
    }

    public Employee getEmployeeByEmailAndPassword(String email, String password) {
        return repoEmployee.findByEmailAndPassword(email, password);
    }

    public void logInBoss(String email, String password, IServiceObserverBoss client) throws Exception {
        Boss boss = repoBoss.findByEmailAndPassword(email, password);
        if (boss == null) {
            throw new Exception("Email or password invalid!");
        } else {
            this.observerMapBoss.put(boss.getId(), client);
        }
    }

    @Override
    public void logOutBoss(Boss boss, IServiceObserverBoss client) {
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

    public synchronized void logInEmployee(String email, String password, IServiceObserver client) throws Exception {
        Employee employee = repoEmployee.findByEmailAndPassword(email, password);
        if (employee != null) {
            if (observerMap.get(employee.getId()) != null) {
                throw new Exception("Employee already logged in!");
            }
            observerMap.put(employee.getId(), client);
            System.out.println("S-a logat: " + employee.getId() + " si observer map are: " + observerMap.size());
            notifyBossEmployeeLogIn(employee);
        } else {
            throw new Exception("Email or password invalid!");
        }
    }

    private void notifyBossEmployeeLogIn(Employee employee) {
        System.out.println("intrii in notify?");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IServiceObserverBoss client = observerMapBoss.get(1);
        if (client != null) {
            System.out.println("Da intrii aici??");
            executorService.execute(() -> {
                try {
                    System.out.println("BOSS: " + client);
                    client.employeeLogIn(employee);
                } catch (Exception e) {
                    System.out.println("Error on employee log in!");
                }
            });
        }
        executorService.shutdown();
    }

    private void notifyBossEmployeeLogOut(Employee employee) {
        List<Employee> employees = (List<Employee>) repoEmployee.findAll();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (var em : employees) {
            IServiceObserverBoss client = observerMapBoss.get(em.getId());
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

    public EmployeeAndArrivalTime addEmployeeToWork(EmployeeAndArrivalTime employeeAndArrivalTime) {
        return employeeAndArrivalTimeIntegerIRepository.add(employeeAndArrivalTime);
    }

    public EmployeeAndArrivalTime deleteEmployeeFromWork(Integer id) {
        return employeeAndArrivalTimeIntegerIRepository.delete(id);
    }

    public List<EmployeeAndArrivalTime> getAllEmployeesFromWork() {
        return (List<EmployeeAndArrivalTime>) employeeAndArrivalTimeIntegerIRepository.findAll();
    }
}
