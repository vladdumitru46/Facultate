package com.example.reteasocialagui.example.tests;

import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.domain.validators.UserValidator;
import com.example.reteasocialagui.example.domain.validators.Validator;
import com.example.reteasocialagui.example.repository.Repository;
import com.example.reteasocialagui.example.repository.memory.FriendshipRepository;
import com.example.reteasocialagui.example.repository.memory.InMemoryRepository;



public class Tests {
    public void test_add_user() {
        Validator<User> val = new UserValidator();
        Repository<Integer, User> repo = new InMemoryRepository<>(val);
        User user1 = new User("Dumitru", "Vlad");
        repo.save(1, user1);
        User user2 = new User("Dumi", "Vlad");
        repo.save(2, user2);
        User user3 = new User("Dum", "Vlad");
        repo.save(3, user3);
        assert (repo.get_all().size() == 3);
    }

    public void test_delete_user() {
        Validator<User> val = new UserValidator();
        Repository<Integer, User> repo = new InMemoryRepository<>(val);
        User user1 = new User("Dumitru", "Vlad");
        repo.save(1, user1);
        User user2 = new User("Dumi", "Vlad");
        repo.save(2, user2);
        User user3 = new User("Dum", "Vlad");
        repo.save(3, user3);
        assert (repo.get_all().size() == 3);
        repo.delete(user1.getId());
        assert (repo.get_all().size() == 2);

    }

    public void add_friendship() {
        Validator<User> val = new UserValidator();
        Repository<Integer, User> repo = new InMemoryRepository<>(val);
        User user1 = new User("Dumitru", "Vlad");
        repo.save(1, user1);
        User user2 = new User("Dumi", "Vlad");
        repo.save(2, user2);
        User user3 = new User("Dum", "Vlad");
        repo.save(3, user3);
        User user4 = new User("fadfa", "Vdad");
        repo.save(4, user4);
        User user5 = new User("gadg", "Vdagag");
        repo.save(5, user5);
        User user6 = new User("dfasdgadg", "Vaagdgg");
        repo.save(6, user6);

        FriendshipRepository fiends_list = new FriendshipRepository();
        fiends_list.addFriendship(user1, user2);
        fiends_list.addFriendship(user1, user3);
        fiends_list.addFriendship(user1, user4);
        fiends_list.addFriendship(user2, user5);
        fiends_list.addFriendship(user3, user2);

        assert (fiends_list.showFriends(1).size() == 3);
    }

    public void test_remove_friendship() {
        Validator<User> val = new UserValidator();
        Repository<Integer, User> repo = new InMemoryRepository<>(val);
        User user1 = new User("Dumitru", "Vlad");
        repo.save(1, user1);
        User user2 = new User("Dumi", "Vlad");
        repo.save(2, user2);
        User user3 = new User("Dum", "Vlad");
        repo.save(3, user3);
        User user4 = new User("fadfa", "Vdad");
        repo.save(4, user4);
        User user5 = new User("gadg", "Vdagag");
        repo.save(5, user5);
        User user6 = new User("dfasdgadg", "Vaagdgg");
        repo.save(6, user6);

        FriendshipRepository fiends_list = new FriendshipRepository();
        fiends_list.addFriendship(user1, user2);
        fiends_list.addFriendship(user1, user3);
        fiends_list.addFriendship(user1, user4);
        fiends_list.addFriendship(user2, user5);
        fiends_list.addFriendship(user3, user2);

        assert fiends_list.showFriends(2).size() == 2;

        fiends_list.removeFriendships(user1, user2);
        assert (fiends_list.showFriends(1).size() == 2);
        assert (fiends_list.showFriends(2).size() == 1);
    }

    private void test_number_of_communities() {
        Validator<User> val = new UserValidator();
        Repository<Integer, User> repo = new InMemoryRepository<>(val);
        User user1 = new User("Dumitru", "Vlad");
        repo.save(1, user1);
        User user2 = new User("Dumi", "Vlad");
        repo.save(2, user2);
        User user3 = new User("Dum", "Vlad");
        repo.save(3, user3);
        User user4 = new User("fadfa", "Vdad");
        repo.save(4, user4);
        User user5 = new User("gadg", "Vdagag");
        repo.save(5, user5);
        User user6 = new User("dfasdgadg", "Vaagdgg");
        repo.save(6, user6);

        FriendshipRepository fiends_list = new FriendshipRepository();
        fiends_list.addFriendship(user1, user4);
        fiends_list.addFriendship(user2, user5);
        fiends_list.addFriendship(user3, user2);


    }

    public void run_tests() {
        System.out.println("Start teste!\n");
        test_add_user();
        test_delete_user();
        add_friendship();
        test_remove_friendship();
        test_number_of_communities();
        System.out.println("Finish teste!\n");
    }


}
