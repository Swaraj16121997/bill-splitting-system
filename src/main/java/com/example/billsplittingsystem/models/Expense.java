package com.example.billsplittingsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Expense extends BaseModel{

    private String description;
    private int amount;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser> expenseUsers;

    @ManyToOne
    private Group group;

}