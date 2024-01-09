package com.example.billsplittingsystem.strategies;

import com.example.billsplittingsystem.models.Expense;
import com.example.billsplittingsystem.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettleUpStrategy {

    List<Transaction> settleUp(List<Expense> expensesToSettle);
}

// Settle - Bunch of expenses
// User - expenses
// group - expenses
