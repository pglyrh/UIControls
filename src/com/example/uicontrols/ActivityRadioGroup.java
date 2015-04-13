package com.example.uicontrols;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class ActivityRadioGroup extends Activity {

	//定义RadioButton
	private RadioButton button1;
	private RadioButton button2;
	private RadioButton button3;
	private Button submitButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//绑定新建布局
		setContentView(R.layout.activity_radio_group);
		
		//获得控件
		button1 = (RadioButton) findViewById(R.id.radio1);
		button2 = (RadioButton) findViewById(R.id.radio2);
		button3 = (RadioButton) findViewById(R.id.radio3);
		submitButton = (Button) findViewById(R.id.submitButton);
		
		//添加事件
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (button1.isChecked()) {
					//创建提示框
					new AlertDialog.Builder(ActivityRadioGroup.this).setTitle("选择的答案")
						.setMessage("选择的答案是1").setPositiveButton("确认", null).show();
				}else if (button2.isChecked()) {
					//创建提示框
					new AlertDialog.Builder(ActivityRadioGroup.this).setTitle("选择的答案")
						.setMessage("选择的答案是2").setPositiveButton("确认", null).show();
				}else if (button3.isChecked()) {
					//创建提示框
					new AlertDialog.Builder(ActivityRadioGroup.this).setTitle("选择的答案")
						.setMessage("选择的答案是3").setPositiveButton("确认", null).show();
				}
			}
		});
	}
	
}
