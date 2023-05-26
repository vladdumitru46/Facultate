package com.example;

import com.example.repository.RepoBoss;

public class Main {
    public static void main(String[] args) {
        RepoBoss repoBoss = new RepoBoss();
        System.out.println(repoBoss.findByEmailAndPassword("gigel@yahoo.com", "1234"));
    }
}