package com.example.lap053;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etEmployeeId, etEmployeeName, etEmployeePhone;
    private Button btnAddEmployee;
    private ListView lvEmployees;

    private ArrayList<String> employeeList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liên kết với giao diện
        etEmployeeId = findViewById(R.id.etEmployeeId);
        etEmployeeName = findViewById(R.id.etEmployeeName);
        etEmployeePhone = findViewById(R.id.etEmployeePhone);
        btnAddEmployee = findViewById(R.id.btnAddEmployee);
        lvEmployees = findViewById(R.id.lvEmployees);

        // Khởi tạo danh sách và adapter
        employeeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        lvEmployees.setAdapter(adapter);

        // Xử lý sự kiện nút Thêm mới
        btnAddEmployee.setOnClickListener(view -> {
            String id = etEmployeeId.getText().toString().trim();
            String name = etEmployeeName.getText().toString().trim();
            String phone = etEmployeePhone.getText().toString().trim();

            if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            String employee = "ID: " + id + " | Name: " + name + " | Phone: " + phone;
            employeeList.add(employee);
            adapter.notifyDataSetChanged();

            // Xóa dữ liệu sau khi thêm
            etEmployeeId.setText("");
            etEmployeeName.setText("");
            etEmployeePhone.setText("");
        });

        // Xử lý sự kiện click để hiển thị thông tin nhân viên
        lvEmployees.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedEmployee = employeeList.get(position);
            String[] details = selectedEmployee.split("\\|");

            etEmployeeId.setText(details[0].replace("ID:", "").trim());
            etEmployeeName.setText(details[1].replace("Name:", "").trim());
            etEmployeePhone.setText(details[2].replace("Phone:", "").trim());
        });

        // Xử lý sự kiện long click để xóa nhân viên
        lvEmployees.setOnItemLongClickListener((adapterView, view, position, id) -> {
            employeeList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Đã xóa nhân viên", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}