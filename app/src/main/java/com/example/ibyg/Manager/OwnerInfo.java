package com.example.ibyg.Manager;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

//카페 정보를 받아오고, 보내줄 정보들
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

    private String editCoffee1;
    private String editPrice1;
    private String editCoffee2;
    private String editPrice2;
    private String editCoffee3;
    private String editPrice3;
    private String editCoffee4;
    private String editPrice4;

    private String photoUrl;

    public OwnerInfo(){

    }

    public OwnerInfo(String editTextName, String editTextAddress, String editTextPhone, String editTexttime,
                     String editTextwifi, String editTextseat, String editTextconsent, String editTextprice,
                     String editCoffee1, String editPrice1, String editCoffee2, String editPrice2,
                     String editCoffee3, String editPrice3, String editCoffee4, String editPrice4, String photoUrl){
        this.editTextName = editTextName;
        this.editTextAddress = editTextAddress;
        this.editTextPhone = editTextPhone;
        this.editTexttime = editTexttime;
        this.editTextwifi = editTextwifi;
        this.editTextseat = editTextseat;
        this.editTextconsent = editTextconsent;
        this.editTextprice  = editTextprice ;

        this.editCoffee1  = editCoffee1 ;
        this.editPrice1  = editPrice1 ;
        this.editCoffee2  = editCoffee2 ;
        this.editPrice2  = editPrice2 ;
        this.editCoffee3  = editCoffee3 ;
        this.editPrice3  = editPrice3 ;
        this.editCoffee4  = editCoffee4 ;
        this.editPrice4  = editPrice4 ;
        this.photoUrl = photoUrl;
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
    }


    public String geteditCoffee1(){
        return this.editCoffee1;
    }
    public void seteditCoffee1(String editCoffee1){ this.editCoffee1 = editCoffee1;}

    public String geteditPrice1(){
        return this.editPrice1;
    }
    public void seteditPrice1(String editPrice1){ this.editPrice1 = editPrice1;}

    public String geteditCoffee2(){
        return this.editCoffee2;
    }
    public void seteditCoffee2(String editCoffee2){ this.editCoffee2 = editCoffee2;}

    public String geteditPrice2(){
        return this.editPrice2;
    }
    public void seteditPrice2(String editPrice2){ this.editPrice2 = editPrice2;}

    public String geteditCoffee3(){
        return this.editCoffee3;
    }
    public void seteditCoffee3(String editCoffee3){ this.editCoffee3 = editCoffee3; }

    public String geteditPrice3(){
        return this.editPrice3;
    }
    public void seteditPrice3(String editPrice3){ this.editPrice3 = editPrice3;}

    public String geteditCoffee4(){
        return this.editCoffee4;
    }
    public void seteditCoffee4(String editCoffee4){ this.editCoffee4 = editCoffee4;}

    public String geteditPrice4(){
        return this.editPrice4;
    }
    public void seteditPrice4(String editPrice4){ this.editPrice4 = editPrice4;}

    public String getPhotoUrl(){ return this.photoUrl; }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }}

