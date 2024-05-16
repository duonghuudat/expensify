package com.example.expensify;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expenses")
public class Expense {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;
    public String expenseName;
    public String expenseAmount;
    public String expenseLocation;
    public String expenseCategory;
    public boolean expensePaid;
}

