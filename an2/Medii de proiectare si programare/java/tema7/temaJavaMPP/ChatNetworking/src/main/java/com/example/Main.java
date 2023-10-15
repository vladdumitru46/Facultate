package com.example;

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        for (int i = 2; i < list.size() * 2; list.stream().skip(2).limit(3)) {
            System.out.println(list.get(i));
        }
    }
}