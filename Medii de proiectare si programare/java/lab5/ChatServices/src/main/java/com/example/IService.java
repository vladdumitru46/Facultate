package com.example;

import java.time.LocalDate;
import java.util.List;

public interface IService {

    void logInEmployee(String email, String password, IServiceObserver client) throws Exception;

    void logOut(EmployeeAtOffice employeeAtOffice) throws Exception;

    List<Show> searchArtistByDate(LocalDate date);

    List<Show> getAllShows(EmployeeAtOffice employeeAtOffice) throws ChatException;

    void sellTickets(String showName, String buyerName, Integer noOFTickets) throws Exception;

    Show findOneShow(String showName);

    void updateShow(Show show) throws Exception;

    EmployeeAtOffice findEmployeeByEmailAndPassword(String email, String password) throws Exception;

}
