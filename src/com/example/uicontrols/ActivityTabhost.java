package com.example.uicontrols;

import android.app.TabActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class ActivityTabhost extends TabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabhost);
		
		Resources resources = getResources();
		
		// 获取TabHost组件
		TabHost host = getTabHost();
		TabSpec spec;
		
		// 创建一个选项卡并设置内容，indicator是Lable上显示的名字
		spec = host.newTabSpec("tag1").setIndicator("选项卡1").setContent(R.id.txt1);
		// 将选项卡加入到TabHost容器中
		host.addTab(spec);

		// 创建一个选项卡并设置内容
		spec = host.newTabSpec("tag2").setIndicator("选项卡2").setContent(R.id.txt2);
		// 将选项卡加入到TabHost容器中
		host.addTab(spec);
		
		// 创建一个选项卡并设置内容
		spec = host.newTabSpec("tag3").setIndicator("选项卡3").setContent(R.id.txt3);
		// 将选项卡加入到TabHost容器中
		host.addTab(spec);
		
		// 设置当前活动选项卡
		host.setCurrentTab(0);
		
		// 注册选项卡改变监听器
		host.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// 显示
				Toast.makeText(ActivityTabhost.this, tabId, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
