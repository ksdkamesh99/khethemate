package com.example.kheetemate;

import com.google.android.gms.maps.model.LatLng;

public class Myordercos {
    private String fname,product,quantity,price,phno;
    LatLng sf;
    public Myordercos() {
    }

    public Myordercos(String fname,String product,String quantity,String price,String phno,LatLng sf) {
        this.fname = fname;
        this.product = product;
        this.quantity= quantity;


        this.price=price;
        this.phno=phno;
        this.sf=sf;

    }

    public String getfname() {
        return fname;
    }
    public String getproduct() {
        return product;
    }
    public String getquantity() {
        return quantity;
    }
    public String getprice() {
        return price;
    }
    public String getphno() {
        return phno;
    }
    public LatLng getlatlngsf() {
        return sf;
    }
}
