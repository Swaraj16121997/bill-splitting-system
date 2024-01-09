package com.example.billsplittingsystem.operations;

public interface Operation {
    void execute(String input);

    // validate
    boolean matches(String input);
}
