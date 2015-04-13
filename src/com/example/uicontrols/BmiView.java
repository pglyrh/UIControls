package com.example.uicontrols;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class BmiView extends View implements OnTouchListener, Runnable {
	private final Context context;
	// 屏幕长、宽
	int width, height;
	// 屏幕中心x， y
	int centerX;
	int centerY;
	// 分别为刻度盘、刻度、小球绘制区域
	RectF rectF1, rectF2, rectF3;
	// 刻度绘制区域的原始值（调整过的）
	float rx, ry;
	float rl, rr, rt, rb;

	// 是否为第一次加载，用于初始化
	boolean flag = false;

	// 其他单位数据
	int pointInterval, ballInterval, delta, delta2;

	// ball上的内容
	String ballNumber = "19.5";
	// 刻度盘数据
	String meterNumber = "60.0";

	// 图片旋转角度
	float Angle,Angle2 = 0;
	// 构建Matrix对象
	Matrix mMatrix = new Matrix();
	// 声明Bitmap对象（刻度）
	Bitmap mBitQQ, mBitQQ2 = null;
	int mBitQQX = 0;
	int mBitQQY = 0;

	// 小球图片
	Bitmap bitmapBall = null;
	
	public BmiView(Context context) {
		this(context, null);
	}

	public BmiView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// 装载刻度图片资源
		mBitQQ = BitmapFactory.decodeResource(getResources(), R.drawable.kedu);
		mBitQQX = mBitQQ.getWidth();
		mBitQQY = mBitQQ.getHeight();
		
		// 小球图片
		bitmapBall = BitmapFactory.decodeResource(getResources(),
				R.drawable.ball);
		computeAngel();		
//		// 开启线程
//		new Thread(this).start();
	}

	// 设置内容
	public void setBallNumber(String num) {
		this.ballNumber = num;
		//计算
		computeAngel();
	}

	public String getBallNumber() {
		return this.ballNumber;
	}
	public void setMeterNumber(String num) {
		this.meterNumber = num;
	}
	
	public String getMeterNumber() {
		return this.meterNumber;
	}

	// 设置旋转角度
	public void setAngle(float angle) {
		this.Angle = angle;
	}

	public float getAngle() {
		return this.Angle;
	}
	// 设置小球图片
	public void setBallImage(Bitmap bitmap){
		this.bitmapBall = bitmap;
	}

	// 初始化view的一些参数
	public void initView() {
		this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		// 屏幕尺寸
		centerX = getWidth() / 2;
		centerY = getHeight() / 2;
		width = getWidth();
		height = getHeight();

		// 调整间隔
		pointInterval = (int) (width / 2.05);
		delta = width / 65;
		delta2 = width / 45;
		ballInterval = (int) (width / 9.6);

		if (!flag) {
			// 刻度
			rectF2 = new RectF(centerX - pointInterval + delta2, centerY
					- pointInterval + delta, centerX + pointInterval - delta2,
					centerY + pointInterval - delta);
			// 刻度盘
			rectF1 = new RectF(centerX - width / 2, rectF2.top, centerX + width
					/ 2, centerY);

			// 记录第一次刻度的绘制尺寸
			rx = rectF2.width();
			ry = rectF2.height();
			rl = rectF2.left;
			rr = rectF2.right;
			rt = rectF2.top;
			rb = rectF2.bottom;
			// System.out.println("rx:" + rx);
			// System.out.println("ry:" + ry);

			// 圆球
			rectF3 = new RectF(centerX - width / 11,
					rectF1.bottom - width / 16, centerX + width / 11,
					rectF1.bottom - width / 16 + width / 11 + width / 11);

			// 更改标志位
			flag = true;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		initView();

		// 绘制刻度盘
		drawMeter(rectF1, canvas);

		// 绘制刻度
		drawKedu(rectF2, canvas);

		// 绘制小球
		drawBall(rectF3, ballInterval, canvas);
		
		drawText(canvas);
		// drawText(rectF1, 0, canvas);

		drawMeterNumber(canvas);
		
		super.onDraw(canvas);
	}

	// 绘制刻度盘
	public void drawMeter(RectF oval1, Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
				R.drawable.weight_record_meter), null, oval1, paint);
	}

	/**
	 * 动态构建旋转矩阵Matrix对象
	 * 
	 * @param matrix
	 *            需要计算的矩阵
	 * @param canvas
	 *            画布
	 * @param degrees
	 *            图片旋转的角度，正值为顺时针，负值为逆时针
	 * @param pivotX
	 *            轴心的X坐标
	 * @param pivotY
	 *            轴心的Y坐标
	 */
	private Matrix getMyMatrix(Matrix matrix, float degrees, int pivotX,
			int pivotY) {
		// 重置Matrix
		matrix.reset();
		float cosValue = (float) Math.cos(Math.PI / (180 / degrees));
		float sinValue = (float) Math.sin(Math.PI / (180 / degrees));
		// 设置旋转矩阵值
		matrix.setValues(new float[] { cosValue, -sinValue, pivotX, sinValue,
				cosValue, pivotY, 0, 0, 1 });
		return matrix;
	}

	public void drawKedu(RectF oval1, Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);

		// //重置mMatrix对象
		mMatrix.reset();
		mMatrix.setTranslate(rectF2.centerX(), rectF2.centerY()); // 设置图片的旋转中心，即绕（X,Y）这点进行中心旋转
		// //设置旋转
		mMatrix.setRotate(Angle);

		// 生成旋转后的新图片
		mBitQQ2 = Bitmap.createBitmap(mBitQQ, 0, 0, mBitQQX, mBitQQY, mMatrix,
				true);
		// 新图片的宽、高
		int x = mBitQQ2.getWidth();
		int y = mBitQQ2.getHeight();
		// 与原图片的差距，并更改绘制区域，使刻度看起来大小未变
		float dx = (x - mBitQQX) * rx / mBitQQX;
		float dy = (y - mBitQQY) * ry / mBitQQY;

		// 重置刻度的外围轮廓
		oval1.left = rl - dx / 2;
		oval1.top = rt - dy / 2;
		oval1.right = rr + dx / 2;
		oval1.bottom = rb + dy / 2;

		// 绘制图片
		canvas.drawBitmap(mBitQQ2, null, oval1, paint);
	}

	// 绘制圆球
	public void drawBall(RectF oval1, int width, Canvas canvas) {

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		canvas.drawBitmap(
				bitmapBall,
				null, oval1, paint);
		// paint.setARGB(255, 0, 0, 0);
		// paint.setStyle(Paint.Style.STROKE);
		// paint.setStrokeWidth(2);
		// // canvas.drawRect(oval1, paint);
		// // //ball上的内容
		// RectF itemOval=new RectF(oval1.left, oval1.top,
		// oval1.right,oval1.top+oval1.height()/3);
		// canvas.drawRect(itemOval, paint);
		// //让文字居中。。。。。
		// // canvas.drawText(ballItem, itemOval, y, paint);
		// RectF numOval=new RectF(oval1.left, itemOval.bottom,
		// oval1.right,itemOval.bottom+oval1.height()/3);
		// canvas.drawRect(numOval, paint);
		// RectF unitOval=new RectF(oval1.left, numOval.bottom,
		// oval1.right,oval1.bottom);
		// canvas.drawRect(unitOval, paint);
		// canvas.drawText(ballItem, oval1.centerX()-20, oval1.centerY()-20,
		// paint);
		// canvas.drawText(ballNumber, oval1.centerX()-20, oval1.centerY(),
		// paint);
		// canvas.drawText(ballUnit, oval1.centerX()-20, oval1.centerY()+20,
		// paint);
	}

	// 绘制圆球上的数据
	public void drawText(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTextAlign(Align.CENTER);
		paint.setARGB(255, 255, 255, 255);
//		paint.setStyle(Paint.Style.STROKE);
//				 paint.setStrokeWidth(2);
		RectF itemOval=new RectF(rectF3.left, rectF3.top,rectF3.right,rectF3.top+rectF3.height()/2);
//		canvas.drawRect(itemOval, paint);
		
		//bmi小球
		paint.setTextSize(22);
		canvas.drawText("BMI", itemOval.centerX(), itemOval.bottom-itemOval.width()/15, paint);
//		canvas.drawText("BMI", itemOval.centerX()-itemOval.width()/4, itemOval.bottom-itemOval.width()/15, paint);
		RectF itemOval2=new RectF(rectF3.left, rectF3.bottom-rectF3.height()/2,rectF3.right,rectF3.bottom);
		canvas.drawText(ballNumber, itemOval.centerX(), itemOval2.bottom-itemOval2.width()/4, paint);
//		canvas.drawText(ballNumber, (float)(itemOval.centerX()-itemOval.width()/3.8), itemOval2.bottom-itemOval2.width()/4, paint);
//		canvas.drawRect(itemOval2, paint);
		
//		//刻度盘
//		paint.setARGB(255, 0, 0, 0);
//		paint.setStyle(Paint.Style.STROKE);
//		paint.setStrokeWidth(2);
////		RectF meterNumberOval=new RectF(rectF1.left, rectF1.top,rectF1.right,rectF1.bottom);
////		RectF meterNumberOval=new RectF((float)(centerX-width/4.4), rectF1.top+height/9,
////				(float)(centerX+width/4.4),rectF1.bottom-height/13);
//		RectF meterNumberOval=new RectF((float)(centerX-width/4.4), (float)(rectF1.top+width/6.2),
//				(float)(centerX+width/4.4),(float)(rectF1.bottom-width/9.2));
////		canvas.drawRect(meterNumberOval, paint);
//		// 绘制盘上数据
//		paint.setTextSize(65);
//		canvas.drawText("000.00", meterNumberOval.left,meterNumberOval.centerY()+meterNumberOval.height()/4, paint);
//		Path path = new Path();  
//		  
//		path.moveTo(meterNumberOval.left,meterNumberOval.centerY()); //设定起始点  
//		path.lineTo(meterNumberOval.right,meterNumberOval.centerY());//第一条直线的终点，也是第二条直线的起点  
//		path.close();//闭环  
//		  
//		canvas.drawPath(path, paint);
//		canvas.drawTextOnPath("aaa", path, 0, 0, paint);
	}

	public void drawMeterNumber(Canvas canvas) {
		// 文字居中
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setARGB(255, 255, 255, 255);
		// 刻度盘
		//水平居中
		paint.setTextAlign(Align.CENTER);
		// 绘制盘上数据
		paint.setTextSize(65);
		// RectF meterNumberOval=new RectF(rectF1.left,
		// rectF1.top,rectF1.right,rectF1.bottom);
		// RectF meterNumberOval=new RectF((float)(centerX-width/4.4),
		// rectF1.top+height/9,
		// (float)(centerX+width/4.4),rectF1.bottom-height/13);
		RectF meterNumberOval = new RectF((float) (centerX - width / 4.4),
				(float) (rectF1.top + width / 6.2),
				(float) (centerX + width / 4.4),
				(float) (rectF1.bottom - width / 9.2));
		// canvas.drawRect(meterNumberOval, paint);
		FontMetricsInt fontMetrics = paint.getFontMetricsInt();
		int baseline = (int) (meterNumberOval.top
				+ (meterNumberOval.bottom - meterNumberOval.top
						- fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top);
		// 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(meterNumber, meterNumberOval.centerX(), baseline, paint);
		// canvas.drawText("000.00",
		// meterNumberOval.left,meterNumberOval.centerY()+meterNumberOval.height()/4,
		// paint);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// System.out.println("............x0"+arg0.getX());
		return true;
	}

	// 触摸，跳转，改变direct的值
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// 通过到达圆心的距离，和角度判断属于哪一个项目（方向）
			// 获得x, y
			float x = event.getX();
			float y = event.getY();
			// System.out.println("x..................."+x);
			// System.out.println("y..................."+y);

			// 重绘
			this.invalidate();
			return true;
		} else {
			return super.onTouchEvent(event);
		}
	}

	//计算转换角度
	public void computeAngel(){
		//将bmi转成float
		float bmi = Float.parseFloat(ballNumber);
		
		//计算delta
		float delta = 21 - bmi;
		
//		//转换成角度
		this.Angle2 = delta*15;
//		this.Angle2 = (24+delta)%24*15;
//		System.out.println("..........angel............."+Angle2);
//		float nowAngle = (24+delta)%24;
//		
//		return nowAngle;
	}
	
	// 线程
	@Override
	public void run() {
		//现将angle置为0
		Angle = 0;
		while (!Thread.currentThread().isInterrupted()) {
			try {
				//进程刷新速度
				Thread.sleep(2);
				//angle2的正负，控制旋转方向
				if (Angle2 < 0) {
					Angle = (float) (Angle - 0.1);
					if (Angle <= Angle2) {
						//减到指定角度停止进程
						Thread.currentThread().interrupt();
					}
				}else {
					Angle = (float) (Angle + 0.1);
					if (Angle >= Angle2) {
						//加到指定角度停止进程
						Thread.currentThread().interrupt();
					}
				}
//				System.out.println(".............angle: "+Angle);
				
//				invalidate();
				
				//处理完成后给handler发送消息  
		        Message msg = new Message();  
		        msg.what = ActivityCustomBmiView.getCompeted();  
		        ActivityCustomBmiView.getHandler().sendMessage(msg);
				
			} catch (Exception e) {
				// 可让当前进程停止
				// Thread.currentThread().interrupt();
				// 打印下错误
				System.out.println("exception........."+e.getMessage());
			}

		}
	}
}
