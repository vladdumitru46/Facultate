package com.example.reteasocialagui.example.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UserFriendsFrom {

    private Integer id;
    private String firstName;
    private String lastName;

    private LocalDateTime time;

    public UserFriendsFrom(Integer id, String firstName, String lastName, LocalDateTime time) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getTime() {
        return time;
    }


    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() +
                ",  Friends from: " + time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}