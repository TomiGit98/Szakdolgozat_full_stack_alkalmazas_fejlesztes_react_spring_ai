package com.ptechnology.posthandlingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

public class UserDetails {

    public UserDetails() {
    }

    public UserDetails(String profilephotourl, String username, String password){
        this.profilephotourl = profilephotourl;
        this.username = username;
    }

    private String profilephotourl;
    private String username;

    public String getProfilephotourl() {
        return profilephotourl;
    }

    public void setProfilephotourl(String profilephotourl) {
        this.profilephotourl = profilephotourl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userid=" + profilephotourl +
                ", username='" + username + '\'' +
                '}';
    }
}
