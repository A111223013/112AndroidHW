package com.example.hw_mcdonaldorder;

import android.media.Image;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView image1, image2, image3, image4, image5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        image1 = (ImageView) findViewById(R.id.img1);
        image2 = (ImageView) findViewById(R.id.img2);
        image3 = (ImageView) findViewById(R.id.img3);
        image4 = (ImageView) findViewById(R.id.img4);
        image5 = (ImageView) findViewById(R.id.img5);

        ImageView output1 = (ImageView)findViewById(R.id.output1);
        ImageView output2 = (ImageView)findViewById(R.id.output2);
        ImageView output3 = (ImageView)findViewById(R.id.output3);
        ImageView output4 = (ImageView)findViewById(R.id.output4);
        ImageView output5 = (ImageView)findViewById(R.id.output5);

        CheckBox chk1 = (CheckBox) findViewById(R.id.chk1);
        CheckBox chk2 = (CheckBox) findViewById(R.id.chk2);
        CheckBox chk3 = (CheckBox) findViewById(R.id.chk3);
        CheckBox chk4 = (CheckBox) findViewById(R.id.chk4);
        CheckBox chk5 = (CheckBox) findViewById(R.id.chk5);

        TextView showOrder =(TextView)findViewById(R.id.showOrder);
        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    showOrder.setText("您點的餐點如下");
                    output1.setVisibility(ImageView.VISIBLE);
                }
                else
                {
                    output1.setVisibility(ImageView.INVISIBLE);
                }
            }
        });
        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    showOrder.setText("您點的餐點如下");
                    output2.setVisibility(ImageView.VISIBLE);
                }
                else
                {
                    output2.setVisibility(ImageView.INVISIBLE);
                }
            }
        });
        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    showOrder.setText("您點的餐點如下");
                    output3.setVisibility(ImageView.VISIBLE);
                }
                else
                {
                    output3.setVisibility(ImageView.INVISIBLE);
                }
            }
        });
        chk4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    showOrder.setText("您點的餐點如下");
                    output4.setVisibility(ImageView.VISIBLE);
                }
                else
                {
                    output4.setVisibility(ImageView.INVISIBLE);
                }
            }
        });
        chk5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    showOrder.setText("您點的餐點如下");
                    output5.setVisibility(ImageView.VISIBLE);
                }
                else
                {
                    output5.setVisibility(ImageView.INVISIBLE);
                }
            }
        });
        };
    }
