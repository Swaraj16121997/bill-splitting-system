package com.example.billsplittingsystem.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OperationExecutor {
    private List<Operation> operations = new ArrayList<>();

    @Autowired
    public OperationExecutor(SettleUpUser settleUpUser) {
        operations.add(settleUpUser);
    }

    public void addOperation(Operation operation) {
        operations.add(operation);

    }

    public void removeOperation(Operation operation) {
        operations.remove(operation);
    }

    public void execute(String input) {
        for (Operation operation: operations) {
            if (operation.matches(input)) {
                operation.execute(input);
                break;
            }
        }
    }
}
