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


        // 找到按鈕
        Button btnSure = findViewById(R.id.btnSure);

        // 找到 EditText
        EditText edtNumber = findViewById(R.id.edtNumber);
        // 将EditText中的文本设置为"1"
        edtNumber.setText("1");
        // 找到 RadioGroup
        RadioGroup rgType = findViewById(R.id.rgType);

        RadioGroup rgGender = findViewById(R.id.rgGender);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UpdateResult();
            }
        });

        // 設置 RadioGroup 的選擇監聽器
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UpdateResult();
            }
        });

        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 不需要实现此方法
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 文本框文本更改时更新结果
                UpdateResult();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 不需要实现此方法
            }
        });


        // 設置按鈕的點擊監聽器
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 先调用更新结果方法以确保 AllMoney 被计算
                UpdateResult();

                // 創建一個 Intent 對象，指定要啟動的目標 Activity
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);

                // 將訂票資訊作為參數傳遞給目標 Activity
                String ticketNum = edtNumber.getText().toString();
                intent.putExtra("ticketNum", ticketNum);
                intent.putExtra("AllMoney", money * Integer.parseInt(ticketNum)); // 将重新计算的 AllMoney 添加到 Intent 中

                // 添加性別信息到 Intent 中
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

                // 啟動目標 Activity
                startActivity(intent);
            }
        });

        // 初始化时更新一次结果
        UpdateResult();
    }

    // 更新 TextView 的方法
    // 更新 TextView 的方法
    public void UpdateResult() {
        String txvResult = "";

        // 找到 EditText 和 RadioButtons
        EditText edtNumber = findViewById(R.id.edtNumber);
        RadioButton rdbBoy = findViewById(R.id.rdbBoy);
        RadioButton rdbGirl = findViewById(R.id.rdbGirl);
        RadioGroup rgType = findViewById(R.id.rgType);

        // 获取票数
        String TicketNum = edtNumber.getText().toString();
        if (TicketNum.isEmpty()) {
            return;
        }

        // 如果选择了性别
        if (rdbBoy.isChecked()) {
            txvResult = getResources().getString(R.string.male) + "\n";
        } else if (rdbGirl.isChecked()) {
            txvResult = getResources().getString(R.string.female) + "\n";
        }

        int AllMoney = 0;

        // 根据选中的 RadioButton 计算总金额
        if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
            AllMoney = 500 * Integer.parseInt(TicketNum);
            txvResult += getResources().getString(R.string.regularticket) + TicketNum + "张\n" + AllMoney + "元";
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
            AllMoney = 400 * Integer.parseInt(TicketNum);
            txvResult += getResources().getString(R.string.childticket) + TicketNum + "张\n" + AllMoney + "元";
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
            AllMoney = 250 * Integer.parseInt(TicketNum); // 使用 250 元作为学生票的价格
            txvResult += getResources().getString(R.string.studentticket) + TicketNum + "张\n" + AllMoney + "元";
        }

        // 找到 TextView
        TextView txvOutput = findViewById(R.id.txvResult);
        // 设置 TextView 的文字内容
        txvOutput.setText(txvResult);
    }
}
