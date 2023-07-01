package com.example.temampp.services;

import com.example.temampp.domains.EmployeeAtOffice;
import com.example.temampp.domains.Show;
import com.example.temampp.events.ShowChangeEvent;
import com.example.temampp.observer.Observable;
import com.example.temampp.observer.Observer;
import com.example.temampp.repositoryes.ArtistRepo;
import com.example.temampp.repositoryes.BuyerRepo;
import com.example.temampp.repositoryes.EmployeeRepo;
import com.example.temampp.repositoryes.ShowRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<ShowChangeEvent> {
    private final ArtistRepo artistRepo;
    private final BuyerRepo buyerRepo;
    private final EmployeeRepo employeeRepo;
    private final ShowRepository showRepository;

    private List<Observer<ShowChangeEvent>> observerList = new ArrayList<>();

    public Service(ArtistRepo artistRepo, BuyerRepo buyerRepo, EmployeeRepo employeeRepo, ShowRepository showRepository) {
        this.artistRepo = artistRepo;
        this.buyerRepo = buyerRepo;
        this.employeeRepo = employeeRepo;
        this.showRepository = showRepository;
    }

    public boolean logInEmployee(String email, String password) {
        return employeeRepo.logIn(email, password);
    }

    public List<Show> searchArtistByDate(LocalDate date) {
        return showRepository.searchArtistByDate(date);
    }


    public void sellTicketsToShow(String showName, String buyerName, Integer noOfTicket) {
        buyerRepo.sellTicketsToShow(showName, buyerName, noOfTicket);
    }

    public List<Show> getAllShows() {
        return (List<Show>) showRepository.findAll();
    }

    @Override
    public void addObserver(Observer<ShowChangeEvent> e) {
        observerList.add(e);
    }

    @Override
    public void removeObserver(Observer<ShowChangeEvent> e) {
        observerList.remove(e);
    }

    @Override
    public void notifyObservers(ShowChangeEvent t) {
        this.observerList.forEach(x -> x.update(t));
    }

    public void sellTickets(String showName, String buyerName, Integer noOFTickets) {
        buyerRepo.sellTicketsToShow(showName, buyerName, noOFTickets);
    }

    public Show findOneShow(String showName) {
        return showRepository.findOne(showName);
    }

    public Show updateShow(Show show) {
        return showRepository.update(show);
    }

    public EmployeeAtOffice findEmployeeByEmailAndPassword(String email, String password) {
        return employeeRepo.findOneByEmailAndPassword(email, password);
    }
}
