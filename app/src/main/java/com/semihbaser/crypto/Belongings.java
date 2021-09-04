package com.semihbaser.crypto;

import java.io.Serializable;

public class Belongings implements Serializable {

    public String email;
    public String name;
    public String name2;
    public String price;
    public String downloadUrl;
    public String info;
    public String documentId;

    public Belongings(String email, String name,String name2, String price, String downloadUrl, String info, String documentId) {
        this.email = email;
        this.name = name;
        this.name2= name2;
        this.price = price;
        this.downloadUrl = downloadUrl;
        this.info = info;
        this.documentId = documentId;
    }
}
