package com.example.billsplittingsystem;

import com.example.billsplittingsystem.operations.OperationExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@EnableJpaAuditing
@SpringBootApplication
public class BillSplittingSystemApplication implements CommandLineRunner {

    private OperationExecutor operationExecutor;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public BillSplittingSystemApplication(OperationExecutor operationExecutor) {
        this.operationExecutor = operationExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            String input = scanner.next();
            operationExecutor.execute(input);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BillSplittingSystemApplication.class, args);
    }
}
