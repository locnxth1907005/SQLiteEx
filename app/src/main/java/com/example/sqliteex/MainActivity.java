package com.example.sqliteex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sqliteex.database.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edName;
    private EditText edDes;
    private Button btRegister;
    private Spinner spinner;
    private DBHelper db;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView() {
        edName = (EditText) findViewById(R.id.edUser);
        edDes = (EditText) findViewById(R.id.edDes);
        btRegister = (Button) findViewById(R.id.btRegister);
        checkBox = (CheckBox) findViewById(R.id.ck);
        btRegister.setOnClickListener(this);

        String[] genders = {"Male","Female","UNKnow"};
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });

    }

    @Override
    public void onClick(View view) {
    if (view == btRegister){
        onRegister();
     } 
    }

    private void onRegister() {
        if (edName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checkBox.isChecked()){
            Toast.makeText(this, "Please angree rules", Toast.LENGTH_SHORT).show();
            return;
        }
        String isAdd = db.addUSER(edName.getText().toString(),spinner.getSelectedItem().toString(),edDes.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,ListUserActivity.class);
        startActivity(intent);

    }
}