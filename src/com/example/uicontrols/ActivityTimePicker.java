package com.example.uicontrols;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class ActivityTimePicker extends Activity {
	
	private Button buttonSelectTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_picker);
		
		buttonSelectTime = (Button) findViewById(R.id.buttonSelectTime);
		buttonSelectTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//����ʱ��ѡ����
				//TimePickerDialog(ActivityTimePicker.this, callBack, hourOfDay, minute, is24HourView)
				new TimePickerDialog(ActivityTimePicker.this, new TimePickerDialog.OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						//ѡ����ɺ�
						buttonSelectTime.setText(String.format("%s:%s", timeFormat(hourOfDay),timeFormat(minute)));
					}
				}, 11, 11, true).show();
			}
		});
	}
	
	//������ʹ��λ����ʱ����Գ��ֳ���λ��
	public String timeFormat(int value){
		if (value >=10) {
			return ""+value;
		}else {
			return "0"+value;
		}
	}
}
