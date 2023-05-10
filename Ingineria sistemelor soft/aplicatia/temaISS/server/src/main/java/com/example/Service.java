package com.example;

import com.example.interfaces.IRepoBoss;
import com.example.interfaces.IRepoEmployee;
import com.example.interfaces.IRepository;

public class Service implements IService {

    private final IRepoBoss repoBoss;
    private final IRepoEmployee repoEmployee;
    private final IRepository<Task, Integer> repoTask;
    private final IRepository<TaskOfEmployee, Integer> repoTaskOfEmployee;

    public Service(IRepoBoss repoBoss, IRepoEmployee repoEmployee, IRepository<Task, Integer> repoTask, IRepository<TaskOfEmployee, Integer> repoTaskOfEmployee) {
        this.repoBoss = repoBoss;
        this.repoEmployee = repoEmployee;
        this.repoTask = repoTask;
        this.repoTaskOfEmployee = repoTaskOfEmployee;
    }

    public Boss logInBoss(String email, String password) {
        return repoBoss.findByEmailAndPassword(email, password);
    }

    public Employee logInEmployee(String email, String password) {
        return repoEmployee.findByEmailAndPassword(email, password);
    }

    public Task addTask(Task task) {
        return repoTask.add(task);
    }

    public Task findTask(Integer id) {
        return repoTask.findOne(id);
    }

    public TaskOfEmployee addTaskOfEmployees(TaskOfEmployee task) {
        return repoTaskOfEmployee.add(task);
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
