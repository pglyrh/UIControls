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
	
	//�ؼ���
	private String controlName="";
	public String getControlName() {
		return controlName;
	}
	
	//context
	private Context context = null;
	public Context getContext() {
		return context;
	}
	
	//������ص�Intent����
	private Intent relatedIntent = null;
	public Intent getRelatedIntent() {
		return relatedIntent;
	}
	
	//ʵ�ַ���startActivity
	public void startActivity(){
		//Todo
		getContext().startActivity(relatedIntent);
	}
	
	//��дtoString()���б�����ʾ���ô˷���
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.controlName;
	}
	
}
