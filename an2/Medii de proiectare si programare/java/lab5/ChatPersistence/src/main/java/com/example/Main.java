package com.example;

import com.example.repositoryes.ShowRepository;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        ShowRepository showRepository = new ShowRepository(new Properties());
        System.out.println(showRepository.findAll());
    }
}