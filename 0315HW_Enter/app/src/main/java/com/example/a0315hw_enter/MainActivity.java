package com.example.a0315hw_enter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void button_Click(View view) {
        EditText account = (EditText) findViewById(R.id.account);
        String userName = account.getText().toString();
        int a = 0;
        int p = 0;
        int e = 0;
        if (userName.equals("A111223013")) {
            a = 1;
        } else {
            a = 0;
        }
        EditText password = (EditText) findViewById(R.id.password);
        String Enterpass = password.getText().toString();
        if (Enterpass.equals("archer0109")) {
            p = 1;
        } else {
            p = 0;
        }
        TextView txvShow = findViewById(R.id.txvShow);

        if (a == 1 && p == 1) {
            txvShow.setText("成功登入");
        } else if (a == 1 && p == 0){
            txvShow.setText("登入失敗，密碼錯誤");

    } else if(a==0&&p==1){
            txvShow.setText("登入失敗，帳號錯誤");
}
        else{
            txvShow.setText("登入失敗，請檢查帳號和密碼");
        }


    }
}