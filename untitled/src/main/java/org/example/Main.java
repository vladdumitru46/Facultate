package org.example;


import java.util.Scanner;

public class Main {
    public static String encrypt(String message, Integer key) {
        String newMessage = "";
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) != ' ') {
                int newCharacter = (int) message.charAt(i);
                newCharacter += key;
                char charatcer = (char) newCharacter;
                if (charatcer >= 'a' && charatcer <= 'z') {
                    newMessage += (char) newCharacter;
                } else if (newCharacter > 96) {
                    newCharacter -= key;
                    newCharacter = newCharacter - 26 + 10;
                    newMessage += (char) newCharacter;
                } else {
                    newCharacter += 6;
                    newMessage += (char) newCharacter;
                }
            } else {
                newMessage += message.charAt(i);
            }
        }
        return newMessage;
    }

    public static String decrypt(String message, Integer key) {
        String newMessage = "";
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) != ' ') {
                int newCharacter = (int) message.charAt(i);
                newCharacter -= key;
                char charatcer = (char) newCharacter;
                if (i == 0) {
                    newCharacter -= 6;
                    newMessage += (char) newCharacter;
                } else if (charatcer >= 'a' && charatcer <= 'z') {
                    newMessage += (char) newCharacter;
                } else if (newCharacter < 96) {
                    newCharacter += key;
                    newCharacter = newCharacter + 26 - key;
                    newMessage += (char) newCharacter;
                }
            } else {
                newMessage += message.charAt(i);
            }
        }
        return newMessage;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        Integer key = scanner.nextInt();
        Integer eOrD = scanner.nextInt();

        if(eOrD == 0){
            System.out.println(encrypt(message, key));
        }
        else{
            System.out.println(decrypt(message, key));
        }
    }
}