package com.example.kheetemate;

import android.location.Location;
import android.util.Log;

public class farmer {
    String Id,NaMe,PhoneNuM,StaTe,ProDuct,AddRess,DescripTion,PriCe,ImaGe,CateGory,LaT,LnG,Phone,DisTrict,PiN,CiTy,AdharNum,UpiD;

    public farmer(){
    }

    public farmer(String naMe, String phoneNuM, String staTe, String proDuct, String addRess, String descripTion, String priCe, String imaGe, String cateGory,String laT,String lnG,String id,String phone,String disTrict,String piN,String ciTy,String adharNum,String upiD) {
        NaMe = naMe;
        PhoneNuM = phoneNuM;
        StaTe = staTe;
        ProDuct = proDuct;
        AddRess = addRess;
        DescripTion = descripTion;
        PriCe = priCe;
        ImaGe = imaGe;
        CateGory = cateGory;
        LaT=laT;
        LnG=lnG;
        Id=id;
        Phone=phone;
        DisTrict=disTrict;
        PiN=piN;
        CiTy=ciTy;
        AdharNum=adharNum;
        UpiD=upiD;
    }

    public String getUpiD() {
        return UpiD;
    }

    public void setUpiD(String upiD) {
        UpiD = upiD;
    }

    public String getAdharNum() {
        return AdharNum;
    }

    public void setAdharNum(String adharNum) {
        AdharNum = adharNum;
    }

    public String getCiTy() {
        return CiTy;
    }

    public void setCiTy(String ciTy) {
        CiTy = ciTy;
    }

    public String getPiN() {
        return PiN;
    }

    public void setPiN(String piN) {
        PiN = piN;
    }

    public String getDisTrict() {
        return DisTrict;
    }

    public void setDisTrict(String disTrict) {
        DisTrict = disTrict;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getNaMe() {
        return NaMe;
    }

    public void setNaMe(String naMe) {
        NaMe = naMe;
    }

    public String getPhoneNuM() {
        return PhoneNuM;
    }

    public void setPhoneNuM(String phoneNuM) {
        PhoneNuM = phoneNuM;
    }

    public String getStaTe() {
        return StaTe;
    }

    public void setStaTe(String staTe) {
        StaTe = staTe;
    }

    public String getProDuct() {
        return ProDuct;
    }

    public void setProDuct(String proDuct) {
        ProDuct = proDuct;
    }

    public String getAddRess() {
        return AddRess;
    }

    public void setAddRess(String addRess) {
        AddRess = addRess;
    }

    public String getDescripTion() {
        return DescripTion;
    }

    public void setDescripTion(String descripTion) {
        DescripTion = descripTion;
    }

    public String getPriCe() {
        return PriCe;
    }

    public void setPriCe(String priCe) {
        PriCe = priCe;
    }

    public String getImaGe() {
        return ImaGe;
    }

    public void setImaGe(String imaGe) {
        ImaGe = imaGe;
    }

    public String getCateGory() {
        return CateGory;
    }

    public void setCateGory(String cateGory) {
        CateGory = cateGory;
    }

    public String getLaT() {

        return LaT;
    }

    public void setLaT(String laT) {
        LaT = laT;
    }

    public String getLnG() {
        return LnG;
    }

    public void setLnG(String lnG) {
        LnG = lnG;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    /*public static double disbetween2lonlat(double lat1,double long1,double lat2,double long2){
        Location startPoint=new Location("locationA");
        startPoint.setLatitude();
        startPoint.setLongitude(long1);

        Location endPoint=new Location("locationA");
        endPoint.setLatitude(lat2);
        endPoint.setLongitude(long2);

        double distance=startPoint.distanceTo(endPoint);
        final String qq="dist";
        Log.d(qq, String.valueOf(distance));
        return distance;
    }*/

}
