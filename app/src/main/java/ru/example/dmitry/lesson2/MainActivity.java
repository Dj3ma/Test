package ru.example.dmitry.lesson2;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText field1;
    private EditText field2;
    private RadioButton operation1;
    private RadioButton operation2;
    private RadioButton operation3;
    private RadioButton operation4;
    private CheckBox floatValues;
    private CheckBox signedValues;
    private TextView resultField;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field1 = (EditText) findViewById(R.id.Field1);
        field2 = (EditText) findViewById(R.id.Field2);
        operation1 = (RadioButton) findViewById(R.id.Operation1);
        operation2 = (RadioButton) findViewById(R.id.Operation2);
        operation3 = (RadioButton) findViewById(R.id.Operation3);
        operation4 = (RadioButton) findViewById(R.id.Operation4);
        floatValues = (CheckBox) findViewById(R.id.FloatValues);
        signedValues = (CheckBox) findViewById(R.id.SignedValues);
        resultField = (TextView) findViewById(R.id.ResultField);
        calculateButton = (Button) findViewById(R.id.CalculateButton);

        View.OnClickListener checkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.FloatValues:
                        if (((CheckBox)v).isChecked()) {
                            field1.setInputType(field1.getInputType() + InputType.TYPE_NUMBER_FLAG_DECIMAL);
                            field2.setInputType(field2.getInputType() + InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        } else {
                            field1.setInputType(field1.getInputType() - InputType.TYPE_NUMBER_FLAG_DECIMAL);
                            field2.setInputType(field2.getInputType() - InputType.TYPE_NUMBER_FLAG_DECIMAL);
                            if(field1.getText().toString().indexOf('.')!=-1) {
                                field1.setText(field1.getText().toString().substring(0, field1.getText().toString().indexOf('.')));
                            }
                            if(field2.getText().toString().indexOf('.')!=-1) {
                                field2.setText(field2.getText().toString().substring(0, field2.getText().toString().indexOf('.')));
                            }
                        }
                        break;
                    case R.id.SignedValues:
                        if (((CheckBox)v).isChecked()){
                            field1.setInputType(field1.getInputType() + InputType.TYPE_NUMBER_FLAG_SIGNED);
                            field2.setInputType(field2.getInputType() + InputType.TYPE_NUMBER_FLAG_SIGNED);
                        } else {
                            field1.setInputType(field1.getInputType() - InputType.TYPE_NUMBER_FLAG_SIGNED);
                            field2.setInputType(field2.getInputType() - InputType.TYPE_NUMBER_FLAG_SIGNED);
                            if(field1.getText().toString().indexOf('-')!=-1){
                                field1.setText(field1.getText().toString().substring(1));
                            }
                            if(field2.getText().toString().indexOf('-')!=-1){
                                field2.setText(field2.getText().toString().substring(1));
                            }
                        }
                        break;
                    case R.id.CalculateButton:
                        if(field1.getText().toString().equals("")||field2.getText().toString().equals("")){
                            resultField.setText(R.string.isEmpty);
                        }else {
                            resultField.setText(mathOperation());
                        }
                        break;
                }
            }
        };
        floatValues.setOnClickListener(checkListener);
        signedValues.setOnClickListener(checkListener);
        calculateButton.setOnClickListener(checkListener);
        View.OnClickListener radioListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v!=operation1){
                    operation1.setChecked(false);
                }
                if(v!=operation2){
                    operation2.setChecked(false);
                }
                if(v!=operation3){
                    operation3.setChecked(false);
                }
                if(v!=operation4){
                    operation4.setChecked(false);
                }
            }
        };
        operation1.setOnClickListener(radioListener);
        operation2.setOnClickListener(radioListener);
        operation3.setOnClickListener(radioListener);
        operation4.setOnClickListener(radioListener);

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private String mathOperation(){
        float result = 0;
            switch (getRadioButtonId()) {
                case -1:
                    Toast toast = Toast.makeText(this, R.string.OperationNotFound, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                case R.id.Operation1:
                    result = Float.parseFloat(field1.getText().toString()) + Float.parseFloat(field2.getText().toString());
                    break;
                case R.id.Operation2:
                    result = Float.parseFloat(field1.getText().toString()) - Float.parseFloat(field2.getText().toString());
                    break;
                case R.id.Operation3:
                    result = Float.parseFloat(field1.getText().toString()) / Float.parseFloat(field2.getText().toString());
                    break;
                case R.id.Operation4:
                    result = Float.parseFloat(field1.getText().toString()) * Float.parseFloat(field2.getText().toString());
                    break;
            }

        return Float.toString(result);
    }
    private int getRadioButtonId(){
        if(operation1.isChecked()){
            return R.id.Operation1;
        }
        if(operation2.isChecked()){
            return R.id.Operation2;
        }
        if(operation3.isChecked()){
            return R.id.Operation3;
        }
        if(operation4.isChecked()){
            return R.id.Operation4;
        }
        return -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        field1.setText(null);
        field2.setText(null);
        resultField.setText(R.string.ResultField);
        return true;
        }
    }

