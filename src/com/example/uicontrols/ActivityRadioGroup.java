package com.example.uicontrols;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class ActivityRadioGroup extends Activity {

	//����RadioButton
	private RadioButton button1;
	private RadioButton button2;
	private RadioButton button3;
	private Button submitButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//���½�����
		setContentView(R.layout.activity_radio_group);
		
		//��ÿؼ�
		button1 = (RadioButton) findViewById(R.id.radio1);
		button2 = (RadioButton) findViewById(R.id.radio2);
		button3 = (RadioButton) findViewById(R.id.radio3);
		submitButton = (Button) findViewById(R.id.submitButton);
		
		//����¼�
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (button1.isChecked()) {
					//������ʾ��
					new AlertDialog.Builder(ActivityRadioGroup.this).setTitle("ѡ��Ĵ�")
						.setMessage("ѡ��Ĵ���1").setPositiveButton("ȷ��", null).show();
				}else if (button2.isChecked()) {
					//������ʾ��
					new AlertDialog.Builder(ActivityRadioGroup.this).setTitle("ѡ��Ĵ�")
						.setMessage("ѡ��Ĵ���2").setPositiveButton("ȷ��", null).show();
				}else if (button3.isChecked()) {
					//������ʾ��
					new AlertDialog.Builder(ActivityRadioGroup.this).setTitle("ѡ��Ĵ�")
						.setMessage("ѡ��Ĵ���3").setPositiveButton("ȷ��", null).show();
				}
			}
		});
	}
	
}
