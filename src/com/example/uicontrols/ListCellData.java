package com.example.uicontrols;

import android.content.Context;
import android.content.Intent;

public class ListCellData {

	//RadioGroup
	
	public ListCellData(Context context, String controlName, Intent relatedIntent) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.controlName = controlName;
		this.relatedIntent = relatedIntent;
	}
	
	//控件名
	private String controlName="";
	public String getControlName() {
		return controlName;
	}
	
	//context
	private Context context = null;
	public Context getContext() {
		return context;
	}
	
	//与其相关的Intent对象
	private Intent relatedIntent = null;
	public Intent getRelatedIntent() {
		return relatedIntent;
	}
	
	//实现方法startActivity
	public void startActivity(){
		//Todo
		getContext().startActivity(relatedIntent);
	}
	
	//重写toString()，列表项显示调用此方法
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.controlName;
	}
	
}
