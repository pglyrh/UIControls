package com.example.uicontrols;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ActivityProgressBar extends Activity {
	//ProgressBar����
	private ProgressBar progressBar;
	//���̰ٷֱ�
	private int progress = 0;
	//��ʱ��
	private Timer timer = null;
	private TimerTask timerTask = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_bar);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar3);
		progressBar.setMax(100);
		startTimer();
	}
	
	//��д���ڷ���
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		stopTimer();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		startTimer();
	}
	
	//������ʱ��
	public void startTimer(){
		if (timer == null) {
			timer = new Timer();
			timerTask = new TimerTask() {
				
				@Override
				public void run() {
					//����progressֵ
					progress ++;
					if (progress>100) {
						stopTimer();
					}else {
						progressBar.setProgress(progress);
					}				
				}
			};
			//������ʱ��
			timer.schedule(timerTask, 1000, 100);
		}
	}
	
	//ֹͣ��ʱ��
	public void stopTimer(){
		if (timer != null) {
			timerTask.cancel();
			timer.cancel();
			
			timerTask = null;
			timer = null;
		}
	}
}
