package com.example.ibyg.Manager;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class OwnerInfo implements Serializable {

    @Exclude private String id;

    private String editTextName;
    private String editTextAddress;
    private String editTextPhone;
    private String editTexttime;
    private String editTextwifi;
    private String editTextseat;
    private String editTextconsent;
    private String editTextprice;

    public OwnerInfo() {

    }

    public OwnerInfo(String editTextName, String editTextAddress, String editTextPhone, String editTexttime,
                     String editTextwifi, String editTextseat, String editTextconsent, String editTextprice){
        this.editTextName = editTextName;
        this.editTextAddress = editTextAddress;
        this.editTextPhone = editTextPhone;
        this.editTexttime = editTexttime;
        this.editTextwifi = editTextwifi;
        this.editTextseat = editTextseat;
        this.editTextconsent = editTextconsent;
        this.editTextprice  = editTextprice ;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String geteditTextName(){
        return this.editTextName;
    }
    public void seteditTextName(String editTextName){
        this.editTextName = editTextName;
    }

    public String geteditTextAddress(){
        return this.editTextAddress;
    }
    public void seteditTextAddress(String editTextAddress){ this.editTextAddress = editTextAddress; }

    public String geteditTextPhone(){
        return this.editTextPhone;
    }
    public void seteditTextPhone(String editTextPhone){
        this.editTextPhone = editTextPhone;
    }

    public String geteditTexttime(){
        return this.editTexttime;
    }
    public void seteditTexttime(String editTexttime){
        this.editTexttime = editTexttime;
    }

    public String geteditTextwifi(){
        return this.editTextwifi;
    }
    public void seteditTextwifi(String editTextwifi){
        this.editTextwifi = editTextwifi;
    }

    public String geteditTextseat(){
        return this.editTextseat;
    }
    public void seteditTextseat(String editTextseat){
        this.editTextseat = editTextseat;
    }

    public String geteditTextconsent(){
        return this.editTextconsent;
    }
    public void seteditTextconsent(String editTextconsent){ this.editTextconsent = editTextconsent; }

    public String geteditTextprice (){
        return this.editTextprice ;
    }
    public void seteditTextprice(String editTextprice ){
        this.editTextprice  = editTextprice ;
    }}

