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
	//֪ͨ�������ڳ���֪ͨ
	private NotificationManager notificationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		
		showNotificationButton = (Button) findViewById(R.id.buttonShowNotification);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		//ȡ��
		notificationManager.cancel(R.layout.activity_notification);
		
		showNotificationButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����һ��֪ͨ����APIΪnew Notification.Builder
				Notification notification = new Notification(R.drawable.ic_launcher,
						"Ticker Text", System.currentTimeMillis());
				notification.setLatestEventInfo(ActivityNotification.this, "contentTitle", 
						"contentText", PendingIntent.getActivity(ActivityNotification.this, 
								1, getIntent(),0));
				
				//����
				notificationManager.notify(R.layout.activity_notification,notification);
			}
		});
	}
}
