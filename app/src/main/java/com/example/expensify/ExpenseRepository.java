package com.example.expensify;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class ExpenseRepository {
    private static ExpenseRepository instance;
    private ExpenseDao expenseDao;

    private ExpenseRepository(Context context) {
        ExpenseDatabase database = Room.databaseBuilder(context, ExpenseDatabase.class, "expense_db.db").build();
        expenseDao = database.expenseDao();
    }

    public static synchronized ExpenseRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ExpenseRepository(context);
        }
        return instance;
    }

    public void insert(Expense expense) {
        new Thread(() -> expenseDao.insert(expense)).start();
    }

    public List<Expense> getAllExpenses() {
        return expenseDao.getAllExpenses();
    }
}

