package com.example.uicontrols;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class ActivityCustomBmiView extends Activity implements Runnable{
	private static BmiView bmiView;
	private EditText editText;
	private Button button, buttonpdf;
	private static final int COMPLETED = 0;  

	private static Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == COMPLETED) {
				bmiView.invalidate();
			}
		}
	};  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_bmi_view);
		bmiView = (BmiView) findViewById(R.id.bmiView);
		editText = (EditText) findViewById(R.id.editTextBmi);
		button = (Button) findViewById(R.id.buttonBmi);
		buttonpdf = (Button) findViewById(R.id.buttonPdf);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				System.out.println("........textview....."+editText.getText().toString());
				bmiView.setBallNumber(editText.getText().toString());
				new Thread(bmiView).start();
			}
		});
		
		buttonpdf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createPDF();
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// 开启线程
//		new Thread(this).start();
//		System.out.println("onresume.........");
		new Thread(bmiView).start();
	}
	
	public static int getCompeted(){
		return COMPLETED;
	}
	
	public static Handler getHandler(){
		return handler;
	}
	
	@Override
	public void run() {
		//现将angle置为0
		bmiView.Angle = 0;
 
		while (!Thread.currentThread().isInterrupted()) {
			try {
				//进程刷新速度
				Thread.sleep(2);
				//angle2的正负，控制旋转方向
				if (bmiView.Angle2 < 0) {
					bmiView.Angle = (float) (bmiView.Angle - 0.1);
					if (bmiView.Angle <= bmiView.Angle2) {
						//减到指定角度停止进程
						Thread.currentThread().interrupt();
					}
				}else {
					bmiView.Angle = (float) (bmiView.Angle + 0.1);
					if (bmiView.Angle >= bmiView.Angle2) {
						//加到指定角度停止进程
						Thread.currentThread().interrupt();
					}
				}
				
				//处理完成后给handler发送消息  
		        Message msg = new Message();  
		        msg.what = COMPLETED;  
		        handler.sendMessage(msg); 
			} catch (Exception e) {
				// 可让当前进程停止
				// Thread.currentThread().interrupt();
				// 打印下错误
				System.out.println("exception........."+e.getMessage());
			}

		}
//		System.out.println("finish.........");
	}
//	//现将angle置为0
//	bmiView.Angle = 0;
//	System.out.println("bmiView.Angle2........."+bmiView.Angle2);
//	while (!Thread.currentThread().isInterrupted()) {
//		try {
//			//进程刷新速度
//			Thread.sleep(2);
//			//angle2的正负，控制旋转方向
//			if (bmiView.Angle2 < 0) {
//				bmiView.Angle = (float) (bmiView.Angle - 0.1);
//				if (bmiView.Angle <= bmiView.Angle2) {
//					//减到指定角度停止进程
//					Thread.currentThread().interrupt();
////						System.out.println("a........");
//				}
//			}else {
//				bmiView.Angle = (float) (bmiView.Angle + 0.1);
//				if (bmiView.Angle >= bmiView.Angle2) {
//					//加到指定角度停止进程
//					Thread.currentThread().interrupt();
////						System.out.println("b........");
//				}
//			}
////				System.out.println(".............angle: "+Angle);
//			
//			bmiView.invalidate();
////				System.out.println("bmiView.invalidate().........");
//		} catch (Exception e) {
//			// 可让当前进程停止
//			// Thread.currentThread().interrupt();
//			// 打印下错误
//			System.out.println("exception........."+e.getMessage());
//		}
//		
//	}
////		System.out.println("finish.........");
//}
	public void createPDF() {
		Document doc = new Document();
		System.out.println("Document.........");
		try {
			String path = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/virtyPdf";

			File dir = new File(path);
			if (!dir.exists()){
				dir.mkdirs();
				System.out.println("makedir.........");
			}

			File file = new File(dir, "sample.pdf");
			FileOutputStream fOut = new FileOutputStream(file);

			PdfWriter.getInstance(doc, fOut);

			// open the document
			doc.open();

			Paragraph p1 = new Paragraph(
					"Hi! I am generating my first PDF using DroidText----joe");
			Font paraFont = new Font(Font.COURIER);
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			p1.setFont(paraFont);

			// add paragraph to document
			doc.add(p1);

			Paragraph p2 = new Paragraph(
					"This is an example of a simple paragraph");
			Font paraFont2 = new Font(Font.COURIER, 14.0f, Color.GREEN);
			p2.setAlignment(Paragraph.ALIGN_CENTER);
			p2.setFont(paraFont2);

			doc.add(p2);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext()
					.getResources(), R.drawable.pic1);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			Image myImg = Image.getInstance(stream.toByteArray());
			myImg.setAlignment(Image.MIDDLE);

			// add image to document
			doc.add(myImg);

			// set footer
			Phrase footerText = new Phrase("This is an example of a footer");
			HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
			doc.setFooter(pdfFooter);

		} catch (DocumentException de) {

		} catch (IOException e) {

		} finally {
			doc.close();
		}

	}
}
