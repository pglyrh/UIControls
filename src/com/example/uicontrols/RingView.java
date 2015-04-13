package com.example.uicontrols;

import android.R.bool;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class RingView extends View implements OnTouchListener{
	 private final Context context;
	 //长、宽
	 int width, height;
	 //最外层圆环的宽度
	 int outRingWidth;
	 //箭头间隔， 小球间隔
	 int pointInterval, ballInterval;
	 //圆心x， y
	 int centerX;
	 int centerY;
	 RectF rectF1, rectF2, rectF3;
	 //指向方向，12点钟方向为0，顺势针增加，最大至5
	 int direct = 3;
	 boolean flag = false;
	 
	 //ball上的内容
	 String ballItem = "体重", ballNumber="60.0", ballUnit="kg";
	 private TextView textViewItem, textViewNumber, textViewUnit;
	 
	    public RingView(Context context) {  	          
	        this(context, null);  
	    }  
	  
	    public RingView(Context context, AttributeSet attrs) {  
	        super(context, attrs);  
	        this.context = context; 	    	
	    }  

	 //设置、获得方向
	 public void setDirect(int direct){
		 this.direct = direct;
	 }
	 public int getDirect(){
		 return this.direct;
	 }
	 //设置内容
	 public void setBallItem(String item){
		 this.ballItem = item;
	 }
	 public String getBallItem(){
		 return this.ballItem;
	 }
	 public void setBallNumber(String num){
		 this.ballNumber = num;
	 }
	 public String getBallNumber(){
		 return this.ballNumber;
	 }
	 public void setBallUnit(String unit){
		 this.ballUnit = unit;
	 }
	 public String getBallUnit(){
		 return this.ballUnit;
	 }
	    
	    //初始化view的一些参数
	    public void initView(){
	    	if (!flag) {
	    		this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	    		centerX = getWidth()/2;
//	    		centerX = px2dip(context, centerX);
	    		centerY = getHeight()/2;
//	    		centerY = px2dip(context, centerY);
	    		
	    		width = getWidth()-40;
//	    		centerX = px2dip(context, centerX);
	    		height = getHeight();
	    		
	    		outRingWidth = width/6;
	    		pointInterval = width/4+13;
	    		ballInterval = width/6;
	    		
	    		//最外层
	    		rectF1 = new RectF(centerX-width/2, centerY-width/2, centerX+width/2, centerY+width/2);
	    		//箭头外层
	    		rectF2 = new RectF(centerX-pointInterval,centerY-pointInterval,
	    				centerX+pointInterval,centerY+pointInterval);
	    		//圆球
	    		rectF3 = new RectF(centerX-ballInterval,centerY-ballInterval,
	    				centerX+ballInterval,centerY+ballInterval);
	    		
	    		flag = true;
			}
	    }
	    
	    @Override  
	    protected void onDraw(Canvas canvas) {  
	    	initView();
	    	
	    	drawRing(rectF1, outRingWidth, canvas);
	    	
	    	drawPoint(rectF2, 12, canvas);
	    	
	    	drawBall(rectF3, ballInterval, canvas);
	    	
//	    	drawText(rectF1, 0, canvas);
	    	drawText2(rectF1, 0, canvas);

//	        super.onDraw(canvas);  
//	    	drawMyRect(canvas);
	    }  
	    
	    //绘制外围圆环
	    public void drawRing(RectF oval1, int ringWidth, Canvas canvas){
	    	//内圆的矩形轮廓
	        RectF innerOval=new RectF(oval1.left+ringWidth, oval1.top+ringWidth,
	        		oval1.right-ringWidth,oval1.bottom-ringWidth);
	        
	        //设置画笔
	        Paint paint = new Paint();
	        paint.setAntiAlias(true);
	        
	        //绘制外圆
	        for (int i = 0; i < 6; i++) {
	        	//查看方向
				if (i == direct) {
					//指向的一块
			        paint.setARGB(255, 108, 191, 0);
				}else {
					paint.setARGB(255, 217, 217, 217);
				}
				canvas.drawArc(oval1, (240+i*60+1)%360, 58, true, paint);
			}
	        	          
	        //绘制内圆  
	        Paint innerPaint = new Paint();
	        innerPaint.setAntiAlias(true); //消除锯齿  
	        innerPaint.setARGB(255, 255, 255, 255); 
	        canvas.drawOval(innerOval, innerPaint);
	        
	    }
	    
	    public void drawMyRect(Canvas canvas){
	    	Paint paint = new Paint();
	    	paint.setColor(Color.GREEN);  
	        paint.setTextSize(16);  //设置字体大小  
	        canvas.drawRect(10, 10, 50, 8, paint);  
	        canvas.drawText("我没有旋转",50, 10, paint);  
	        //保存canvas之前的操作,在sava()和restore之间的操作不会对canvas之前的操作进行影响  
	        canvas.save() ;  
	          
	        //顺时针旋转30度  
	        canvas.rotate(30) ;  
	        canvas.drawColor(Color.WHITE);  
	        canvas.drawRect(50, 10, 80, 50, paint);  
	        //canvas.translate(20,20);  
	        canvas.drawText("我是旋转的",115,20, paint);  
	          
	        //复原之前save()之前的属性,并且将save()方法之后的roate(),translate()以及clipXXX()方法的操作清空  
	        canvas.restore();  
	          
	        //平移(20,20)个像素  
	        //canvas.translate(20,20);  
	        canvas.drawRect(80, 10, 110,30, paint);  
	        canvas.drawText("我没有旋转",115,20, paint);
	    }
	    
	    public void drawPoint(RectF oval1, int width, Canvas canvas){
	        //内圆矩形轮廓   
	    	RectF innerOval=new RectF(oval1.left+width, oval1.top+width,
	        		oval1.right-width,oval1.bottom-width);
	        
	    	//半径
	    	float h = (float) Math.sqrt(12*12-6*6);
//	    	System.out.println("hhhhhhhhhhhhhh"+h);
	    	float radius = innerOval.width()/2;
	    	float a = radius+23-1;
	    	
	    	float x, y;
	    	//设置画笔
	        Paint paint = new Paint();
	        
//	        canvas.drawRect(oval1, paint);
	        
	        paint.setAntiAlias(true);
	        paint.setARGB(255, 108, 191, 0);
	        
	      //外线
	        canvas.drawArc(oval1, (240+direct*60+1)%360, 58, true, paint);
	        
		    //画三角箭头
	        Path path = new Path();
	        switch (direct) {
			case 0:
				//12点方向
				path.moveTo(oval1.centerX(), oval1.top-h-1);// 此点为多边形的起点  
		        path.lineTo(oval1.centerX()-6, oval1.top+1);  
		        path.lineTo(oval1.centerX()+6, oval1.top+1);   
		        path.close(); // 使这些点构成封闭的多边形
				break;
			case 1:
				//1点到2点方向
				x = (float)(oval1.centerX()+Math.sqrt((a*a-(a*a)/4)));
				y = oval1.centerY()-a/2;
				path.moveTo(x, y);// 此点为多边形的起点  
				path.lineTo(x-12, y);  
				path.lineTo(x-6, y+h);   
				path.close(); // 使这些点构成封闭的多边形
				break;
			case 2:
				//4点到5点方向
				x = (float)(oval1.centerX()+Math.sqrt((a*a-(a*a)/4)));
				y = oval1.centerY()+a/2;
				path.moveTo(x, y);// 此点为多边形的起点  
				path.lineTo(x-12, y);  
				path.lineTo(x-6, y-h);   
				path.close(); // 使这些点构成封闭的多边形
				break;
			case 3:
				//6点方向
				path.moveTo(oval1.centerX(), oval1.bottom+h-1);// 此点为多边形的起点  
		        path.lineTo(oval1.centerX()-6, oval1.bottom-1);  
		        path.lineTo(oval1.centerX()+6, oval1.bottom-1);   
		        path.close(); // 使这些点构成封闭的多边形
				break;
			case 4:
				//7点到8点方向			
				x = (float)(oval1.centerX()-Math.sqrt((a*a-(a*a)/4)));
				y = oval1.centerY()+a/2;
				path.moveTo(x, y);// 此点为多边形的起点  
				path.lineTo(x+12, y);  
				path.lineTo(x+6, y-h);   
				path.close(); // 使这些点构成封闭的多边形
				break;
			case 5:
				//10点到11点方向			
				x = (float)(oval1.centerX()-Math.sqrt((a*a-(a*a)/4)));
				y = oval1.centerY()-a/2;
				path.moveTo(x, y);// 此点为多边形的起点  
				path.lineTo(x+12, y);  
				path.lineTo(x+6, y+h);   
				path.close(); // 使这些点构成封闭的多边形
				break;
			default:
				break;
			}
	        canvas.drawPath(path, paint);
	        
//	        //外线
//	        canvas.drawArc(oval1, (240+direct*60+1)%360, 58, true, paint);
	        
	        //绘制内线
	        Paint innerPaint = new Paint();
	        innerPaint.setAntiAlias(true); //消除锯齿  
	        innerPaint.setARGB(255, 255, 255, 255); 
	        canvas.drawOval(innerOval, innerPaint);
	    }
	    public void drawBall(RectF oval1, int width, Canvas canvas){
	    	//绘制内圆   
//	    	RectF innerOval=new RectF(oval1.left+width, oval1.top+width,
//	    			oval1.right-width,oval1.bottom-width);
	    	
	    	Paint paint = new Paint();
	    	paint.setAntiAlias(true);
	    	paint.setFilterBitmap(true);
	    	canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 
					R.drawable.ball), null, oval1, paint);
//	    	paint.setARGB(255, 0, 0, 0);
//	    	paint.setStyle(Paint.Style.STROKE);
//	    	paint.setStrokeWidth(2);
////	    	canvas.drawRect(oval1, paint);
////	    	//ball上的内容
//	    	RectF itemOval=new RectF(oval1.left, oval1.top,
//	    			oval1.right,oval1.top+oval1.height()/3);
//	    	canvas.drawRect(itemOval, paint);
//	    	//让文字居中。。。。。
////	    	canvas.drawText(ballItem, itemOval, y, paint);
//	    	RectF numOval=new RectF(oval1.left, itemOval.bottom,
//	    			oval1.right,itemOval.bottom+oval1.height()/3);
//	    	canvas.drawRect(numOval, paint);
//	    	RectF unitOval=new RectF(oval1.left, numOval.bottom,
//	    			oval1.right,oval1.bottom);
//	    	canvas.drawRect(unitOval, paint);
//	    	canvas.drawText(ballItem, oval1.centerX()-20, oval1.centerY()-20, paint);
//	    	canvas.drawText(ballNumber, oval1.centerX()-20, oval1.centerY(), paint);
//	    	canvas.drawText(ballUnit, oval1.centerX()-20, oval1.centerY()+20, paint);
	    }
	    
	  //绘制外围圆环
	    public void drawText(RectF oval1, int ringWidth, Canvas canvas){
	    	//设置画笔
	        Paint paint[] = new Paint[6];
	        	        
	    	//查看指向的方向，更改画笔的颜色
	    	for (int i = 0; i < 6; i++) {
	    		paint[i] = new Paint();
				if (i == direct) {
					paint[i].setARGB(255, 255, 255, 255);
				}else {
					paint[i].setARGB(255, 102, 102, 102);
				}
				paint[i].setAntiAlias(true);
//				float sizesp = px2sp(context, 30);
//				System.out.println("size........"+px2sp(context, sizesp));
		        paint[i].setTextSize(30);
//		        paint[i].setTextSize(sp2px(context, 13));
			}
	    	
//	        System.out.println("oval1.width()..........."+oval1.width());
	        
//	        float a = oval1.width()/5;
//	        float b = (float) (oval1.width()/8.7);
//	        
//	    	Path path;
//	    	
//	    	path = new Path();
//			path.addArc(oval1, 172, 60);
//	        canvas.drawTextOnPath("基础代谢", path, a, b, paint[5]);
//	    	
//	        canvas.drawText("体重", oval1.centerX()-30, oval1.centerY()-160, paint[0]);
//	        
//	        path = new Path();
//	        path.addArc(oval1, 295, 60);
//	        canvas.drawTextOnPath("脂肪率", path, a, b, paint[1]);
//	        
//	        path = new Path();
//	        path.addArc(oval1, 355, 60);
//	        canvas.drawTextOnPath("水分率", path, a, b, paint[2]);
//	        
//	        canvas.drawText("肌肉率", oval1.centerX()-45, oval1.centerY()+180, paint[3]);
//	        
//	        path = new Path();
//	        path.addArc(oval1, 120, 60);
//	        canvas.drawTextOnPath("骨量", path, a, b, paint[4]);
	        
//	        float a = oval1.width()/5;
	        float a = oval1.width()/5;
//	        a = dip2px(context, a);
	        float b = (float) (oval1.width()/8.7);
//	        b = dip2px(context, b);
//	        System.out.println("dp.......a........."+px2dip(context, a));
//	        System.out.println("dp.......b........."+px2dip(context, b));
	        
	        //修改体重和肌肉率的间隔
	        float c = (float) (oval1.width()/2.6);
	        float d = (float) (oval1.width()/2.3);
	        
//	        System.out.println("dp.......c........."+px2dip(context, c));
//	        System.out.println("dp.......d........."+px2dip(context, d));
	        
//	        System.out.println("dp.......30........."+px2dip(context, 30));
//	        System.out.println("dp.......45........."+px2dip(context, 45));
	        
	    	Path path;
	    	
	    	//环上的arc有问题。。不同机子，显示有差别，Path的问题， a,b有差别。。大小适配
	    	path = new Path();
			path.addArc(oval1, 172, 60);
//	        canvas.drawTextOnPath("基础代谢", path, a, b, paint[5]);
	        canvas.drawTextOnPath("基础代谢", path, dip2px(context, 55), dip2px(context, 31), paint[5]);
	    	
//	        canvas.drawText("体重", oval1.centerX()-30, oval1.centerY()-c, paint[0]);
//	        canvas.drawText("体重", oval1.centerX()-dip2px(context, 20), oval1.centerY()-dip2px(context, 105), paint[0]);
//	        float e = oval1.centerX()-30;
//	        System.out.println("e....dp........"+px2dip(context, e)); //130
//	        float e2 = oval1.centerY()-c;
//	        System.out.println("e2....dp........"+px2dip(context, e2)); //133
//	        e = px2dip(context, oval1.centerX());
//	        e = dip2px(context, dpValue)
//	        canvas.drawText("体重", e, e2, paint[0]);
	        canvas.drawText("体重", dip2px(context, 130), dip2px(context, 133), paint[0]);
	        
	        path = new Path();
	        path.addArc(oval1, 295, 60);
//	        canvas.drawTextOnPath("脂肪率", path, a, b, paint[1]);
	        canvas.drawTextOnPath("脂肪率", path, dip2px(context, 55), dip2px(context, 31), paint[1]);
	        
	        path = new Path();
	        path.addArc(oval1, 355, 60);
//	        canvas.drawTextOnPath("水分率", path, a, b, paint[2]);
	        canvas.drawTextOnPath("水分率", path, dip2px(context, 55), dip2px(context, 31), paint[2]);
	        
//	        float f = oval1.centerX()-45;
//	        System.out.println("f....dp........"+px2dip(context, f)); //120
//	        float f2 = oval1.centerY()+d; //357
//	        System.out.println("f2....dp........"+px2dip(context, f2));  
//	        canvas.drawText("肌肉率", oval1.centerX()-45, oval1.centerY()+d, paint[3]);
//	        canvas.drawText("肌肉率", oval1.centerX()-dip2px(context, 30), oval1.centerY()+dip2px(context, 119), paint[3]);
//	        canvas.drawText("肌肉率", f, f2, paint[3]);
	        canvas.drawText("肌肉率", dip2px(context, 120), dip2px(context, 357), paint[3]);
	        
	        path = new Path();
	        path.addArc(oval1, 120, 60);
	        canvas.drawTextOnPath("骨量", path, dip2px(context, 55), dip2px(context, 31), paint[4]);
//	        canvas.drawTextOnPath("骨量", path, a, b, paint[4]);
	        
	    }
	    
	    //绘制外围圆环
	    public void drawText2(RectF oval1, int ringWidth, Canvas canvas){
	    	//设置画笔
	    	Paint paint[] = new Paint[6];
	    	
	    	//查看指向的方向，更改画笔的颜色
	    	for (int i = 0; i < 6; i++) {
	    		paint[i] = new Paint();
	    		if (i == direct) {
	    			paint[i].setARGB(255, 255, 255, 255);
	    		}else {
	    			paint[i].setARGB(255, 102, 102, 102);
	    		}
	    		paint[i].setTextAlign(Align.CENTER);
	    		paint[i].setAntiAlias(true);
//				float sizesp = px2sp(context, 30);
//				System.out.println("size........"+px2sp(context, sizesp));
	    		paint[i].setTextSize(30);
//		        paint[i].setTextSize(sp2px(context, 13));
	    	}
	    	
	    	//外围、内围大小
			float out = rectF1.width()/2;
			float in = rectF1.width()/2-outRingWidth;
			
			//计算cos30的值
		    float cos = (float) Math.cos(Math.toRadians(30));
			
			//上半区
			float x1 = oval1.centerX()-in/2;
			float y1 = (float) (oval1.centerY()-out*1.06);
			float x4 = oval1.centerX()+in/2;
			float y4 = oval1.centerY()-cos*in;
			
			//下半区
			float x2 = oval1.centerX()-in/2;
			float y2 = oval1.centerY()+cos*in;
			float y3 = (float) (oval1.centerY()+out*1.06);;
			float x3 = oval1.centerX()+in/2;
	    	
			//上半区、下半区文字绘制区域
			RectF r1=new RectF(x1, y1,x4,y4);
			RectF r2=new RectF(x2, y2,x3,y3);
		
			FontMetricsInt fontMetrics = paint[0].getFontMetricsInt();
			//r1的baseline
			int baseline = (int) (r1.top
					+ (r1.bottom - r1.top
							- fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top);
			//r2的baseline
			int baseline2 = (int) (r2.top
					+ (r2.bottom - r2.top
							- fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top);
			canvas.save();
			// 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
			canvas.drawText("体重", r1.centerX(), baseline, paint[0]);
	        
	        canvas.rotate(-60, centerX, centerY);
	        canvas.drawText("基础代谢", r1.centerX(), baseline, paint[5]); 
	        
	        canvas.rotate(120, centerX, centerY);
	        canvas.drawText("脂肪率", r1.centerX(), baseline, paint[1]);
	        canvas.restore();
	        	        
	        canvas.save();
	        canvas.drawText("肌肉率", r2.centerX(), baseline2, paint[3]);
	        canvas.rotate(-60, centerX, centerY);
//	        canvas.drawRect(r2, paint[0]);  
	        canvas.drawText("水分率", r2.centerX(), baseline2, paint[2]); 
	        
	        canvas.rotate(120, centerX, centerY);
//	        canvas.drawRect(r2, paint[0]);  
	        canvas.drawText("骨量", r2.centerX(), baseline2, paint[4]);
	        canvas.restore();
	    	
	    }
	    
	    /** 
	     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
	     */  
	    public static int dip2px(Context context, float dpValue) {  
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (dpValue * scale + 0.5f);  
	    }
	    /** 
	     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
	     */  
	    public static int px2dip(Context context, float pxValue) {  
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (pxValue / scale + 0.5f);  
	    } 
	    /**
         * 将px值转换为sp值，保证文字大小不变
         * 
         * @param pxValue
         * @param fontScale
         *            （DisplayMetrics类中属性scaledDensity）
         * @return
         */ 
        public static int px2sp(Context context, float pxValue) { 
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
            return (int) (pxValue / fontScale + 0.5f); 
        } 
       
        /**
         * 将sp值转换为px值，保证文字大小不变
         * 
         * @param spValue
         * @param fontScale
         *            （DisplayMetrics类中属性scaledDensity）
         * @return
         */ 
        public static int sp2px(Context context, float spValue) { 
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
            return (int) (spValue * fontScale + 0.5f); 
        }
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
//			System.out.println("............x0"+arg0.getX());
			return true;
		}
		
		//触摸，跳转，改变direct的值
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {	
				//通过到达圆心的距离，和角度判断属于哪一个项目（方向）
				//获得x, y
				float x = event.getX();
				float y = event.getY();
				System.out.println("x..................."+x);
				System.out.println("y..................."+y);
				
				//外围、内围大小
				float out = rectF1.width()/2;
				float in = rectF1.width()/2-outRingWidth;
				System.out.println("out..................."+out);
				System.out.println("in..................."+in);
				
				//判断距离
				float deltaX = x - rectF1.centerX();
				float deltaY = y - rectF1.centerY();
				System.out.println("deltaX..................."+deltaX);
				System.out.println("deltaY..................."+deltaY);
				
				//计算距离
				float distant = (float) Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
				System.out.println("distant..................."+distant);
				
				//判断是否在其中
				if (distant<=out && distant>=in) {
					//在其中
					if (deltaY<0) {
						//上半区
						if (deltaX<0) {
							//上左
							//tan>1，即角度大于45，则为5区
							float tan = Math.abs(deltaX)/Math.abs(deltaY);
							if (tan>1) {
								direct = 5;
							}else {
								direct = 0;
							}
						}else {
							//上右
							//tan>1，即角度大于45，则为1区
							float tan = Math.abs(deltaX)/Math.abs(deltaY);
							if (tan>1) {
								direct = 1;
							}else {
								direct = 0;
							}
						}
					}else {
						//下半区
						if (deltaX<0) {
							//下左
							//tan>1，即角度大于45，则为5区
							float tan = Math.abs(deltaX)/Math.abs(deltaY);
							if (tan>1) {
								direct = 4;
							}else {
								direct = 3;
							}
						}else {
							//上右
							//tan>1，即角度大于45，则为1区
							float tan = Math.abs(deltaX)/Math.abs(deltaY);
							if (tan>1) {
								direct = 2;
							}else {
								direct = 3;
							}
						}
					}
//					System.out.println(".............direct"+direct);
					//重绘
					this.invalidate();
					return true;
				}else {
					//不在其中
					return false;
				}
			}else {
				return super.onTouchEvent(event);
			}
		}
}
