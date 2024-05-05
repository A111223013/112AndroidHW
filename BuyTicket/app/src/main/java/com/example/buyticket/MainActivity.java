//A111223013 江秉騏

package com.example.buyticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int money = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSure = findViewById(R.id.btnSure);
        EditText edtNumber = findViewById(R.id.edtNumber);
        edtNumber.setText("1");
        RadioGroup rgType = findViewById(R.id.rgType);
        RadioGroup rgGender = findViewById(R.id.rgGender);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UpdateResult();
            }
        });

        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UpdateResult();
            }
        });

        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                UpdateResult();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateResult();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                String ticketNum = edtNumber.getText().toString();
                intent.putExtra("ticketNum", ticketNum);
                intent.putExtra("AllMoney", money * Integer.parseInt(ticketNum));
                RadioButton rdbBoy = findViewById(R.id.rdbBoy);
                RadioButton rdbGirl = findViewById(R.id.rdbGirl);
                if (rdbBoy.isChecked()) {
                    intent.putExtra("gender", getResources().getString(R.string.male));
                } else if (rdbGirl.isChecked()) {
                    intent.putExtra("gender", getResources().getString(R.string.female));
                }
                RadioGroup rgType = findViewById(R.id.rgType);
                if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
                    intent.putExtra("Tickets", getResources().getString(R.string.regularticket));
                    money = 500;
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
                    intent.putExtra("Tickets", getResources().getString(R.string.childticket));
                    money = 400;
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
                    intent.putExtra("Tickets", getResources().getString(R.string.studentticket));
                    money = 300;
                }
                intent.putExtra("AllMoney", money); // 确保正确的 AllMoney 值被传递


                startActivity(intent);
            }
        });

        UpdateResult();
    }

    public void UpdateResult() {
        String txvResult = "";
        EditText edtNumber = findViewById(R.id.edtNumber);
        RadioButton rdbBoy = findViewById(R.id.rdbBoy);
        RadioButton rdbGirl = findViewById(R.id.rdbGirl);
        RadioGroup rgType = findViewById(R.id.rgType);
        String TicketNum = edtNumber.getText().toString();
        if (TicketNum.isEmpty()) {
            return;
        }
        if (rdbBoy.isChecked()) {
            txvResult = getResources().getString(R.string.male) + "\n";
        } else if (rdbGirl.isChecked()) {
            txvResult = getResources().getString(R.string.female) + "\n";
        }
        int AllMoney = 0;
        if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
            AllMoney = 500 * Integer.parseInt(TicketNum);
            txvResult += getResources().getString(R.string.regularticket) +":"+ TicketNum + "\n" + AllMoney;
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
            AllMoney = 400 * Integer.parseInt(TicketNum);
            txvResult += getResources().getString(R.string.childticket) +":"+ TicketNum + "\n" + AllMoney;
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
            AllMoney = 250 * Integer.parseInt(TicketNum);
            txvResult += getResources().getString(R.string.studentticket) +":"+ TicketNum + "\n" + AllMoney;
        }
        TextView txvOutput = findViewById(R.id.txvResult);
        txvOutput.setText(txvResult);
    }
}
