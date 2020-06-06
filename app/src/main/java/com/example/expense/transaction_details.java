package com.example.expense;

import java.sql.Timestamp;

public class transaction_details {
    public double amount;
    public String tags;
    public String notes;

    public transaction_details(double amount, String tags, String notes) {
        this.amount = amount;
        this.tags = tags;
        this.notes = notes;
    }

    public double getAmount() {
        return amount;
    }

    public String getTags() {
        return tags;
    }

    public String getNotes() {
        return notes;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
