package com.ptechnology.posthandlingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Postinfo {

    @Id
    private Long id;
    private Long userid;
    private String filename;
    private String url;
    private String description;
    private String profilephotourl;
    private String username;

    public Postinfo(Long userid, String filename, String url, String description, String profilephotourl, String username) {
        this.userid = userid;
        this.filename = filename;
        this.url = url;
        this.description = description;
        this.profilephotourl = profilephotourl;
        this.username = username;
    }
}
