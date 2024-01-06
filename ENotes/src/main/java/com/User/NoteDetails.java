package com.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NoteDetails {
    private int id;
    private String title;
    private String content;
    private LocalDateTime date;
    private UserDetails user;

    public NoteDetails() {
    }

    public NoteDetails(int id, String title, String content, LocalDateTime date, UserDetails user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public NoteDetails(NoteDetails note) {
        this.id = note.id;
        this.title = note.title;
        this.content = note.content;
        this.date = note.date;
        this.user = note.user;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDateFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");
        String formattedDateTime = date.format(formatter);
        return formattedDateTime;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "NoteDetails{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
