package com.example.billsplittingsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Entity
public class Record extends BaseModel{
    @ManyToOne
    private User user;
    private int amount;

    @Autowired
    public Record(User user, int amount){
        this.user = user;
        this.amount = amount;
    }
}
