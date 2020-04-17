package com.example.teamwork_backend.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Task{
    private long id;
    private String content;
    private LocalDateTime updateAt;

    public Task(){

    }
    public Task(Long id ,String content){
        this.id=id;
        this.content=content;
    }
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    public LocalDateTime getUpdatedAt(){
        return updateAt;
    }
    public void setUpdatedAt(){
        this.updateAt=LocalDateTime.now();
    }
    public void setContent(String content){
        this.content=content;
    }
}