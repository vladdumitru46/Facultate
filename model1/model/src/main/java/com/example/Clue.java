package com.example;

public class Clue extends Entity<Integer> {
    private Integer positionLine;
    private Integer positionColumn;
    private String textOfClue;

    public Clue(Integer positionLine, Integer positionColumn, String textOfClue) {
        this.positionLine = positionLine;
        this.positionColumn = positionColumn;
        this.textOfClue = textOfClue;
    }

    public Clue() {
    }

    public Integer getPositionLine() {
        return positionLine;
    }

    public void setPositionLine(Integer positionLine) {
        this.positionLine = positionLine;
    }

    public Integer getPositionColumn() {
        return positionColumn;
    }

    public void setPositionColumn(Integer positionColumn) {
        this.positionColumn = positionColumn;
    }

    public String getTextOfClue() {
        return textOfClue;
    }

    public void setTextOfClue(String textOfClue) {
        this.textOfClue = textOfClue;
    }
}
