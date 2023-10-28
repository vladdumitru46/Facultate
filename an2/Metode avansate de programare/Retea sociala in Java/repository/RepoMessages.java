package com.example.reteasocialagui.example.repository;

import com.example.reteasocialagui.example.domain.Messages;
import com.example.reteasocialagui.example.domain.User;

import java.util.List;

public interface RepoMessages {

    List<Messages> getMessagesBetweenUsers(User sender, User receiver);

    void sendMessage(User sender, User receiver, String message);

    void deleteMessage(User sender, User receiver, String message);

}
