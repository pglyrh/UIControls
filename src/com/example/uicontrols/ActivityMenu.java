package com.example.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;

public class ActivityMenu extends Activity {
	// 定义菜单标识
	private static final int MENU_OPTION = 0;
	private static final int MENU_HELP = 1;
	private static final int MENU_ABOUT = 2;
	private static final int MENU_QUIT = 3;
	
	private static final int SUB_MENU_ADD = 101;
	private static final int SUB_MENU_DEL = 102;
	
	private static final int CONTEXT_MENU_ADD = 201;
	private static final int CONTEXT_MENU_DEL = 202;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		//对TextView注册ContextMenu
		registerForContextMenu(findViewById(R.id.textViewMenuContext));
	}
	
	// 创建菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 创建一个包含子菜单的菜单
		SubMenu sub = menu.addSubMenu(0, MENU_OPTION, 0, "操作");
		sub.setIcon(R.drawable.pic1);
		sub.add(0, SUB_MENU_ADD, 0, "添加（sub）");
		sub.add(0, SUB_MENU_DEL, 1, "删除（sub）");
		
		// 创建3个普通菜单
		menu.add(0, MENU_HELP, 1, "帮助").setIcon(R.drawable.pic2);
		menu.add(0, MENU_ABOUT, 2, "关于").setIcon(R.drawable.pic3);
		menu.add(0, MENU_QUIT, 3, "退出").setIcon(R.drawable.pic4);
		
		// 返回true，菜单可见
		return true;
	}
	
	// 点击OptionsMenu选项
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case SUB_MENU_ADD:
			Toast.makeText(this, "SUB_MENU_ADD", Toast.LENGTH_SHORT).show();
			break;
		case SUB_MENU_DEL:
			Toast.makeText(this, "SUB_MENU_DEL", Toast.LENGTH_SHORT).show();
			break;
		case MENU_HELP:
			Toast.makeText(this, "SUB_MENU_HELP", Toast.LENGTH_SHORT).show();
			break;
		case MENU_ABOUT:
			Toast.makeText(this, "SUB_MENU_ABOUT", Toast.LENGTH_SHORT).show();
			break;
		case MENU_QUIT:
			Toast.makeText(this, "SUB_MENU_QUIT", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return true;
	}
	
	// 创建上下文菜单
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, CONTEXT_MENU_ADD, 1, "添加（Context）");
		menu.add(0, CONTEXT_MENU_DEL, 2, "删除（Context）");
	}
	
	// 根据被单击上下文菜单的标识，执行相应的输出
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case CONTEXT_MENU_ADD:
			Toast.makeText(this, "CONTEXT_MENU_ADD", Toast.LENGTH_SHORT).show();
			break;
		case CONTEXT_MENU_DEL:
			Toast.makeText(this, "CONTEXT_MENU_DEL", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return true;
	}
}
