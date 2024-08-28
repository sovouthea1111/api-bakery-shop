package org.example.auth.controller;

import org.example.auth.model.User;
import org.example.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path =  "api")
public class UserController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping(path = "userList")
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> users = userRepo.findAll();

            users.addAll(userRepo.findAll());

            if (users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);

        }catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        Optional<User> userData = userRepo.findById(id);
        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userRepo.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping(path = "/updateUser/{id}")
    public ResponseEntity <User> updateUserId(@RequestBody  long id, @RequestBody User user) {
        Optional<User> oldUserData = userRepo.findById(id);
        if (oldUserData.isPresent()) {
            User updateUserDate = oldUserData.get();

            updateUserDate.setUsername(user.getUsername());
            updateUserDate.setEmail(user.getEmail());
            updateUserDate.setPassword(user.getPassword());

            User newUser = userRepo.save(updateUserDate);

            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public ResponseEntity<HttpStatus>deleteUser(@PathVariable long id) {
        userRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

