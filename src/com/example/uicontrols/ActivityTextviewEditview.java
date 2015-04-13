package com.example.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTextviewEditview extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textview_editview);
		TextView textView = (TextView) findViewById(R.id.text);
		textView.setSelected(true);
	}
}
