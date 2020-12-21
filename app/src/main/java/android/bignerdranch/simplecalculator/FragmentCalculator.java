package android.bignerdranch.simplecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentCalculator extends Fragment {
    private TextView tv_enterPlace;
    private TextView tv_result;
    private static final String TAG = "someCalculator";

    private Button bt_zero;
    private Button bt_one;
    private Button bt_two;
    private Button bt_three;
    private Button bt_four;
    private Button bt_fife;
    private Button bt_six;
    private Button bt_seven;
    private Button bt_eight;
    private Button bt_nine;
    private Button bt_dot;
    private Button bt_add;
    private Button bt_subtract;
    private Button bt_multiply;
    private Button bt_divide;
    private Button bt_result;

    private ImageButton ib_backSpace;


    private char operand = ' ';
    private char operand2;
    private String secondString = "";
    private String firstString = "";
    private Double[] arrayDouble = new Double[5];

    private boolean isNotResultNull;
    private boolean forComma;


    FragmentCalculator newFragment() {

        return new FragmentCalculator();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);
        tv_enterPlace = v.findViewById(R.id.tv_first);
        tv_result = v.findViewById(R.id.tv_result);

        bt_zero = v.findViewById(R.id.bt_zero);
        bt_one = v.findViewById(R.id.bt_one);
        bt_two = v.findViewById(R.id.bt_two);
        bt_three = v.findViewById(R.id.bt_three);
        bt_four = v.findViewById(R.id.bt_four);
        bt_fife = v.findViewById(R.id.bt_fife);
        bt_six = v.findViewById(R.id.bt_six);
        bt_seven = v.findViewById(R.id.bt_seven);
        bt_eight = v.findViewById(R.id.bt_eight);
        bt_nine = v.findViewById(R.id.bt_nine);

        bt_dot = v.findViewById(R.id.bt_dot);
        bt_add = v.findViewById(R.id.bt_add);
        bt_subtract = v.findViewById(R.id.bt_subtract);
        bt_multiply = v.findViewById(R.id.bt_multiplay);
        bt_divide = v.findViewById(R.id.bt_divide);
        bt_result = v.findViewById(R.id.bt_result);

        ib_backSpace = v.findViewById(R.id.ib_backSpace);
        arrayDouble[2] = 0.00;
        buttonsofNumbers();
        buttonsOfOperations();


        return v;
    }

    private void buttonsofNumbers() {
        bt_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("0");
            }
        });
        bt_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("1");
            }
        });
        bt_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("2");
            }
        });
        bt_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("3");
            }
        });
        bt_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("4");
            }
        });
        bt_fife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("5");
            }
        });
        bt_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("6");
            }
        });
        bt_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("7");
            }
        });
        bt_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("8");
            }
        });
        bt_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber("9");
            }
        });
    }

    private void buttonsOfOperations() {
        bt_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstString.equals("")) {
                    enterNumber(".");
                    bt_dot.setEnabled(false);
                }
            }
        });
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstString.equals("")) {
                    clickableNumberButtons(true);
                    tv_enterPlace.setText(tv_enterPlace.getText().toString() + "+");
                    operand = '+';
                    operand2 = '+';
                    initializeResult();
                    forComma = true;
                    clickableOperationButtons(false);
                }
            }
        });
        bt_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstString.equals("")) {
                    clickableNumberButtons(true);
                    tv_enterPlace.setText(tv_enterPlace.getText().toString() + "-");
                    operand = '-';
                    operand2 = '-';
                    initializeResult();
                    forComma = true;
                    clickableOperationButtons(false);
                }
            }
        });
        bt_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstString.equals("")) {
                    clickableNumberButtons(true);
                    tv_enterPlace.setText(tv_enterPlace.getText().toString() + "*");
                    operand = '*';
                    initializeResult();
                    forComma = true;
                    clickableOperationButtons(false);
                }
            }
        });
        bt_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstString.equals("")) {
                    clickableNumberButtons(true);
                    tv_enterPlace.setText(tv_enterPlace.getText().toString() + "÷");
                    operand = '÷';
                    initializeResult();
                    forComma = true;
                    clickableOperationButtons(false);
                }
            }
        });

        ib_backSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backSpace();
            }
        });
        ib_backSpace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cleanAll();
                return false;
            }
        });
        bt_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeResult();
                tv_enterPlace.setText(arrayDouble[0].toString());
                tv_result.setText("");
                clickableNumberButtons(false);
            }
        });
    }

    private void initializeResult() {
        if (isNotResultNull) {
            arrayDouble[0] = arrayDouble[3];
            secondString = "";


        }
    }

    private void enterNumber(String number) {

        tv_enterPlace.setText(tv_enterPlace.getText().toString() + number);
        clickableOperationButtons(true);
        if (operand == ' ') {
            firstString = firstString + number;
            arrayDouble[0] = Double.parseDouble(firstString);
            Log.i(TAG, Double.toString(arrayDouble[0]) + " firstNumber in EnterNumber");

        } else {

            secondString = secondString + number;
            Log.i(TAG, secondString + " secondNumber in EnterNumber");
            if (forComma && !secondString.equals("")) {

                bt_dot.setEnabled(true);
                forComma = false;
            }
            arrayDouble[1] = Double.parseDouble(secondString);
            operationNumbers(operand);

        }
    }

    private void operationNumbers(char userOperand) {
        isNotResultNull = true;


        switch (userOperand) {
            case '+':

                arrayDouble[3] = arrayDouble[0] + arrayDouble[1];
                arrayDouble[2] = arrayDouble[1];
                Log.i(TAG, Double.toString(arrayDouble[0]) + " is first number in OperationNumber");
                Log.i(TAG, Double.toString(arrayDouble[1]) + " is second number in OperationNumber");
                Log.i(TAG, Double.toString(arrayDouble[3]) + " is the result in OperationNumber");

                tv_result.setText(Double.toString(arrayDouble[3]));
                break;
            case '-':

                arrayDouble[3] = arrayDouble[0] - arrayDouble[1];
                arrayDouble[2] = arrayDouble[1];
                Log.i(TAG, Double.toString(arrayDouble[0]) + " is first number in OperationNumber");
                Log.i(TAG, Double.toString(arrayDouble[1]) + " is second number in OperationNumber");
                tv_result.setText(Double.toString(arrayDouble[3]));
                break;
            case '*':

                if (operand2 == '+') {

                    arrayDouble[0] = arrayDouble[0] - arrayDouble[2];

                    arrayDouble[2] = arrayDouble[2] * arrayDouble[1];
                    arrayDouble[3] = arrayDouble[0] + arrayDouble[2];

                    tv_result.setText(Double.toString(arrayDouble[3]));
                } else if (operand2 == '-') {
                    arrayDouble[0] = arrayDouble[0] + arrayDouble[2];

                    arrayDouble[2] = arrayDouble[2] * arrayDouble[1];
                    arrayDouble[3] = arrayDouble[0] - arrayDouble[2];
                    tv_result.setText(Double.toString(arrayDouble[3]));
                } else {
                    arrayDouble[3] = arrayDouble[0] * arrayDouble[1];
                    Log.i(TAG, Double.toString(arrayDouble[0]) + "double 0 in multiplying");
                    tv_result.setText(Double.toString(arrayDouble[3]));
                }
                break;
            case '÷':

                if (operand2 == '+') {

                    arrayDouble[0] = arrayDouble[0] - arrayDouble[2];

                    arrayDouble[2] = arrayDouble[2] / arrayDouble[1];
                    arrayDouble[3] = arrayDouble[0] + arrayDouble[2];

                    tv_result.setText(Double.toString(arrayDouble[3]));
                } else if (operand2 == '-') {
                    arrayDouble[0] = arrayDouble[0] + arrayDouble[2];

                    arrayDouble[2] = arrayDouble[2] / arrayDouble[1];
                    arrayDouble[3] = arrayDouble[0] - arrayDouble[2];
                    tv_result.setText(Double.toString(arrayDouble[3]));
                } else {
                    arrayDouble[3] = arrayDouble[0] / arrayDouble[1];
                    Log.i(TAG, Double.toString(arrayDouble[0]) + " double 0 in dividing");
                    tv_result.setText(Double.toString(arrayDouble[3]));
                }
                break;
            default:

        }


    }

    private void cleanAll() {

        tv_enterPlace.setText("");
        tv_result.setText("");
        operand = ' ';
        operand2 = ' ';
        arrayDouble[0] = 0.00;

        arrayDouble[1] = 0.00;
        arrayDouble[2] = 0.00;
        arrayDouble[3] = 0.00;
        arrayDouble[4] = 0.00;
        firstString = "";
        secondString = "";
        isNotResultNull = false;
        bt_dot.setEnabled(true);
        clickableNumberButtons(true);
    }

    private void backSpace() {
        clickableNumberButtons(true);

        if (operand == ' ' && firstString.length() > 1) {
            tv_enterPlace.setText(tv_enterPlace.getText().toString().
                    substring(0, tv_enterPlace.getText().length() - 1));
            firstString = firstString.substring(0, firstString.length() - 1);

            arrayDouble[0] = Double.parseDouble(firstString);

        } else if (operand != ' ' && secondString.length() > 1) {
            tv_enterPlace.setText(tv_enterPlace.getText().toString().
                    substring(0, tv_enterPlace.getText().length() - 1));
            secondString = secondString.substring(0, secondString.length() - 1);
            arrayDouble[1] = Double.parseDouble(secondString);
            operationNumbers(operand);
        } else {
            cleanAll();
        }

    }

    private void clickableNumberButtons(boolean permission) {
        bt_zero.setClickable(permission);
        bt_one.setClickable(permission);
        bt_two.setClickable(permission);
        bt_three.setClickable(permission);
        bt_four.setClickable(permission);
        bt_fife.setClickable(permission);
        bt_six.setClickable(permission);
        bt_seven.setClickable(permission);
        bt_eight.setClickable(permission);
        bt_nine.setClickable(permission);
        bt_dot.setClickable(permission);

        if (permission) {
            tv_result.setTextSize(25);

        }
    }


    private void clickableOperationButtons(boolean clickable) {

        bt_add.setClickable(clickable);
        bt_subtract.setClickable(clickable);
        bt_multiply.setClickable(clickable);
        bt_divide.setClickable(clickable);
    }
}
