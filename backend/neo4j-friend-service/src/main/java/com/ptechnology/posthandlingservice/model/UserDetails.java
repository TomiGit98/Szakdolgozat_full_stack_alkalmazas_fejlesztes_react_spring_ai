package com.ptechnology.posthandlingservice.model;

public class UserDetails {

    public UserDetails() {
    }

    public UserDetails(String profilephotourl, String username){
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
