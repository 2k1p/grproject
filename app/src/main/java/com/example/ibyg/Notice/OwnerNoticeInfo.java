package com.example.ibyg.Notice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
//공지사항 제목, 내용을 받아오고, 보낼 정보들
public class OwnerNoticeInfo implements Serializable {
    private String title;
    private ArrayList<String> contents;
    private String publisher;
    private Date createdAt;
    private String id;

    public OwnerNoticeInfo(String title, ArrayList<String> contents, String publisher, Date createdAt, String id){
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
        this.createdAt = createdAt;
        this.id = id;
    }

    public OwnerNoticeInfo(String title, ArrayList<String> contents, String publisher, Date createdAt){
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
        this.createdAt = createdAt;
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
    }}