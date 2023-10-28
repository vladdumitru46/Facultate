package com.example.reteasocialagui.example.repository.file;

import com.example.reteasocialagui.example.domain.Friendship;
import com.example.reteasocialagui.example.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public class FriendshipFile extends AbstractFriendshipRepository {


    public FriendshipFile(String fileName) {
        super(fileName);
    }

    @Override
    public Friendship extractEntity(List<String> attributes) {
        User user1 = new User(attributes.get(1), attributes.get(2));
        user1.setId(Integer.parseInt(attributes.get(0)));
        User user2 = new User(attributes.get(4), attributes.get(5));
        user2.setId(Integer.parseInt(attributes.get(3)));
        LocalDateTime time = LocalDateTime.parse(attributes.get(6));
        Friendship friendship = new Friendship(user1, user2, time);
        return friendship;
    }

    @Override
    protected String createEntityAsString(Friendship entity) {
        return entity.getUser1().getId() + "," + entity.getUser1().getFirstName() + "," + entity.getUser1().getLastName() + "," +
                entity.getUser2().getId() + "," + entity.getUser2().getFirstName() + "," + entity.getUser2().getLastName() + "," +
                entity.getTime();
    }

}
