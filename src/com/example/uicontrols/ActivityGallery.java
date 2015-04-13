package com.example.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Gallery;

public class ActivityGallery extends Activity {
	private Gallery gallery;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//Gallery现已被HorizontalScrollView替代
		setContentView(R.layout.activity_gallery);
		gallery = (Gallery) findViewById(R.id.gallery);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		for (int i = 0; i < 20; i++) {
			adapter.add("hello"+i);
		}
		gallery.setAdapter(adapter);
	}
}
