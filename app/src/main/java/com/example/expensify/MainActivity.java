package com.example.expensify;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTextDate;
    EditText editTextExpenseName;
    EditText editTextExpenseAmount;
    EditText editTextExpenseLocation;
    Spinner spinnerExpenseCategory;
    SwitchCompat switchExpensePaid;
    Button buttonAddExpense;

    ImageButton buttonCalendar;

    ExpenseViewModel expenseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần trong layout
        editTextDate = findViewById(R.id.editTextDate);
        editTextExpenseName = findViewById(R.id.editTextExpenseName);
        editTextExpenseAmount = findViewById(R.id.editTextExpenseAmount);
        editTextExpenseLocation = findViewById(R.id.editTextExpenseLocation);
        spinnerExpenseCategory = findViewById(R.id.spinnerExpenseCategory);
        switchExpensePaid = findViewById(R.id.switchExpensePaid);
        buttonAddExpense = findViewById(R.id.buttonAddExpense);
        buttonCalendar = findViewById(R.id.buttonCalendar);


        // Khởi tạo ViewModel
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        // Đặt sự kiện click cho nút "Thêm"
        buttonAddExpense.setOnClickListener(view -> {
            // Lấy thông tin từ các trường nhập liệu
            String date = editTextDate.getText().toString();
            String expenseName = editTextExpenseName.getText().toString();
            String expenseAmount = editTextExpenseAmount.getText().toString();
            String expenseLocation = editTextExpenseLocation.getText().toString();
            String expenseCategory = spinnerExpenseCategory.getSelectedItem().toString();
            boolean expensePaid = switchExpensePaid.isChecked();

            // Kiểm tra thông tin nhập liệu
            if (TextUtils.isEmpty(date) || TextUtils.isEmpty(expenseName) ||
                    TextUtils.isEmpty(expenseAmount) || TextUtils.isEmpty(expenseLocation)) {
                Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                // Tạo đối tượng Expense
                Expense expense = new Expense();
                expense.date = date;
                expense.expenseName = expenseName;
                expense.expenseAmount = expenseAmount;
                expense.expenseLocation = expenseLocation;
                expense.expenseCategory = expenseCategory;
                expense.expensePaid = expensePaid;

                // Lưu vào Room Database
                expenseViewModel.insert(expense);

                // Hiển thị thông báo
                Toast.makeText(MainActivity.this, "Đã thêm khoản chi phí: " + expenseName + " tại " + expenseLocation, Toast.LENGTH_SHORT).show();
            }
        });

        buttonCalendar.setOnClickListener(v -> showDatePickerDialog());

    }
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Set selected date to EditText
                        editTextDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },
                year, month, dayOfMonth);
        datePickerDialog.show();
    }
}
