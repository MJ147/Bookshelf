package com.mja.model;

import java.time.LocalDate;

public class BlogPost {
    private Long id;
    private String title;
    private String author;
    private String content;
    private String dateOfPosted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateOfPosted() {
        return dateOfPosted;
    }

    public void setDateOfPosted(String dateOfPosted) {
        this.dateOfPosted = dateOfPosted;
    }
}
