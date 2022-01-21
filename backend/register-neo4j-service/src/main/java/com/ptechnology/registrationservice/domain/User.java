package com.ptechnology.registrationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue
    private Long id;
    private Long userid;
    private String email;
    private String username;
    private String password;
    private Boolean ready;

    @Relationship(type = "HAS_ROLE", direction = Relationship.Direction.OUTGOING)
    private Collection<User> roles = new ArrayList<>();
    ///
    @Relationship(type = "SIGNED_TO_BE_FRIENDS", direction = Relationship.Direction.OUTGOING)
    private Collection<User> signedto = new ArrayList<>();

    @Relationship(type = "SIGNED_BY_BE_FRIENDS", direction = Relationship.Direction.INCOMING)
    private Collection<User> signedby = new ArrayList<>();
    ///
    @Relationship(type = "FRIEND", direction = Relationship.Direction.INCOMING)
    public List<User> friends = new ArrayList<>();

    @Relationship(type = "PROFILE_PICTURE", direction = Relationship.Direction.OUTGOING)
    public Profilepicture profilepicture = new Profilepicture();
}