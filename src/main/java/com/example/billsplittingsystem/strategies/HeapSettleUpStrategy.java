package com.example.billsplittingsystem.strategies;

import com.example.billsplittingsystem.models.*;
import com.example.billsplittingsystem.models.Record;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy {     // generally finding minimum no. of transactions is always an NP-hard problem (i.e, nondeterministic polynomial time)

    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        Map<User, Integer> extraAmount = new HashMap<>();
        List<Transaction> transactions = new ArrayList<>();
        // Map to store extra amount to be paid to each use

        /*
        A PaidBy 1000
        B Had to Pay 500

        E1
        E2
        E2

        Extra Money
        A = 1000
        B = -500
         */

        // Extra MONEY MAP Creation
        for (Expense expense: expenses) {
            for (ExpenseUser expenseUser : expense.getExpenseUsers().stream().filter(expenseUser -> expenseUser.getExpenseUserType().equals(ExpenseUserType.PAID_BY)).collect(Collectors.toList())) {
                if (!extraAmount.containsKey(expenseUser.getUser())) {
                    extraAmount.put(expenseUser.getUser(), 0);
                }
                extraAmount.put(expenseUser.getUser(), extraAmount.get(expenseUser.getUser()) + expenseUser.getAmount());
            }
            for (ExpenseUser expenseUser : expense.getExpenseUsers().stream().filter(expenseUser -> expenseUser.getExpenseUserType().equals(ExpenseUserType.HAD_TO_PAY)).collect(Collectors.toList())) {
                if (!extraAmount.containsKey(expenseUser.getUser())) {
                    extraAmount.put(expenseUser.getUser(), 0);
                }
                extraAmount.put(expenseUser.getUser(), extraAmount.get(expenseUser.getUser()) - expenseUser.getAmount());
            }
        }
        /*
        A = 100
        B = -10
        C = -90

        A = 100


       B = -10
       C = -90
         */

        Queue<Record> negativeQueue = new PriorityQueue<>();
        // This queue will contain the amount had to pay by the users.

        Queue<Record> positiveQueue = new PriorityQueue<>();
        // This queue will contain the amount paid extra by the users.

        // For loop to fill the queue based on the extra amount map.
        for (User user: extraAmount.keySet()) {
            if (extraAmount.get(user) > 0) {
                positiveQueue.add(new Record(user, extraAmount.get(user)));
            } else {
                negativeQueue.add(new Record(user, extraAmount.get(user)));
            }
        }

        // create transaction list.
        while (!positiveQueue.isEmpty() && !negativeQueue.isEmpty()) {
            Record firstNegative = negativeQueue.remove(); // user paid lesser.
            Record firstPositive = positiveQueue.remove(); // user paid extra.

            if (firstPositive.getAmount() > Math.abs(firstNegative.getAmount())) {
                // 1000  > -500
                // B -> A 500
                int pendingAmount = firstPositive.getAmount() - Math.abs(firstNegative.getAmount());
                transactions.add(
                        new Transaction(firstNegative.getUser(), firstPositive.getUser(), Math.abs(firstNegative.getAmount()))
                );
                // A -> 1000-500 = 500
                positiveQueue.add(new Record(firstPositive.getUser(), pendingAmount));
            } else {
                // 500 > -1000
                int pendingAmount = Math.abs(firstNegative.getAmount()) - firstPositive.getAmount();
                transactions.add(
                        new Transaction(firstNegative.getUser(), firstPositive.getUser(), firstPositive.getAmount())
                );
                negativeQueue.add(new Record(firstNegative.getUser(), firstNegative.getAmount() + firstPositive.getAmount()));
            }
        }
        return transactions;
    }
}
