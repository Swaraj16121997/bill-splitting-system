package com.example.billsplittingsystem.repositories;

import com.example.billsplittingsystem.models.ExpenseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser,Long> {
}
