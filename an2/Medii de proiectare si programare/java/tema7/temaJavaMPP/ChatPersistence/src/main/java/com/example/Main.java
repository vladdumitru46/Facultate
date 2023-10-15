package com.example;

import com.example.repositoryes.ArtistRepo;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
//        EmployeeRepo employeeRepo = new EmployeeRepo(new Properties());
//        EmployeeAtOffice employeeAtOffice = new EmployeeAtOffice("kdsjgfas", "k@yahoo.com", "1234");
//        employeeRepo.add(employeeAtOffice);
//        System.out.println(employeeRepo.findOne(employeeAtOffice.getId()));
//        employeeRepo.delete(employeeAtOffice.getId());
//        System.out.println(employeeRepo.findAll());
        ArtistRepo artistRepo = new ArtistRepo(new Properties());
        Artist artist = new Artist("kkkkkkkk", "a", 22);
        artistRepo.add(artist);
    }
}