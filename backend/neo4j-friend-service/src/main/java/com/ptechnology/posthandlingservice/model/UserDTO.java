package com.ptechnology.posthandlingservice.model;

public class UserDTO {

    private Long userid;
    private String email;
    private String username;
    private String label;

    public UserDTO(Long userid, String email, String username, String label){
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.label = label;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
