package com.example.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class ActivityRatingBar extends Activity {
	private RatingBar ratingBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_rating_bar);
		
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		
		ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				//������ʾ�򣬳��ִ�ֽ��
				Toast.makeText(ActivityRatingBar.this, "����Ϊ��"+rating, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
