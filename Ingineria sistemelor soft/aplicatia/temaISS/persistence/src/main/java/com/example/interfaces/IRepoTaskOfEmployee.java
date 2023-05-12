package com.example.interfaces;

import com.example.TaskOfEmployee;

import java.util.List;

public interface IRepoTaskOfEmployee extends IRepository<TaskOfEmployee, Integer> {
    List<TaskOfEmployee> findAllTasksForEmployee(Integer id);
}
