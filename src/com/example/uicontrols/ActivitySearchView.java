package com.example.uicontrols;

import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ActivitySearchView extends Activity implements
		SearchView.OnQueryTextListener {
	// private SearchView sv;
	// private ListView lv;
	// //自动完成的列表
	// private final String[] mStrings={"aaaaaa","bbbbbb","abc","cccccc"};
	//
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_search_view);
	// lv=(ListView)findViewById(R.id.lv);
	// lv.setAdapter(new
	// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mStrings));
	// lv.setTextFilterEnabled(true);
	//
	// sv=(SearchView)findViewById(R.id.sv);
	// //设置该SearchView默认是否自动缩小为图标
	// sv.setIconifiedByDefault(true);
	// //为该SearchView组件设置事件监听器
	// sv.setOnQueryTextListener(this);
	// //设置该SearchView显示搜索按钮
	// // sv.setSubmitButtonEnabled(true);
	//
	// //设置该SearchView内默认显示的提示文本
	// sv.setQueryHint("查找");
	//
	//
	// }
	//
	//
	// //用户输入字符时激发该方法
	// @Override
	// public boolean onQueryTextChange(String newText) {
	// // TODO Auto-generated method stub
	// if(TextUtils.isEmpty(newText))
	// {
	// //清楚ListView的过滤
	// lv.clearTextFilter();
	// }
	// else
	// {
	// //使用用户输入的内容对ListView的列表项进行过滤
	// lv.setFilterText(newText);
	//
	// }
	// // return true;
	// return true;
	// }
	// //单击搜索按钮时激发该方法
	// @Override
	// public boolean onQueryTextSubmit(String query) {
	// // TODO Auto-generated method stub
	// //实际应用中应该在该方法内执行实际查询
	// //此处仅使用Toast显示用户输入的查询内容
	// Toast.makeText(this, "您选择的是："+query, Toast.LENGTH_SHORT).show();
	// return true;
	// }

	// private String[] strs = new String[] { "hello", "hello1", "hello2",
	// "hello3"};
	 private String[] strs = new String[] { "hello", "hello1", "hello2",
	 "hello3", "hello4", "hello5", "java", "javascript", "c#", "c++",
	 "php", "python" };
	SearchView sv = null;
	 ListView listView;
	 ArrayAdapter<String> adapter;
	// View popupWindow;
	PopupWindow mPopupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_view);

		sv = (SearchView) this.findViewById(R.id.sv);

		sv.setIconifiedByDefault(false);

		sv.setSubmitButtonEnabled(true);

		sv.setQueryHint("查询");

		sv.setOnQueryTextListener(this);

}
	//判断是否有指定字符串并返回字符串
	private ArrayList<String> getCertainStrs(String subString){
		ArrayList<String> arrayList= new ArrayList<String>();
		
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].contains(subString)) {
				arrayList.add(strs[i]);
			}
		}		
//		return (String[]) arrayList.toArray();		
		return arrayList;		
	}
	
	/*
	 * 创建PopupWindow
	 */
	private void initPopuptWindow(String str) {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View popupWindow = layoutInflater.inflate(R.layout.popup_window, null);

		// radioGroup.setOnCheckedChangeListener(this);

		listView = (ListView) popupWindow.findViewById(R.id.listViewPopSearch);
		adapter = new ArrayAdapter<String>(this,
				 android.R.layout.simple_list_item_1);
		adapter.clear();
		if (!TextUtils.isEmpty(str)) {			
			adapter.addAll(getCertainStrs(str));
		}else {
			adapter.addAll(strs);
		}
		listView.setAdapter(adapter);
		// 创建一个PopupWindow
		// 参数1：contentView 指定PopupWindow的内容
		// 参数2：width 指定PopupWindow的width
		// 参数3：height 指定PopupWindow的height
		mPopupWindow = new PopupWindow(popupWindow, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mPopupWindow.setFocusable(false);
		// 需要设置一下此参数，点击外边可消失 
		//设置点击窗口外边窗口消失 
		mPopupWindow.setOutsideTouchable(true); 
		//设置弹出窗体需要软键盘，
		mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
		//再设置模式，和Activity的一样，覆盖，调整大小。
		mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		// 获取屏幕和PopupWindow的width和height
//		mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
//		mScreenWidth = getWindowManager().getDefaultDisplay().getHeight();
//		mPopupWindowWidth = mPopupWindow.getWidth();
//		mPopupWindowHeight = mPopupWindow.getHeight();
		
		//listview点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//获得选择的食物
				String choose = adapter.getItem(position);
				sv.setQuery(choose, false);
				mPopupWindow.dismiss();
			}
		});
	}
	//内容变更
	@Override
	public boolean onQueryTextChange(String str) {
		// System.out.println("str" + str);
//		getPopupWindowInstance();
//		initPopuptWindow(str);
//		mPopupWindow.showAsDropDown(sv);
		// sv.setFocusable(true);
		if (mPopupWindow != null) {
			mPopupWindow.dismiss(); 				
		}
		if (TextUtils.isEmpty(str)) {
			// 清楚ListView的过滤
//			 initPopuptWindow("");
//			listView.clearTextFilter();
		} else {
			// 使用用户输入的内容对ListView的列表项进行过滤
			// listView.setFilterText(newText);
//			initPopuptWindow(str);
			initPopuptWindow(str);
			mPopupWindow.showAsDropDown(sv);
			return true;
		}
		// return true;
		return true;
	}
	@Override
	public boolean onQueryTextSubmit(String str) {

		Toast.makeText(ActivitySearchView.this, str, Toast.LENGTH_SHORT).show();

		return false;
	}
	
////	private String[] strs = new String[] { "hello", "hello1", "hello2",
////			"hello3", "hello4", "hello5", "java", "javascript", "c#", "c++",
////			"php", "python" };
//	SearchView sv = null;
//	ListView lv = null;
////	ListView listView;
////	View popupWindow;
//	PopupWindow mPopupWindow;
//
//	@SuppressWarnings("deprecation")
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_search_view);
//
//		sv = (SearchView) this.findViewById(R.id.sv);
//
//		sv.setIconifiedByDefault(false);
//
//		sv.setSubmitButtonEnabled(true);
//
//		sv.setQueryHint("查询");
//		
//		 sv.setOnQueryTextListener(this);
//		 
////		 LayoutInflater layoutInflater = LayoutInflater.from(this);
////			popupWindow = layoutInflater.inflate(R.layout.popup_window, null);
////			listView = (ListView) popupWindow.findViewById(R.id.listViewPopSearch);
////			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
////					 android.R.layout.simple_list_item_1);
////			adapter.addAll(strs);
////			listView.setAdapter(adapter);
////			listView.setTextFilterEnabled(true);
////		 initPopuptWindow();
//		 
////		 listView.setTextFilterEnabled(true);
//		// //设置该SearchView显示搜索按钮
//		// // sv.setSubmitButtonEnabled(true);
//
//		// 通过反射，修改默认的样式，可以从android的search_view.xml中找到需要的组件
//
//		// try {
//		// Field field = sv.getClass().getDeclaredField("mSubmitButton");
//		//
//		// field.setAccessible(true);
//		//
//		// ImageView iv = (ImageView) field.get(sv);
//		//
//		// iv.setImageDrawable(this.getResources().getDrawable(
//		// R.drawable.pointer));
//		//
//		//
//		// } catch (Exception e) {
//		//
//		// e.printStackTrace();
//		// }
//
////		Cursor cursor = this.getTestCursor();
//		// Cursor cursor = null;
////		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
////				R.layout.mytextview, cursor, new String[] { "tb_name" },
////				new int[] { R.id.textview });
//		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//		// android.R.layout.simple_dropdown_item_1line);
//		// adapter.addAll(strs);
//		// sv.setsu
//		// sv.setAdapter(adapter);
//		// sv.setSuggestionsAdapter(adapter);
//
//	}

//	@Override
//	public boolean onQueryTextChange(String str) {
//		// System.out.println("str" + str);
//		getPopupWindowInstance();
//		mPopupWindow.showAsDropDown(sv);
//		// sv.setFocusable(true);
//		if (TextUtils.isEmpty(str)) {
//			
//			 mPopupWindow.dismiss(); 
//			// 清楚ListView的过滤
////			 initPopuptWindow("");
////			listView.clearTextFilter();
//		} else {
//			// 使用用户输入的内容对ListView的列表项进行过滤
//			// listView.setFilterText(newText);
//			initPopuptWindow(str);
//			return true;
//		}
//		// return true;
//		return true;
//	}
//
//	// public boolean onQueryTextChange(String newText) {
//	// // TODO Auto-generated method stub
//	// if(TextUtils.isEmpty(newText))
//	// {
//	// //清楚ListView的过滤
//	// lv.clearTextFilter();
//	// }
//	// else
//	// {
//	// //使用用户输入的内容对ListView的列表项进行过滤
//	// lv.setFilterText(newText);
//	//
//	// }
//	// // return true;
//	// return true;
//	// }
//	@Override
//	public boolean onQueryTextSubmit(String str) {
//
//		Toast.makeText(ActivitySearchView.this, str, Toast.LENGTH_SHORT).show();
//
//		return false;
//	}

//	// 添加suggestion需要的数据
//	public Cursor getTestCursor() {
//
//		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
//				this.getFilesDir() + "/my.db3", null);
//
//		Cursor cursor = null;
//		try {
//
//			String insertSql = "insert into tb_test values (null,?,?)";
//
//			db.execSQL(insertSql, new Object[] { "aa", 1 });
//
//			db.execSQL(insertSql, new Object[] { "ab", 2 });
//
//			db.execSQL(insertSql, new Object[] { "ac", 3 });
//
//			db.execSQL(insertSql, new Object[] { "ad", 4 });
//
//			db.execSQL(insertSql, new Object[] { "ae", 5 });
//
//			String querySql = "select * from tb_test";
//
//			cursor = db.rawQuery(querySql, null);
//
//		} catch (Exception e) {
//
//			String sql = "create table tb_test (_id integer primary key autoincrement,tb_name varchar(20),tb_age integer)";
//
//			db.execSQL(sql);
//
//			String insertSql = "insert into tb_test values (null,?,?)";
//
//			db.execSQL(insertSql, new Object[] { "aa", 1 });
//
//			db.execSQL(insertSql, new Object[] { "ab", 2 });
//
//			db.execSQL(insertSql, new Object[] { "ac", 3 });
//
//			db.execSQL(insertSql, new Object[] { "ad", 4 });
//
//			db.execSQL(insertSql, new Object[] { "ae", 5 });
//
//			String querySql = "select * from tb_test";
//
//			cursor = db.rawQuery(querySql, null);
//		}
//
//		return cursor;
//	}
//
//	/*
//	 * 获取PopupWindow实例
//	 */
//	private void getPopupWindowInstance() {
//		if (null != mPopupWindow) {
//			mPopupWindow.dismiss();
//			return;
//		} else {
//			initPopuptWindow("");
//		}
//	}
//
//	/*
//	 * 创建PopupWindow
//	 */
//	private void initPopuptWindow(String str) {
////		LayoutInflater layoutInflater = LayoutInflater.from(this);
////		popupWindow = layoutInflater.inflate(R.layout.popup_window, null);
//		// radioGroup.setOnCheckedChangeListener(this);
//
////		listView = (ListView) popupWindow.findViewById(R.id.listViewPopSearch);
////		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
////				 android.R.layout.simple_list_item_1);
////		adapter.addAll(strs);
////		listView.setAdapter(adapter);
////		listView.setTextFilterEnabled(true);
//		if (TextUtils.isEmpty(str)) {			
//			// 清楚ListView的过滤
//			listView.clearTextFilter();
//		} else {
//			listView.setFilterText(str);
//		}
//		// 创建一个PopupWindow
//		// 参数1：contentView 指定PopupWindow的内容
//		// 参数2：width 指定PopupWindow的width
//		// 参数3：height 指定PopupWindow的height
//		mPopupWindow = new PopupWindow(popupWindow, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//		mPopupWindow.setFocusable(false);
//		// 需要设置一下此参数，点击外边可消失 
//		//设置点击窗口外边窗口消失 
//		mPopupWindow.setOutsideTouchable(true); 
//		//设置弹出窗体需要软键盘，
//		mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//		//再设置模式，和Activity的一样，覆盖，调整大小。
//		mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//		// 获取屏幕和PopupWindow的width和height
////		mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
////		mScreenWidth = getWindowManager().getDefaultDisplay().getHeight();
////		mPopupWindowWidth = mPopupWindow.getWidth();
////		mPopupWindowHeight = mPopupWindow.getHeight();
//	}
//
//	//初始化listView
//	private void initListView(String str){
//		if (TextUtils.isEmpty(str)) {			
//			// 清楚ListView的过滤
//			listView.clearTextFilter();
//		} else {
//			listView.setFilterText(str);
//		}
//		// return true;
//	}
	
}
