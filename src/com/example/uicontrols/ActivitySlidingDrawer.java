package com.example.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TextView;

public class ActivitySlidingDrawer extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sliding_drawer);
		
		final TextView handle = (TextView) findViewById(R.id.handle);
		final SlidingDrawer slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);
		final TextView txt = (TextView) findViewById(R.id.textViewSliding); 
		
		// 内容部分被完全打开
		slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				handle.setText("向下");
			}
		});
		
		// 内容部分被完全关闭
		slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				handle.setText("向上");
			}
		});
		
		slidingDrawer.setOnDrawerScrollListener(new OnDrawerScrollListener() {
			
			// 开始滚动
			@Override
			public void onScrollStarted() {
				// TODO Auto-generated method stub
				txt.setText(R.string.app_name);
			}
			
			// 滚动结束
			@Override
			public void onScrollEnded() {
				// TODO Auto-generated method stub
				txt.setText("隐藏内容");
			}
		});
	}
}
