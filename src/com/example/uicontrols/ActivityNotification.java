package com.example.uicontrols;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityNotification extends Activity {
	private Button showNotificationButton;
	//通知管理，用于呈现通知
	private NotificationManager notificationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		
		showNotificationButton = (Button) findViewById(R.id.buttonShowNotification);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		//取消
		notificationManager.cancel(R.layout.activity_notification);
		
		showNotificationButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//构建一个通知，新API为new Notification.Builder
				Notification notification = new Notification(R.drawable.ic_launcher,
						"Ticker Text", System.currentTimeMillis());
				notification.setLatestEventInfo(ActivityNotification.this, "contentTitle", 
						"contentText", PendingIntent.getActivity(ActivityNotification.this, 
								1, getIntent(),0));
				
				//呈现
				notificationManager.notify(R.layout.activity_notification,notification);
			}
		});
	}
}
