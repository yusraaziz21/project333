package com.example.project3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editSurname;
    Button btnAddData;
    Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().setTitle(" Admin Form");
        myDb = new DatabaseHelper(this);
        editName = (EditText)findViewById(R.id.em);
        editSurname = (EditText)findViewById(R.id.pass);
        btnAddData = (Button)findViewById(R.id.bt);
        btnviewAll = (Button)findViewById(R.id.bt2);
        AddData();
        viewAll();


    }


    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),editSurname.getText().toString());

                        if(isInserted == true)
                            Toast.makeText(admin.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else


                            Toast.makeText(admin.this,"Data Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
// show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("email :"+ res.getString(1)+"\n");
                            buffer.append("password :"+ res.getString(2)+"\n");

                        }

// Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}