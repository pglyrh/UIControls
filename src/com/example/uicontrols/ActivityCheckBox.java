package com.example.uicontrols;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ActivityCheckBox extends Activity {

		//���
		private CheckBox checkBox1;
		private CheckBox checkBox2;
		private CheckBox checkBox3;
		private CheckBox checkBox4;
		private Button submitButton;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_checkbox);
			
			//��ÿؼ�
			checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
			checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
			checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
			checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
			submitButton = (Button) findViewById(R.id.checkBoxSubmitButton);
			
			//�¼�
			submitButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String str = "ѡ���ʳ���ǣ�\n";
					if (checkBox1.isChecked()) {
						str += "��\n";
					}if (checkBox2.isChecked()) {
						str += "�׷�\n";
					}if (checkBox3.isChecked()) {
						str += "ˮ��\n";
					}if (checkBox4.isChecked()) {
						str += "�߲�\n";
					}

					//������ʾ
					new AlertDialog.Builder(ActivityCheckBox.this).setTitle("ѡ��Ľ��:")
					.setMessage(str).setPositiveButton("ȷ��", null).show();
				}
			});
		}
}
