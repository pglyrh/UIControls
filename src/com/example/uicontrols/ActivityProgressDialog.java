package com.example.uicontrols;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ActivityProgressDialog extends Activity {
	private Button showProgressDialogButton;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_progress_dialog);
		
		showProgressDialogButton = (Button) findViewById(R.id.buttonShowProgressDialog);
		showProgressDialogButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 呈现进度对话框，3s后消失
				progressDialog = ProgressDialog.show(ActivityProgressDialog.this, 
						"加载中", "正在加载，请稍等...");
				
				new Thread(){
					public void run() {
						try {
							Thread.sleep(3000);
							progressDialog.dismiss();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.start();
			}
		});
	}
}
