package org.example.auth.service;

import org.example.auth.model.User;
import org.example.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    private UserRepo userRepo;

    // get user
    public List<User> findAll() {
        return userRepo.findAll();
    }

    // edit user
    public Optional<User> findById(long id) {
        return userRepo.findById(id);
    }

    // add user
    public User save(User user) {
        return userRepo.save(user);
    }

    // update user
    public User update(User user) {
        return userRepo.save(user);
    }

    // delete user
    public void delete(long id) {
       userRepo.deleteById(id);
    }


}
