package com.example.billsplittingsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Entity
public class Transaction extends BaseModel{
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    private int amount;

    @Autowired
    public Transaction(User sender, User receiver, int amount){
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
}
