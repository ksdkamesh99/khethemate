package com.example.kheetemate;

public class far {
    String NaMe,ProDuct,PriCe,LaT,LnG,LaT1,LnG1;
    String category,address,phone,image,description;
    public far(){
    }

    public far( String naMe,String proDuct,String priCe,String LaT,String LnG,String Lat1,String LnG1,String category,String address,String phone,String image,String description) {
        this.NaMe = naMe;

        this.ProDuct = proDuct;

        this.PriCe = priCe;

        this.LaT=LaT;
        this.LnG=LnG;
        this.LaT1=Lat1;
        this.LnG1=LnG1;
        this.category=category;
        this.address=address;
        this.phone=phone;
        this.image=image;
        this.description=description;
    }



    public String getNaMe() {
        return NaMe;
    }

    public void setNaMe(String naMe) {
        NaMe = naMe;
    }





    public String getProDuct() {
        return ProDuct;
    }

    public void setProDuct(String proDuct) {
        ProDuct = proDuct;
    }





    public String getPriCe() {
        return PriCe;
    }

    public void setPriCe(String priCe) {
        PriCe = priCe;
    }



    public String getLaT() {

        return LaT;
    }

    public String getLnG1() {
        return LnG1;
    }
    public String getLnG() {
        return LnG;
    }
    public String getLaT1() {

        return LaT1;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getImage() {
        return image;
    }


}
