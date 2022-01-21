package com.ptechnology.posthandlingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class User {

    public User(Long userid, String email, String username, String password){
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue
    private Long id;
    private Long userid;
    private String email;
    private String username;
    private String password;
    private Boolean ready;

    private String label;

    @JsonIgnoreProperties({"friends", "signedTo", "signedBy"})
    @Relationship(type = "FRIEND")
    public Set<User> friends;

    @JsonIgnoreProperties({"friends", "signedTo", "signedBy"})
    @Relationship(type = "SignedTo")
    public Set<User> signedTo;

    @JsonIgnoreProperties({"friends", "signedTo", "signedBy"})
    @Relationship(type = "SignedBy")
    public Set<User> signedBy;

    @Relationship(type = "PROFILE_PICTURE", direction = Relationship.Direction.OUTGOING)
    public Profilepicture profilepicture = new Profilepicture();

    public void worksWith(User user) {
        if(friends == null) {
            friends = new HashSet<>();
        }
        friends.add(user);
    }

    public Profilepicture getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(Profilepicture profilepicture) {
        this.profilepicture = profilepicture;
    }

    public void signToFriend(User user) {
        if(signedTo == null) {
            signedTo = new HashSet<>();
        }
        signedTo.add(user);
    }

    public void signByFriend(User user) {
        if(signedBy == null) {
            signedBy = new HashSet<>();
        }
        signedBy.add(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userid=" + userid +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ready=" + ready +
                ", label='" + label + '\'' +
                '}';
    }
}
