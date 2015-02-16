package edu.gatech.seclass.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
	
	private Button button;
    private EditText text;
	private long result = 0;
	private long operand;
	private int operator;
	private boolean waitForInput = false;
	
	//Set text default value to be "0".
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (EditText) findViewById(R.id.editText);
		text.setText(String.valueOf(result));
	}
	
    //Reset the calculator
	public void init() {
		result = 0;
		operand = 0;
		operator = 0;
		waitForInput = false;
	}
    //Handle operators, including "C", "+", "-", "*". Save one operand.
	private void handleOperator(int i) {
		operand = result;
		result = 0;
		switch (i) {
		case 0:
			init();
			text.setText(String.valueOf(result));
			break;
		case 1:
			operator = 1;
			waitForInput = true;
			break;
		case 2:
			operator = 2;
			waitForInput = true;
			break;
		case 3: 
			operator = 3;
			waitForInput = true;
			break;
		default:
			break;
		}
	}
	
	//Handle operator "=". Calculate results. If the result is out of integer limit, display "Error" and reset the calculator.
    public void getResult() {
    	if (waitForInput) {
    		init();
    		text.setText("Error");
    		return;
    	}
    	try {
    		switch (operator) {
        	case 0:
        		break;
        	case 1: 
        		result = operand + result;
        		operand = 0;
        		break;
        	case 2:
        		result = operand - result;
        		operand = 0;
        		break;
        	case 3:
        		result = operand * result;
        		operand = 0;
        		break;
    		}
        	if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) { 
				init();
				text.setText("Error");
			} else {
				text.setText(String.valueOf(result));
			}
    	} catch (Exception ex) {
    		init();
    		text.setText("Error");
    	}
    }
    
    //Button Click Listener.
	public void onButtonClick(View view) {
		switch (view.getId()) {
		case R.id.Button12: //"+"
			handleOperator(1);
	        break;
		case R.id.Button13: //"-"
			handleOperator(2);
	        break;
		case R.id.Button14: //"*"
			handleOperator(3); 
	        break;
		case R.id.Button11: //"C"
			handleOperator(0);
			break;
		case R.id.Button15: //"="
			getResult();
			break;
		default: //"0"-"9"
			button = (Button) findViewById(view.getId());
			String input = button.getText().toString();
			waitForInput = false;
			result = result * 10 + Integer.parseInt(input);
			//If the number is out of integer limit, display "Error" and reset the calculator.
			if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) { 
				init();
				text.setText("Error");
			} else {
				text.setText(String.valueOf(result));
			}  
			break;
		}
		
    }
	
}
