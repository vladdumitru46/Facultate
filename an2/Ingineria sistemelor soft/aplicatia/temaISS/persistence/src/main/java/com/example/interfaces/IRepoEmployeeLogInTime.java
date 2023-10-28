package com.example.interfaces;

import com.example.EmployeeAndArrivalTime;

public interface IRepoEmployeeLogInTime extends IRepository<EmployeeAndArrivalTime, Integer> {

    EmployeeAndArrivalTime findByEmployeeId(Integer id);
}
