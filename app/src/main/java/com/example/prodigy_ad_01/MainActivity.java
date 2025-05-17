package com.example.prodigy_ad_01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    TextView expressionTV, resultTV;
    String expression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionTV = findViewById(R.id.expressionTV);
        resultTV = findViewById(R.id.resultTV);

        int[] numberBtnIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9, R.id.btn00, R.id.btnDot
        };

        int[] operatorBtnIds = {
                R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide, R.id.btnPercent
        };

        View.OnClickListener numberClickListener = v -> {
            Button btn = (Button) v;
            expression += btn.getText().toString();
            expressionTV.setText(expression);
        };

        for (int id : numberBtnIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        for (int id : operatorBtnIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            expression = "";
            expressionTV.setText("");
            resultTV.setText("");
        });

        findViewById(R.id.btnCut).setOnClickListener(v -> {
            if (!expression.isEmpty()) {
                expression = expression.substring(0, expression.length() - 1);
                expressionTV.setText(expression);
            }
        });

        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            try {
                String cleanExpression = expression.replaceAll("×", "*")
                        .replaceAll("÷", "/")
                        .replaceAll("%", "/100");

                Expression exp = new ExpressionBuilder(cleanExpression).build();
                double result = exp.evaluate();
                resultTV.setText(String.valueOf(result));
            } catch (Exception e) {
                resultTV.setText("Error");
            }
        });

    }
}
