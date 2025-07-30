package com.erickmendes.workshop_mongodb.dto;

import com.erickmendes.workshop_mongodb.domain.Post;

import java.io.Serializable;
import java.time.Instant;

public class PostDTO implements Serializable {

    private String id;
    private Instant moment;
    private String title;
    private String body;
    private AuthorDTO author;

    public PostDTO(){

    }

    public PostDTO(Post obj){
        id = obj.getId();
        moment = obj.getMoment();
        title = obj.getTitle();
        body = obj.getBody();
        author = obj.getAuthor();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
