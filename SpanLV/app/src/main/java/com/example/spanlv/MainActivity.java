package com.example.spanlv;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String[] mainFood, food1, food2, food3;
    private ListView listView;
    private Spinner spinnerFood, spinnerMenu;
    private String[] selectedItems = {"主餐: ", "附餐: ", "飲料: "};
    public static String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFood = getResources().getStringArray(R.array.mainfood);
        food1 = new String[]{"牛肉漢堡", "豬肉漢堡", "雞肉漢堡", "素食漢堡"};
        food2 = new String[]{"薯條", "炸雞", "洋蔥圈", "沙拉"};
        food3 = new String[]{"可樂", "紅茶", "綠茶", "奶茶"};

        spinnerFood = findViewById(R.id.spinner);

        listView = findViewById(R.id.listView);

        ArrayAdapter<String> foodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mainFood);
        spinnerFood.setAdapter(foodAdapter);



        spinnerFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> listAdapter;
                switch (position) {
                    case 0:
                        listAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, food1);
                        break;
                    case 1:
                        listAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, food2);
                        break;
                    default:
                        listAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, food3);
                        break;
                }
                listView.setAdapter(listAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFood = parent.getItemAtPosition(position).toString();
                switch (spinnerFood.getSelectedItemPosition()) {
                    case 0:
                        selectedItems[0] = "主餐: " + selectedFood;
                        updateTextView(R.id.txv, selectedItems[0]);
                        break;
                    case 1:
                        selectedItems[1] = "附餐: " + selectedFood;
                        updateTextView(R.id.txv2, selectedItems[1]);
                        break;
                    default:
                        selectedItems[2] = "飲料: " + selectedFood;
                        updateTextView(R.id.txv3, selectedItems[2]);
                        break;
                }
                updateMessage();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


            if (id == R.id.action_send) {
                if (selectedItems[0].equals("主餐: ") || selectedItems[1].equals("附餐: ") || selectedItems[2].equals("飲料: ")) {
                    Toast.makeText(MainActivity.this, "請選擇餐點", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, second.class);
                    startActivity(intent);
                }

            } else if (id == R.id.action_cancel) {
                resetSelection();

            }

        return super.onOptionsItemSelected(item);

        }


    private void updateTextView(int textViewId, String text) {
        TextView textView = findViewById(textViewId);
        textView.setText(text);
    }

    private void resetSelection() {
        selectedItems[0] = "主餐: ";
        selectedItems[1] = "附餐: ";
        selectedItems[2] = "飲料: ";
        updateTextView(R.id.txv, selectedItems[0]);
        updateTextView(R.id.txv2, selectedItems[1]);
        updateTextView(R.id.txv3, selectedItems[2]);
    }

    private void updateMessage() {
        msg = selectedItems[0] + "\n" + selectedItems[1] + "\n" + selectedItems[2];
    }
}
