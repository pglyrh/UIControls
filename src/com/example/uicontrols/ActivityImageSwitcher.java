package com.example.uicontrols;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ActivityImageSwitcher extends Activity {
	private ImageSwitcher imageSwitcher;
	//��־
	private int picNum = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_switcher);
		
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
				
		//����imageSwitcher�����ݹ���
		imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
			
			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				//����ImageView����
				return new ImageView(ActivityImageSwitcher.this);
			}
		});
		showCurrentImage(picNum);
		
		//����Ч��
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(ActivityImageSwitcher.this, 
				android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(ActivityImageSwitcher.this, 
				android.R.anim.fade_out));
		
		//����¼����л���һ��ͼƬ
		imageSwitcher.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showCurrentImage((picNum++)%4);
			}
		});
		
	}
	
	private void showCurrentImage(int picNum){
		//����ͨ����Դ�����R�е�Id
		Class<com.example.uicontrols.R.drawable> cls = R.drawable.class;
		String imgString = "pic"+(picNum+1);
		try {
			int value = cls.getDeclaredField(imgString).getInt(null);
			//System.out.println("value"+value);
			imageSwitcher.setImageResource(value);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*switch (picNum) {
		case 0:
			imageSwitcher.setImageResource(R.drawable.pic1);
			break;
		case 1:
			imageSwitcher.setImageResource(R.drawable.pic2);
			break;
		case 2:
			imageSwitcher.setImageResource(R.drawable.pic3);
			break;
		case 3:
			imageSwitcher.setImageResource(R.drawable.pic4);
			break;
		default:
			break;
		}*/
	}
}
