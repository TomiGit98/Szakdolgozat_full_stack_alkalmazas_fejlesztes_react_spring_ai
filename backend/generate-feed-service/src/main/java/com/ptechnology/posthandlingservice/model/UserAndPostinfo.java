package com.ptechnology.posthandlingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAndPostinfo {

    User user;
    Postinfo postinfo;

    public String toString() {
        return user.getUsername() + " " + user.getEmail() + " " + user.getPassword() + " " + user.getFriends().toString();
    }
}
