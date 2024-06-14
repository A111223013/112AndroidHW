package com.example.spanlv;

import android.content.Intent;
import android.icu.util.Output;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private  String[] m_food,foods;
    private  String[] mg={" "," "," "};
    private ListView lv;
    private Spinner spfood;
    private String[] food1 = {"牛肉漢堡", "豬肉漢堡", "雞肉漢堡", "素食漢堡"};
    private String[] food2 = {"薯條", "炸雞", "洋蔥圈", "沙拉"};
    private String[] food3 = {"可樂", "紅茶", "綠茶", "奶茶"};
    public  static  String msg;
    private   int ps = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        m_food = getResources().getStringArray(R.array.mainfood);
        spfood = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adpfood = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,m_food);
        spfood.setAdapter(adpfood);

        // foods = getResources().getStringArray(R.array.food1);

//        ArrayAdapter<String> adpfood_1 = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,food1);
//
//        lv = (ListView) findViewById(R.id.listView);
//        lv.setAdapter(adpfood_1);

        spfood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] tem;

                if (position == 0)
                {

                    tem = food1;
                    ps = 0;

                } else if (position==1) {

                    tem = food2;
                    ps = 1;


                } else {
                    tem = food3;
                    ps = 2;
                }
                ArrayAdapter<String> adpfood_1 = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, tem);

                lv = (ListView) findViewById(R.id.listView);
                lv.setAdapter(adpfood_1);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (ps==0)
                        {
                            TextView output = findViewById(R.id.txv);
                            String selectf = parent.getItemAtPosition(position).toString();
                            output.setText("主餐:" + selectf);
                            mg[0]=("主餐:" + selectf);
                        } else if (ps==1) {
                            TextView output = findViewById(R.id.txv2);
                            String selectf = parent.getItemAtPosition(position).toString();
                            output.setText("附餐:" + selectf);
                            mg[1]=("附餐:" + selectf);
                        }
                        else {
                            TextView output = findViewById(R.id.txv3);
                            String selectf = parent.getItemAtPosition(position).toString();
                            output.setText("飲料:" + selectf);
                            mg[2]=("飲料:" + selectf);
                        }
                        msg=mg[0]+"\n"
                                +mg[1]+"\n"
                                +mg[2]+"\n"
                        ;
                    }
                });


                adpfood_1.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId ==R.id.to1)
        {
            TextView output = findViewById(R.id.txv);
            if (mg[0]=="主餐:" || mg[1]=="附餐:"||mg[2]=="飲料:"
                    ||mg[0]==" " || mg[1]==" "||mg[2]==" ")
            {
                output.setText("請選擇餐點");

            }
            else {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, second.class);
                startActivity(intent);
            }

        }
        else if (itemId == R.id.to2)
        {

            TextView output = findViewById(R.id.txv);
            mg[0]="主餐:";
            output.setText("主餐:");
            TextView output1 = findViewById(R.id.txv2);
            mg[1]="附餐:";
            output1.setText("附餐:");
            TextView output2 = findViewById(R.id.txv3);
            mg[2]="飲料:";
            output2.setText("飲料:");




        }
        return super.onOptionsItemSelected(item);
    }
}