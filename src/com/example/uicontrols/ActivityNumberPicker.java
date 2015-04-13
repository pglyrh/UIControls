package com.example.uicontrols;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Toast;

public class ActivityNumberPicker extends Activity {
	NumberPicker np1;
    //定义最低价格、最高价格的初始值
    int minPrice=25;
    private Button numberButton;
    private NumberPicker np2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);
        np1=(NumberPicker)findViewById(R.id.np1);
        //设置np1的最小值和最大值
        np1.setMinValue(10);
        np1.setMaxValue(50);
        //设置np1的当前值
        np1.setValue(minPrice);
        np1.setOnValueChangedListener(new OnValueChangeListener(){

            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                    int newVal) {
                // TODO Auto-generated method stub
                minPrice=newVal;
//                showSelectedPrice();
            }
            
        });
        
        
        
        numberButton = (Button) findViewById(R.id.buttonNumberPicker);
        numberButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(
						R.layout.activity_1st_user_list_number_picker,
						(ViewGroup) findViewById(R.id.dialog));
				np2 = (NumberPicker)layout.findViewById(R.id.np2);
//				np2 = (NumberPicker)inflater.inflate(R.layout.activity_1st_user_list_number_picker, 
//						(ViewGroup) findViewById(R.id.np2));
		        np2.setMinValue(80);
		        np2.setMaxValue(90);
				
				new AlertDialog.Builder(ActivityNumberPicker.this)
						.setTitle("自定义布局").setView(layout)
						.setPositiveButton("确定", null)
						.setNegativeButton("取消", null).show();

			}
		});
    }
    
    private void showSelectedPrice()
    {
        Toast.makeText(this, "您选择的价格为："+minPrice
                , Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

}
