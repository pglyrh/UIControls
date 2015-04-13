package com.example.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class ActivityAutoCompleteTextView extends Activity {

	private AutoCompleteTextView autoCompleteTextView;
	private MultiAutoCompleteTextView multiAutoCompleteTextView;
	private ArrayAdapter<String> adapter;
	private ArrayAdapter<String> multiAdapter;
	
	//数据数组
	private String[] strs = new String[]{"hello","hello1","hello2","hello3","hello4","hello5",
			"java","javascript","c#","c++","php","python"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_auto_complete_text_view);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
		multiAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
		
		adapter.addAll(strs);
		multiAdapter.addAll(strs); 
		
		autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
		multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
		multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		
		autoCompleteTextView.setAdapter(adapter);
		multiAutoCompleteTextView.setAdapter(multiAdapter);
	}
}
