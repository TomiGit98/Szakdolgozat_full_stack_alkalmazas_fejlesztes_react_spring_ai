package com.ptechnology.posthandlingservice.model;

import java.util.ArrayList;
import java.util.List;

public class UserReturn {

    List<UserDTO> friends;
    List<UserDTO> signedTo;
    List<UserDTO> signedBy;
    List<UserDTO> users;

    public UserReturn(){
        this.friends = new ArrayList<>();
        this.signedBy = new ArrayList<>();
        this.signedTo = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public List<UserDTO> getFriends() {
        return friends;
    }

    public void setFriends(List<UserDTO> friends) {
        this.friends = friends;
    }

    public List<UserDTO> getSignedTo() {
        return signedTo;
    }

    public void setSignedTo(List<UserDTO> signedTo) {
        this.signedTo = signedTo;
    }

    public List<UserDTO> getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(List<UserDTO> signedBy) {
        this.signedBy = signedBy;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
