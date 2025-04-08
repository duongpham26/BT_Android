package com.duongpham26.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView expressionView, resultView, equalSignView;
    StringBuilder inputBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputBuilder = new StringBuilder();

        expressionView = findViewById(R.id.expressionView);
        resultView = findViewById(R.id.resultView);
        equalSignView = findViewById(R.id.equalSignView);
        equalSignView.setText("");

        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        int[] operatorButtonIds = {
                R.id.buttonadd, R.id.buttonsub, R.id.buttonmul, R.id.buttondiv, R.id.Remainder
        };

        String[] operatorSymbols = {"+", "-", "×", "÷", "%"};

        // Nút số
        for (int i = 0; i < numberButtonIds.length; i++) {
            int finalI = i;
            Button btn = findViewById(numberButtonIds[i]);
            btn.setOnClickListener(v -> {
                inputBuilder.append(finalI);
                expressionView.setText(inputBuilder.toString());
                equalSignView.setText("");
                resultView.setText("");
            });
        }

        // Nút phép toán
        for (int i = 0; i < operatorButtonIds.length; i++) {
            int finalI = i;
            Button btn = findViewById(operatorButtonIds[i]);
            btn.setOnClickListener(v -> {
                inputBuilder.append(operatorSymbols[finalI]);
                expressionView.setText(inputBuilder.toString());
                equalSignView.setText("");
                resultView.setText("");
            });
        }

        // Dấu chấm
        Button buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setOnClickListener(v -> {
            inputBuilder.append(".");
            expressionView.setText(inputBuilder.toString());
            equalSignView.setText("");
            resultView.setText("");
        });

        // Nút xóa
        Button buttonDel = findViewById(R.id.buttonDel);
        buttonDel.setOnClickListener(v -> {
            inputBuilder.setLength(0);
            expressionView.setText("");
            equalSignView.setText("");
            resultView.setText("");
        });

        // Nút bằng
        Button buttonEqual = findViewById(R.id.buttoneql);
        buttonEqual.setOnClickListener(v -> {
            String input = inputBuilder.toString();
            try {
                double result = evaluateExpression(input);

                expressionView.setText(input);
                equalSignView.setText("=");

                String resultText = (result == (int) result)
                        ? String.valueOf((int) result)
                        : String.valueOf(result);

                resultView.setText(resultText);
                inputBuilder.setLength(0);
            }  catch (ArithmeticException e) {
                expressionView.setText(input);
                equalSignView.setText("=");
                resultView.setText("Lỗi chia 0");
                inputBuilder.setLength(0);
            } catch (Exception e) {
                expressionView.setText("");
                equalSignView.setText("");
                resultView.setText("Lỗi!");
                inputBuilder.setLength(0);
            }
        });
    }

    // Hàm tính thủ công
    private double evaluateExpression(String expr) throws Exception {
        expr = expr.replace("×", "*").replace("÷", "/");

        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                currentNumber.append(c);
            } else {
                if (currentNumber.length() == 0) throw new Exception("Biểu thức sai");
                numbers.add(Double.parseDouble(currentNumber.toString()));
                currentNumber.setLength(0);
                operators.add(c);
            }
        }

        if (currentNumber.length() > 0) {
            numbers.add(Double.parseDouble(currentNumber.toString()));
        }

        // Xử lý *, /, %
        for (int i = 0; i < operators.size(); ) {
            char op = operators.get(i);
            if (op == '*' || op == '/' || op == '%') {
                double a = numbers.get(i);
                double b = numbers.get(i + 1);
                double result;

                switch (op) {
                    case '*': result = a * b; break;
                    case '/': result = a / b; break;
                    case '%': result = a % b; break;
                    default: throw new Exception("Toán tử lỗi");
                }

                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
            } else {
                i++;
            }
        }

        // Xử lý +, -
        double result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            double b = numbers.get(i + 1);
            switch (op) {
                case '+': result += b; break;
                case '-': result -= b; break;
                default: throw new Exception("Toán tử lỗi");
            }
        }

        return result;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputBuilder", inputBuilder.toString());
        outState.putString("expression", ((TextView)findViewById(R.id.expressionView)).getText().toString());
        outState.putString("result", ((TextView)findViewById(R.id.resultView)).getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String savedInput = savedInstanceState.getString("inputBuilder", "");
        String savedExpression = savedInstanceState.getString("expression", "");
        String savedResult = savedInstanceState.getString("result", "");

        inputBuilder.setLength(0);
        inputBuilder.append(savedInput);

        ((TextView)findViewById(R.id.expressionView)).setText(savedExpression);
        ((TextView)findViewById(R.id.resultView)).setText(savedResult);
    }
}
