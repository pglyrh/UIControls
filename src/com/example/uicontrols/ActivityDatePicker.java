package com.example.uicontrols;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class ActivityDatePicker extends Activity {
	
	//控件,显示选择的日期
	private Button selectDateButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_date_picker);
		
		selectDateButton = (Button) findViewById(R.id.buttonSelectDate);
		
		selectDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//弹出一个日期选择器对话框
				//DatePickerDialog(context, callBack, year, monthOfYear, dayOfMonth)
				new DatePickerDialog(ActivityDatePicker.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						//按钮显示选择的日期
						selectDateButton.setText(String.format("%d:%d:%d", 
								year,monthOfYear,dayOfMonth));
					}
				}, 2014, 1, 1).show();
			}
		});
	}
}
