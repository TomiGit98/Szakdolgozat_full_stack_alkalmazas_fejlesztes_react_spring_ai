package com.ptechnology.posthandlingservice.controller;

import com.ptechnology.posthandlingservice.model.User;
import com.ptechnology.posthandlingservice.model.UserDTO;
import com.ptechnology.posthandlingservice.model.UserDetails;
import com.ptechnology.posthandlingservice.model.UserReturn;
import com.ptechnology.posthandlingservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {

    private final UserRepository userRepository;

    public FriendController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @PutMapping("/signfriend")
    public ResponseEntity addFriend(@RequestHeader("CUSTOM-REQUEST-HEADER") String myId, @RequestBody String friendId) {
        System.out.println("Friend id: " + friendId);
        System.out.println("My id: " + myId);

        User myUser = userRepository.findByUseridEquals(Long.parseLong(myId));
        User friendUser = userRepository.findByUseridEquals(Long.parseLong(friendId));

        myUser.signToFriend(friendUser);
        userRepository.save(myUser);

        friendUser.signByFriend(myUser);
        userRepository.save(friendUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/acceptfriendrequest")
    public ResponseEntity acceptRequest(@RequestHeader("CUSTOM-REQUEST-HEADER") String myId, @RequestBody String friendId) {
        System.out.println("Friend id: " + friendId);
        System.out.println("My id: " + myId);

        User myUser = userRepository.findByUseridEquals(Long.parseLong(myId));
        User friendUser = userRepository.findByUseridEquals(Long.parseLong(friendId));

        System.out.println("SignedBy");
        myUser.signedBy.stream().forEach(c -> {
            if(c.getUserid() == friendUser.getUserid()){
                myUser.signedBy.remove(c);
            }
            System.out.println(c.toString());
        });

        System.out.println("SignedTo");
        myUser.signedTo.stream().forEach(c -> {
            System.out.println(c.toString());
        });
        myUser.friends.add(friendUser);
        userRepository.save(myUser);

        System.out.println("------------------------------------------------------------------");
        System.out.println("SignedBy");
        friendUser.signedBy.stream().forEach(c -> {
            System.out.println(c.toString());
        });
        System.out.println("SignedTo");
        friendUser.signedTo.stream().forEach(c -> {
            if(c.getUserid() == myUser.getUserid()){
                friendUser.signedTo.remove(c);
            }
            System.out.println(c.toString());
        });
        friendUser.friends.add(myUser);
        userRepository.save(friendUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getfriends/all/{myId}")
    public List<User> getAllUserByUserId(@PathVariable("myId") String myId) {
        User myUser = userRepository.findByUseridEquals(Long.parseLong(myId));

        if(myUser != null){
            List<User> friends = userRepository.findByFriendsUsername(myUser.getUsername());
            friends.stream().forEach(u -> System.out.println(u.getUsername()));
            return friends;
        }
        return new ArrayList<>();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/friends/all")
    public UserReturn allUser(@RequestHeader("CUSTOM-REQUEST-HEADER") String myId) {
        System.out.println(myId);
        User myUser = userRepository.findByUseridEquals(Long.parseLong(myId));

        List<User> users = userRepository.findAll();

        UserReturn userReturn = new UserReturn();

        if(users != null && myUser != null) {

            List<Long> friendIds = new ArrayList<>();
            List<Long> signedToIds = new ArrayList<>();
            List<Long> signedByIds = new ArrayList<>();

            myUser.friends.stream().forEach(c -> {
                friendIds.add(c.getUserid());
            });

            myUser.signedTo.stream().forEach(c -> {
                signedToIds.add(c.getUserid());
            });

            myUser.signedBy.stream().forEach(c -> {
                signedByIds.add(c.getUserid());
            });


            users.stream().forEach(u -> {
                if(u.getUserid() == myUser.getUserid()){
                    System.out.println("It is me");
                } else if(friendIds.contains(u.getUserid())){
                    System.out.println("Friend of mine");
                    userReturn.getFriends().add(new UserDTO(u.getUserid(), u.getEmail(), u.getUsername(), "Already Friend"));
                } else if(signedToIds.contains(u.getUserid())){
                    System.out.println("Signed to be friend");
                    userReturn.getSignedTo().add(new UserDTO(u.getUserid(), u.getEmail(), u.getUsername(), "Signed to be friends"));
                } else if(signedByIds.contains(u.getUserid())){
                    System.out.println("Signed by to be friend");
                    userReturn.getSignedBy().add(new UserDTO(u.getUserid(), u.getEmail(), u.getUsername(), "Signed by to be friends"));
                } else {
                    System.out.println("User");
                    userReturn.getUsers().add(new UserDTO(u.getUserid(), u.getEmail(), u.getUsername(), "Add to friend"));
                }
            });

            System.out.println("UserNot found");

            return userReturn;
        }
        return userReturn;
    }

    @GetMapping("/getuserdetails/{id}")
    public UserDetails getUserDetails(@PathVariable("id") String myId) {
        System.out.println("Get user details!");

        User myUser = userRepository.findByUseridEquals(Long.parseLong(myId));
        if(myUser != null){
            return new UserDetails(myUser.getProfilepicture().getUrl(), myUser.getUsername());
        } else {
            return new UserDetails();
        }
    }

    @GetMapping("/getfriends")
    public User getMyFriends(@RequestHeader("CUSTOM-REQUEST-HEADER") String myId) {
        System.out.println("Get my friends!");

        User myUser = userRepository.findByUseridEquals(Long.parseLong(myId));
        if(myUser != null){
            return myUser;
        } else {
            return null;
        }
    }

}
