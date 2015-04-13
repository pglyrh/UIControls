package com.example.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityListView extends Activity {
	private String[] strs = new String[]{"hello","hello1","hello2","hello3","hello4","hello5",
			"java","javascript","c#","c++","php","python"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
		adapter.addAll(strs);
		
		ListView listView = (ListView) findViewById(R.id.customListview);
		listView.setAdapter(adapter);
	}
}
