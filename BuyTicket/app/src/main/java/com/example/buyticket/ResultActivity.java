package com.example.buyticket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String ticketNum = intent.getStringExtra("ticketNum");
        String gender = intent.getStringExtra("gender");
        String Tickets = intent.getStringExtra("Tickets");
        int allMoney = intent.getIntExtra("AllMoney",0); // 默认值为0

        if (Tickets.equals(getResources().getString(R.string.regularticket))) {
            allMoney = 500*Integer.parseInt(ticketNum);
        } else if (Tickets.equals(getResources().getString(R.string.childticket))) {
            allMoney = 400*Integer.parseInt(ticketNum);;
        } else if (Tickets.equals(getResources().getString(R.string.studentticket))) {
            allMoney = 250*Integer.parseInt(ticketNum);
        }

        // 根据语言设置获取正确的字符串资源
        String genderText = getResources().getString(R.string.MorF);
        String buyText = getResources().getString(R.string.YouBuy);
        String totalText = getResources().getString(R.string.TotalMoney);
        String moneyText = getResources().getString(R.string.Money);

        // 根据语言设置拼接要显示的信息
        String BorG = genderText + gender;
        String message = buyText + Tickets +":" + ticketNum;
        message = BorG + "\n" + message + "\n" + totalText + allMoney +"  "+ moneyText;

        TextView txvPrint = findViewById(R.id.txvPrint);
        txvPrint.setText(message);
    }

}