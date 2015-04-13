package com.example.uicontrols;

import android.R.anim;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class ActivityAnimation extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		
		// Frame Animation
		ImageView imageView = (ImageView) findViewById(R.id.imageViewAnimation);
		// 设置动画
		imageView.setBackgroundResource(R.anim.frame);
		// 获取动画
		final AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
		// 点击按钮
		Button buttonFrame = (Button) findViewById(R.id.buttonAnimationFrame);
		buttonFrame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animationDrawable.start();
			}
		});
		
		// Tween Animation
		final String[] entries = {"Alpha","Scale","Translate","Rotate"};
		Spinner spinner = (Spinner) findViewById(R.id.spinnerAnimation);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,entries);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Animation alphaAnimation = AnimationUtils.loadAnimation(ActivityAnimation.this, R.anim.alpha);
					findViewById(R.id.imageViewAnimationTween).startAnimation(alphaAnimation);
					break;
				case 1:
					Animation scaleAnimation = AnimationUtils.loadAnimation(ActivityAnimation.this, R.anim.scale);
					findViewById(R.id.imageViewAnimationTween).startAnimation(scaleAnimation);
					break;
				case 2:
					Animation translateAnimation = AnimationUtils.loadAnimation(ActivityAnimation.this, R.anim.translate);
					findViewById(R.id.imageViewAnimationTween).startAnimation(translateAnimation);
					break;
				case 3:
					Animation rotateAnimation = AnimationUtils.loadAnimation(ActivityAnimation.this, R.anim.rotate);
					findViewById(R.id.imageViewAnimationTween).startAnimation(rotateAnimation);
					break;

				default:
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
