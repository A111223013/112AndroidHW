package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

// 枚举类型用于表示计算器状态和操作符
enum State {FirstNumberInput, OperatorInputed, SecondNumberInput}
enum OP { None, Add, Sub, Mul, Div}

public class MainActivity extends AppCompatActivity {

    private double theValue = 0; // 当前值
    private double operand1 = 0; // 第一个操作数
    private double operand2 = 0; // 第二个操作数
    private OP op = OP.None; // 当前操作符
    private State state = State.FirstNumberInput; // 当前状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
    }

    // 在窗口焦点改变时调用，调整按钮大小和文本大小
    public void onWindowFocusChanged(boolean hasFocus) {
        GridLayout keysGL = findViewById(R.id.keys);

        int kbHeight = keysGL.getHeight() / keysGL.getRowCount();
        int kbWidth = keysGL.getWidth() / keysGL.getColumnCount();

        Button btn;

        for (int i = 0; i < keysGL.getChildCount(); i++) {
            btn = (Button) keysGL.getChildAt(i);
            btn.setHeight(kbHeight);
            btn.setWidth(kbWidth);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        }
    }

    // 处理按钮点击事件
    // 处理按钮点击事件
    public void processKeyInput(View view) {
        Button b = (Button) view; // 获取点击的按钮
        String bstr = b.getText().toString(); // 获取按钮上的文字
        EditText edt = findViewById(R.id.display); // 获取显示结果的 EditText 元件

        try {
            // 处理数字按钮点击事件
            switch (bstr) {
                // 数字按钮被点击时
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    int digit = Integer.parseInt(bstr); // 将文字转换为整数
                    switch (state) {
                        case FirstNumberInput:
                            if (theValue % 1 == 0) {
                                 theValue = theValue * 10 + digit; // 如果当前值是整数，则直接将当前值乘以10并加上新输入的数字
                            } else {
                                // 如果当前值是小数，则转换为带小数点的浮点数，并添加新输入的数字部分
                                theValue = Double.parseDouble(theValue + "." + digit);
                            }
                            break;
                        case OperatorInputed:
                            theValue = digit; // 将当前值设置为新的数字
                            operand2 = digit; // 将第二个操作数设置为新的数字
                            state = State.SecondNumberInput; // 进入第二个数字输入状态
                            break;
                        case SecondNumberInput:
                            if (theValue % 1 == 0) {
                                theValue = theValue * 10 + digit; // 如果当前值是整数，则直接将当前值乘以10并加上新输入的数字
                            } else {
                                // 如果当前值是小数，则转换为带小数点的浮点数，并添加新输入的数字部分
                                theValue = Double.parseDouble(theValue + "." + digit);
                            }
                            break;
                    }
                    edt.setText(String.valueOf(theValue)); // 在 EditText 中显示当前值
                    break;



                // 小数点按钮被点击时
                case ".":
                    // 如果当前值不包含小数点，则可以添加小数点
                    if (!String.valueOf(theValue).contains(".")) {
                        // 在当前值后面添加小数点
                        theValue = Double.parseDouble(String.valueOf(theValue) + ".");
                        edt.setText(String.valueOf(theValue)); // 在 EditText 中显示当前值
                        state = State.SecondNumberInput; // 进入第二个数字输入状态
                    }
                    break;

                // 运算符按钮被点击时
                case "+":
                case "-":
                case "*":
                case "/":
                    // 显示运算符在 EditText 中
                    edt.setText(String.valueOf(theValue) + bstr);

                    // 根据当前状态进行处理
                    switch (state) {
                        case FirstNumberInput:
                            operand1 = theValue; // 设置第一个操作数
                            operand2 = theValue; // 设置第二个操作数
                            switch (bstr) {
                                case "+":
                                    op = OP.Add;
                                    break;
                                case "-":
                                    op = OP.Sub;
                                    break;
                                case "*":
                                    op = OP.Mul;
                                    break;
                                case "/":
                                    op = OP.Div;
                                    break;
                            }
                            state = State.OperatorInputed; // 进入运算符输入状态
                            break;
                        case OperatorInputed:
                            switch (bstr) {
                                case "+":
                                    op = OP.Add;
                                    break;
                                case "-":
                                    op = OP.Sub;
                                    break;
                                case "*":
                                    op = OP.Mul;
                                    break;
                                case "/":
                                    op = OP.Div;
                                    break;
                            }
                            operand2 = theValue; // 设置第二个操作数
                            break;
                        case SecondNumberInput:
                            operand2 = theValue; // 设置第二个操作数
                            // 根据当前操作符计算结果
                            switch (op) {
                                case Add:
                                    theValue = operand1 + operand2;
                                    break;
                                case Sub:
                                    theValue = operand1 - operand2;
                                    break;
                                case Mul:
                                    theValue = operand1 * operand2;
                                    break;
                                case Div:
                                    theValue = operand1 / operand2;
                                    break;
                            }
                            operand1 = theValue; // 更新第一个操作数为计算结果
                            switch (bstr) {
                                case "+":
                                    op = OP.Add;
                                    break;
                                case "-":
                                    op = OP.Sub;
                                    break;
                                case "*":
                                    op = OP.Mul;
                                    break;
                                case "/":
                                    op = OP.Div;
                                    break;
                            }
                            state = State.OperatorInputed; // 进入运算符输入状态
                            edt.setText(String.valueOf(theValue)); // 在 EditText 中显示当前值
                            break;
                    }
                    break;

                // 等号按钮被点击时
                case "=":
                    // 根据当前状态进行处理
                    if (state == State.OperatorInputed) {
                        // 根据当前操作符计算结果
                        switch (op) {
                            case Add:
                                theValue = operand1 + operand2;
                                break;
                            case Sub:
                                theValue = operand1 - operand2;
                                break;
                            case Mul:
                                theValue = operand1 * operand2;
                                break;
                            case Div:
                                theValue = operand1 / operand2;
                                break;
                        }
                        operand1 = theValue; // 更新第一个操作数为计算结果
                    } else if (state == State.SecondNumberInput) {
                        operand2 = theValue; // 设置第二个操作数
                        // 根据当前操作符计算结果
                        switch (op) {
                            case Add:
                                theValue = operand1 + operand2;
                                break;
                            case Sub:
                                theValue = operand1 - operand2;
                                break;
                            case Mul:
                                theValue = operand1 * operand2;
                                break;
                            case Div:
                                theValue = operand1 / operand2;
                                break;
                        }
                        operand1 = theValue; // 更新第一个操作数为计算结果
                        state = State.OperatorInputed; // 进入运算符输入状态
                    }
                    edt.setText(String.valueOf(theValue)); // 在 EditText 中显示当前值
                    break;

                // 清除按钮被点击时
                case "Clear":
                    state = State.FirstNumberInput; // 重置为第一个数字输入状态
                    theValue = 0; // 清空当前值
                    operand1 = 0; // 清空第一个操作数
                    operand2 = 0; // 清空第二个操作数
                    op = OP.None; // 清空操作符
                    edt.setText("0"); // 在 EditText 中显示 0
                    break;

                // 退格按钮被点击时
                case "Back":
                    theValue = (int) (theValue / 10); // 移除当前值的最后一位数字
                    edt.setText(String.valueOf(theValue)); // 在 EditText 中显示当前值
                    break;
            }
        } catch (NumberFormatException e) {
            // 捕获转换异常
            Log.e("Error", "NumberFormatException: " + e.getMessage());
            edt.setText("Error"); // 在 EditText 中显示错误信息
        }
    }
}
