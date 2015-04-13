package com.example.uicontrols;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPedometer extends Activity implements SensorEventListener, OnClickListener{

	private Button buttonStart, buttonRestart, buttonStop;
	private TextView textViewPedometer;
	private SensorManager sm;
	
	private int count = 0;
	private float lastPoint;
	private boolean flag = true;
	List<Sensor> allSensors;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedometer);
		buttonStart = (Button) findViewById(R.id.buttonPedometerStart);
		buttonRestart = (Button) findViewById(R.id.buttonPedometerRestart);
		buttonStop = (Button) findViewById(R.id.buttonPedometerStop);

		textViewPedometer = (TextView) findViewById(R.id.textViewPedometer);
		
		buttonStart.setOnClickListener(this);
		buttonRestart.setOnClickListener(this);
		buttonStop.setOnClickListener(this);
		
		sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		allSensors = sm.getSensorList(Sensor.TYPE_ALL);
		//迭代输出获得上的传感器
		for (Sensor sensor : allSensors) {
		//System.out.println(sensor.getName().toString());
		System.out.println(sensor.getName().toString());
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (flag)
	    {
	        lastPoint = event.values[1];
	        flag = false;
	    }
	    //  当两个values[1]值之差的绝对值大于8时认为走了一步
	    if (Math.abs(event.values[1] - lastPoint) > 8)
	    {
	    	System.out.println("yidiong..................");
	        //  保存最后一步时的values[1]的峰值
	        lastPoint = event.values[1];
	        //  将当前计数显示在TextView组件中
	        textViewPedometer.setText(String.valueOf(++count));
	    }
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String msg = "";
		 switch (v.getId())
		    {
		        //  开始计步
		        case R.id.buttonPedometerStart:
		        	System.out.println("start...........................");;
		            sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		            //  注册方向传感器
		            boolean a = sm.registerListener(this, sm
		                    .getDefaultSensor(Sensor.TYPE_ORIENTATION),
		                    SensorManager.SENSOR_DELAY_FASTEST);
		            System.out.println("a: "+a);
		            if (a) {
			            msg = "已经开始计步器.";
					}else {
						msg =  "您的手机暂不支持计步";
					}
		            break;
		        //  重置计步器
		        case R.id.buttonPedometerRestart:
		            count = 0;
		            msg = "已经重置计步器.";
		            break;
		        //  停止计步
		        case R.id.buttonPedometerStop:
		            //  注销方向传感器
		            sm.unregisterListener(this);
		            count = 0;
		            msg = "已经停止计步器.";
		            break;
		    }
		    textViewPedometer.setText(String.valueOf(count));
		    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

}
