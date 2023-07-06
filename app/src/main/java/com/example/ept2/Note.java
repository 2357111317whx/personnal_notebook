package com.example.ept2;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Note extends LitePalSupport {

    private String title;
    private String content;
    private Date time;
    private String author;

    public Note() {
    }

    public Note(String title, String content, Date time, String author) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
