package com.example;


import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "player")
public class Player extends Entity<Integer> {

    @Column(name = "name")
    private String name;
    @Column(name = "alias")
    private String alias;

    public Player(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
