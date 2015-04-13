package com.example.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class ActivityGridView extends Activity {

	private ArrayAdapter<String> adapter;
	private GridView gridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view);
		
		gridView = (GridView) findViewById(R.id.gridView);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		
		gridView.setAdapter(adapter);
		
		//Ìí¼ÓÊý¾Ý
		for (int i = 0; i < 60; i++) {
			adapter.add("hello"+i);
		}
	}
	
}
