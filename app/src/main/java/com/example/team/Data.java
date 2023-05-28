package com.example.team;

public class Data {
    private String date;
    private String body;
    private String sportName;
    private String setNum;
    private String num;
    private boolean okOrNo;

    public Data(String date, String body, String sportName, String setNum, String num, boolean okOrNo ){
        this.date = date;
        this.body = body;
        this.sportName = sportName;
        this.setNum = setNum;
        this.num = num;
        this.okOrNo = okOrNo;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean getOkOrNo() {
        return okOrNo;
    }

    public void setOkOrNo(boolean okOrNo) {
        this.okOrNo = okOrNo;
    }
}
