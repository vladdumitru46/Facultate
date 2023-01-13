package com.example.reteasocialagui.example.repository.file;

import com.example.reteasocialagui.example.domain.Friendship;
import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.repository.memory.FriendshipRepository;


import java.io.*;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractFriendshipRepository extends FriendshipRepository {

    String fileName;

    public AbstractFriendshipRepository(String fileName) {
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            super.getAll().clear();
            while ((line = bufferedReader.readLine()) != null) {
                List<String> attr = Arrays.asList(line.split(","));
                Friendship e = extractEntity(attr);
                super.addFriendship(e.getUser1(), e.getUser2());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract Friendship extractEntity(List<String> attributes);

    protected abstract String createEntityAsString(Friendship entity);

    protected void writeToFile(Friendship entity) {
        try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileName, true))) {
            bW.write(createEntityAsString(entity));
            bW.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFriendship(User user1, User user2) {
        super.addFriendship(user1, user2);
        File file = new File(fileName);
        try (PrintWriter pw = new PrintWriter(file)) {
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        for (int i = 0; i < super.getAll().size(); i += 2) {
            Friendship friendship = new Friendship(super.getAll().get(i).getUser1(), super.getAll().get(i).getUser2(),
                    super.getAll().get(i).getTime());
            writeToFile(friendship);
        }
    }

    public void removeFriendships(User user1, User user2) {
        super.removeFriendships(user1, user2);
        File file = new File(fileName);
        try (PrintWriter pw = new PrintWriter(file)) {
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        for (int i = 0; i < super.getAll().size(); i += 2) {
            Friendship friendship = new Friendship(super.getAll().get(i).getUser1(), super.getAll().get(i).getUser2(),
                    super.getAll().get(i).getTime());
            writeToFile(friendship);
        }
    }
}
