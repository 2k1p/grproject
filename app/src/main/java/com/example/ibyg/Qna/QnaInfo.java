package com.example.ibyg.Qna;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class QnaInfo implements Serializable {
    private String title;
    private ArrayList<String> contents;
    private String publisher;
    private Date createdAt;
    private String id;
    private ArrayList<String> request;

    public QnaInfo(String title, ArrayList<String> contents, String publisher, Date createdAt, String id, ArrayList<String> request){
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
        this.createdAt = createdAt;
        this.id = id;
        this.request = request;
    }

    public QnaInfo(String title, ArrayList<String> contents, String publisher, Date createdAt, ArrayList<String> request){
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
        this.createdAt = createdAt;
        this.request = request;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public ArrayList<String> getContents(){ return this.contents; }
    public void setContents(ArrayList<String> contents){
        this.contents = contents;
    }
    public String getPublisher(){
        return this.publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    public Date getCreatedAt(){
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }
    public String getId(){ return this.id; }
    public void setId(String id){
        this.id = id;
    }
    public ArrayList<String> getRequest(){ return this.request; }
    public void setRequest(ArrayList<String> request){
        this.request = request;
    }}