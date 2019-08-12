package com.example.ibyg;

import android.widget.EditText;

public class Memberinfo {
    private String name;
    private String phoneNumber;
    private String birthEdit;
    private String addrEdit;

    public Memberinfo(String name, String phoneNumber, String birthEdit, String addrEdit){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthEdit = birthEdit;
        this.addrEdit = addrEdit;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;

    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;

    }

    public String getBirthEdit(){
        return this.birthEdit;
    }
    public void setBirthEdit(String birthEdit){
        this.birthEdit = birthEdit;

    }

    public String getAddrEdit(){
        return this.addrEdit;
    }
    public void setAddrEdit(String addrEdit){
        this.addrEdit = addrEdit;

    }

}
