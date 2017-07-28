package com.example.a15017470.ompm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    EditText etName, etAmount, etDate, etNumber;
    Button btnAdd;
    ListView lv;
    ArrayList<Data> al;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Person's details");

        etName=(EditText)findViewById(R.id.etName);
        etAmount=(EditText)findViewById(R.id.etAmount);
        etDate=(EditText)findViewById(R.id.etDate);
        etNumber=(EditText)findViewById(R.id.etNumber);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        lv = (ListView)findViewById(R.id.lv);
        al = new ArrayList<Data>();

        DBHelper db = new DBHelper(MainActivity.this);

        al = db.getData();
        db.close();

        for (int i=0; i<al.size(); i++) {
            aa = new CustomAdapter(MainActivity.this, R.layout.row, al);
        }

        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Intent i = new Intent(MainActivity.this, InputActivity.class);
                int id = al.get(position).getId();
                String name = al.get(position).getName();
                String amount = al.get(position).getAmount();
                String date = al.get(position).getDate();
                String number = al.get(position).getNumber();

                Data target = new Data(id, name, amount, date, number);
                i.putExtra("data", target);
                startActivityForResult(i, 9);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String amount = etAmount.getText().toString();
                String date = etDate.getText().toString();
                String number = etNumber.getText().toString();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long row_affected = dbh.insert(name, amount, date, number);
                retrieve();

                if (row_affected != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            retrieve();
        }
    }

    protected void retrieve(){
        DBHelper dbh = new DBHelper(MainActivity.this);
        al.clear();
        al.addAll(dbh.getData());
        dbh.close();
        aa.notifyDataSetChanged();
    }
}
