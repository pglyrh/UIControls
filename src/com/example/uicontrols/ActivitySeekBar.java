package com.example.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class ActivitySeekBar extends Activity {
	private SeekBar seekBar;
	private TextView textViewSeekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_seek_bar);
		
		textViewSeekBar = (TextView) findViewById(R.id.textViewSeekBar);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setMax(5);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			//��д�˺�����������λ�øı�ʱ����
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewSeekBar.setText(String.format("��ǰ����Ϊ��%d", progress));
			}
		});
	}
}
