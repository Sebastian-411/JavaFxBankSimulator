package com.example.labjavafx.model;

import java.util.Date;

public class Movement {
    private String description;
    private double amount;
    private MovementType type;

    private Date date;

    public Movement(String description, double amount, MovementType type, Date date) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = new Date();
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public MovementType getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
