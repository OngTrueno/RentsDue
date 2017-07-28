package com.example.a15017470.ompm;

import java.io.Serializable;

/**
 * Created by 15017470 on 20/7/2017.
 */

public class Data implements Serializable {
    private int id;
    private String name;
    private String amount;
    private String date;
    private String number;

    public Data(int id, String name, String amount, String date, String number) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.number = number;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
