package com.example;

public class Artist extends Entity<String> {
    private String name;
    private String stageName;
    private Integer age;

    public Artist(String name, String stageName, Integer age) {
        this.name = name;
        this.stageName = stageName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", stageName='" + stageName + '\'' +
                ", age=" + age +
                '}';
    }
}
