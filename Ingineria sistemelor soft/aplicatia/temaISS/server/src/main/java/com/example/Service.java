package com.example;

import com.example.dto.TaskOfEmployeeDTO;
import com.example.interfaces.*;

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
    private final IRepoTaskOfEmployee repoTaskOfEmployee;

    private final IRepoEmployeeLogInTime employeeAndArrivalTimeIntegerIRepository;

    private static Map<Integer, IServiceObserver> observerMap;
    private final Map<Integer, IServiceObserverBoss> observerMapBoss;

    public Service(IRepoBoss repoBoss, IRepoEmployee repoEmployee, IRepository<Task, Integer> repoTask, IRepoTaskOfEmployee repoTaskOfEmployee, IRepoEmployeeLogInTime employeeLogInTime) {
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

    public synchronized void logInEmployee(String email, String password, IServiceObserver client) throws Exception {
        Employee employee = repoEmployee.findByEmailAndPassword(email, password);
        if (employee != null) {
            if (observerMap.get(employee.getId()) != null) {
                throw new Exception("Employee already logged in!");
            }
            observerMap.put(employee.getId(), client);
            System.out.println("OBSERVER MAP LOGIN: " + observerMap);
            notifyBossEmployeeLogIn(employee);
        } else {
            throw new Exception("Email or password invalid!");
        }
    }

    @Override
    public void logOutEmployee(Employee employee, IServiceObserver client) throws Exception {
        System.out.println("OBSERVER MAP" + observerMap);
        IServiceObserver localClient = observerMap.remove(employee.getId());
        if (localClient == null) {
            throw new Exception("Employee " + employee.getId() + " is not logged in!");
        }
        notifyBossEmployeeLogOut(employee);
    }

    private void notifyBossEmployeeLogIn(Employee employee) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IServiceObserverBoss client = observerMapBoss.get(1);
        if (client != null) {
            executorService.execute(() -> {
                try {
                    client.employeeLogIn(employee);
                } catch (Exception e) {
                    System.out.println("Error on employee log in!");
                }
            });
        }
        executorService.shutdown();
    }

    private void notifyBossEmployeeLogOut(Employee employee) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IServiceObserverBoss client = observerMapBoss.get(1);
        if (client != null) {
            executorService.execute(() -> {
                try {
                    client.employeeLogOut(employee);
                } catch (Exception e) {
                    System.out.println("Error on employee log in!");
                }
            });
        }
        executorService.shutdown();
    }

    @Override
    public List<EmployeeAndArrivalTime> getLoggedInEmployees(Boss boss) {
        List<EmployeeAndArrivalTime> employees = (List<EmployeeAndArrivalTime>) employeeAndArrivalTimeIntegerIRepository.findAll();
        List<EmployeeAndArrivalTime> result = new ArrayList<>();
        for (var em : employees) {
            if (observerMap.containsKey(em.getEmployeeId())) {
                result.add(em);
            }
        }
        System.out.println(result);
        return result;
    }

    @Override
    public List<TaskOfEmployeeDTO> getTasksOfEmployeesDTO(Boss boss) {
        List<TaskOfEmployee> taskOfEmployeeList = getAllTasksOfEmployees();
        List<TaskOfEmployeeDTO> taskOfEmployeeDTOList = new ArrayList<>();
        for (var i : taskOfEmployeeList) {
            Employee employee = findEmployeeById(i.getEmployeeId());
            Task task = findTask(i.getTaskId());
            TaskOfEmployeeDTO taskOfEmployeeDTO = new TaskOfEmployeeDTO(employee.getId(), task.getId(), employee.getName(), task.getName(), task.getDeadline(), String.valueOf(i.getTaskStatus()));
            taskOfEmployeeDTOList.add(taskOfEmployeeDTO);
        }
        return taskOfEmployeeDTOList;
    }

    public Task addTask(Task task) {
        return repoTask.add(task);
    }

    public Employee findEmployeeById(Integer id) {
        return repoEmployee.findOne(id);
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

    public void updateTaskOfEmployees(TaskOfEmployee task) {
        IServiceObserverBoss client = observerMapBoss.get(1);
        if (client != null) {
            Employee employee = findEmployeeById(task.getEmployeeId());
            Task task1 = findTask(task.getTaskId());
            TaskOfEmployeeDTO taskOfEmployeeDTO = new TaskOfEmployeeDTO(employee.getId(), task1.getId(), employee.getName(), task1.getName(), task1.getDeadline(), String.valueOf(task.getTaskStatus()));
            repoTaskOfEmployee.update(task);
            System.out.println("TASK STATUS " + taskOfEmployeeDTO.getStatus() + " " + taskOfEmployeeDTO.getTaskId());
            client.updatePerformancesTable(taskOfEmployeeDTO);
        } else {
            System.out.println("Cannot update");
        }
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

    public List<Task> getAllTasks() {
        return (List<Task>) repoTask.findAll();
    }

    public EmployeeAndArrivalTime deleteEmployeeFromWork(Integer id) {
        return employeeAndArrivalTimeIntegerIRepository.delete(id);
    }

    public List<EmployeeAndArrivalTime> getAllEmployeesFromWork() {
        return (List<EmployeeAndArrivalTime>) employeeAndArrivalTimeIntegerIRepository.findAll();
    }

    public List<TaskOfEmployee> getTasksForEmployee(Integer id) {
        return repoTaskOfEmployee.findAllTasksForEmployee(id);
    }

    public EmployeeAndArrivalTime getEmployeeAndArrivalTimeByEmployeeId(Integer id) {
        return employeeAndArrivalTimeIntegerIRepository.findByEmployeeId(id);
    }

    public List<TaskOfEmployee> getAllTasksOfEmployees() {
        return (List<TaskOfEmployee>) repoTaskOfEmployee.findAll();
    }

    public TaskOfEmployee getTaskOfEmployeeByEmployeeIdAndTaskId(Integer employeeId, Integer taskId) {
        return repoTaskOfEmployee.findTaskOfEmployeeByEmployeeIdAndTaskId(employeeId, taskId);
    }

}
