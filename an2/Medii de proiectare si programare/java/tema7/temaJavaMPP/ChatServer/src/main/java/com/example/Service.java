package com.example;


import com.example.repositoryes.interfaces.RepoBuyer;
import com.example.repositoryes.interfaces.RepoEmployee;
import com.example.repositoryes.interfaces.RepoShow;
import com.example.repositoryes.interfaces.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {
    private final Repository<String, Artist> artistRepo;
    private final RepoBuyer buyerRepo;
    private final RepoEmployee employeeRepo;
    private final RepoShow showRepository;

    private final Map<Integer, IServiceObserver> observerMap;

    public Service(Repository<String, Artist> artistRepo, RepoBuyer buyerRepo, RepoEmployee employeeRepo, RepoShow showRepository) {
        this.artistRepo = artistRepo;
        this.buyerRepo = buyerRepo;
        this.employeeRepo = employeeRepo;
        this.showRepository = showRepository;
        observerMap = new ConcurrentHashMap<>();
    }

    public synchronized void logInEmployee(String email, String password, IServiceObserver client) throws Exception {
        if (!employeeRepo.logIn(email, password)) {
            throw new Exception("Can't log in!");
        } else {
            EmployeeAtOffice employeeAtOffice = employeeRepo.findOneByEmailAndPassword(email, password);
            observerMap.put(employeeAtOffice.getId(), client);
        }
    }


    @Override
    public void logOut(EmployeeAtOffice employeeAtOffice) throws Exception {
        IServiceObserver localClient = observerMap.remove(employeeAtOffice.getId());
        if (localClient == null) {
            throw new Exception("Employee" + employeeAtOffice.getName() + "is not logged in!\n");
        }
    }

    public List<Show> searchArtistByDate(LocalDate date) {
        return showRepository.searchArtistByDate(date);
    }

    public List<Show> getAllShows(EmployeeAtOffice employeeAtOffice) {
        return (List<Show>) showRepository.findAll();
    }


    public synchronized void sellTickets(String showName, String buyerName, Integer noOFTickets) throws Exception {
        System.out.println("Observer map: " + observerMap.size());
        Show show = findOneShow(showName);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        if (noOFTickets < show.getNoTicketsAvailable()) {
            buyerRepo.sellTicketsToShow(showName, buyerName, noOFTickets);
            for (var iServiceObserver : observerMap.values())
                if (iServiceObserver != null) {
                    executor.execute(() -> {
                        try {
                            System.out.println("modifca pt employee: " + iServiceObserver + "trimite datele: " + showName + " " + noOFTickets);
                            iServiceObserver.ticketSold(new Buyers(buyerName, noOFTickets, showName));
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    });
                }
            executor.shutdown();
        } else {
            throw new Exception("Not enough ticktes for this show!\n");
        }
    }

    public Show findOneShow(String showName) {
        return showRepository.findOne(showName);
    }

    public void updateShow(Show show) {
        showRepository.update(show);
    }

    public EmployeeAtOffice findEmployeeByEmailAndPassword(String email, String password) {
        return employeeRepo.findOneByEmailAndPassword(email, password);
    }

    public EmployeeAtOffice findEmployeeById(Integer id) {
        return employeeRepo.findOne(id);
    }
}
