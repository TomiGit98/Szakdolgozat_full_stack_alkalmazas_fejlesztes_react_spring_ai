package com.ptechnology.aiauthservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Authinfo {

    @Id
    private Long id;
    private Long userid;
    private String filename;
    private String url;

    public Authinfo(Long userid, String filename, String url) {
        this.userid = userid;
        this.filename = filename;
        this.url = url;
    }
}
