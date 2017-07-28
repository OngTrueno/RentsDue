package com.example.a15017470.ompm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    TextView tvId;
    EditText etName, etAmount, etDate, etNumber;
    Button btnUpdate, btnDelete, btnSMS;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Person's details");

        tvId=(TextView)findViewById(R.id.tvId);
        etName=(EditText)findViewById(R.id.etName);
        etAmount=(EditText)findViewById(R.id.etAmount);
        etDate=(EditText)findViewById(R.id.etDate);
        etNumber=(EditText)findViewById(R.id.etNumber);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnSMS=(Button)findViewById(R.id.btnSMS);

        DBHelper dbh = new DBHelper(InputActivity.this);

        Intent i = getIntent();
        data = (Data) i.getSerializableExtra("data");

        tvId.setText("ID: " + data.getId());
        etName.setText(data.getName());
        etAmount.setText(data.getAmount());
        etDate.setText(data.getDate());
        etNumber.setText(data.getNumber());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(InputActivity.this);
                data.setName(etName.getText().toString());
                data.setAmount(etAmount.getText().toString());
                data.setDate(etDate.getText().toString());
                data.setNumber(etNumber.getText().toString());
                dbh.update(data);
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(InputActivity.this);
                dbh.deleteNote(data.getId());
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(InputActivity.this);
                String number = data.getNumber();
                String name = data.getName();
                String amount = data.getAmount();
                String date = data.getDate();

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, "Hi " + name + ". You owe me $" + amount + " on " + date, null, null);
                Toast.makeText(InputActivity.this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
