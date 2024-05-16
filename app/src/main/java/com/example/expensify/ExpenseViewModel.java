package com.example.expensify;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private ExpenseRepository repository;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        repository = ExpenseRepository.getInstance(application);
    }

    public void insert(Expense expense) {
        repository.insert(expense);
    }

    public List<Expense> getAllExpenses() {
        return repository.getAllExpenses();
    }
}
