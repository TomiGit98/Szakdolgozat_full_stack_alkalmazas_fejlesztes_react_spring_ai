package com.ptechnology.feedservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Userpostinfo {

    @Id
    @Generated
    private String id;
    private Long userid;
    private List<Postinfo> postinfos = new ArrayList<>();

    public Userpostinfo(Long userid, List<Postinfo> postinfos) {
        this.userid = userid;
        this.postinfos = postinfos;
    }

}
