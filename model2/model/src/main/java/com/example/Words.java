package com.example;

public class Words extends Entity<Integer> {

    private String word;

    public Words(String word) {
        this.word = word;
    }

    public Words() {
    }

    public String getWord() {
        return word;
    }


    public void setWord(String word) {
        this.word = word;
    }
}
