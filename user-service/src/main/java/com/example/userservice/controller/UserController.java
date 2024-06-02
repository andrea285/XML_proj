package com.example.userservice.controller;

import com.example.userservice.model.Follow;
import com.example.userservice.model.User;
import com.example.userservice.repository.FollowRepository;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setProfilePicture(updatedUser.getProfilePicture());
            user.setBio(updatedUser.getBio());
            user.setMotto(updatedUser.getMotto());
            return userRepository.save(user);
        }).orElseGet(() -> {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userRepository.findByRole(role);
    }

    @PostMapping("/{id}/follow/{followeeId}")
    public Follow followUser(@PathVariable String id, @PathVariable String followeeId) {
        Follow follow = new Follow();
        follow.setFollowerId(id);
        follow.setFolloweeId(followeeId);
        return followRepository.save(follow);
    }

    @DeleteMapping("/{id}/unfollow/{followeeId}")
    public void unfollowUser(@PathVariable String id, @PathVariable String followeeId) {
        followRepository.deleteByFollowerIdAndFolloweeId(id, followeeId);
    }

    @GetMapping("/{id}/followers")
    public List<Follow> getFollowers(@PathVariable String id) {
        return followRepository.findByFolloweeId(id);
    }

    @GetMapping("/{id}/following")
    public List<Follow> getFollowing(@PathVariable String id) {
        return followRepository.findByFollowerId(id);
    }
}
