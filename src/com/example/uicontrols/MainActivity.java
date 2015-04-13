package com.example.uicontrols;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {

	private ArrayAdapter<ListCellData> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        //����Adapter
        adapter = new ArrayAdapter<ListCellData>(this, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        
        //���������Ԫ��
        adapter.add(new ListCellData(this, "Animation",
        		new Intent(this, ActivityAnimation.class)));
        adapter.add(new ListCellData(this, "Menu",
        		new Intent(this, ActivityMenu.class)));
        adapter.add(new ListCellData(this, "TabHost",
        		new Intent(this, ActivityTabhost.class)));
        adapter.add(new ListCellData(this, "Clock",
        		new Intent(this, ActivityClock.class)));
        adapter.add(new ListCellData(this, "TextviewEditview",
        		new Intent(this, ActivityTextviewEditview.class)));
        adapter.add(new ListCellData(this, "ListView",
        		new Intent(this, ActivityListView.class)));
        adapter.add(new ListCellData(this, "CustomView",
        		new Intent(this, ActivityCustomBmiView.class)));
        adapter.add(new ListCellData(this, "RadioGroup",
        		new Intent(this, ActivityRadioGroup.class)));
        adapter.add(new ListCellData(this, "CheckBox",
        		new Intent(this, ActivityCheckBox.class)));
        adapter.add(new ListCellData(this, "DatePicker",
        		new Intent(this, ActivityDatePicker.class)));
        adapter.add(new ListCellData(this, "TimePicker",
        		new Intent(this, ActivityTimePicker.class)));
        adapter.add(new ListCellData(this, "Spinner",
        		new Intent(this, ActivitySpinner.class)));
        adapter.add(new ListCellData(this, "AutoCompleteTextView",
        		new Intent(this, ActivityAutoCompleteTextView.class)));
        adapter.add(new ListCellData(this, "ProgressBar",
        		new Intent(this, ActivityProgressBar.class)));
        adapter.add(new ListCellData(this, "SeekBar",
        		new Intent(this, ActivitySeekBar.class)));
        adapter.add(new ListCellData(this, "GridView",
        		new Intent(this, ActivityGridView.class)));
        adapter.add(new ListCellData(this, "ProgressDialog",
        		new Intent(this, ActivityProgressDialog.class)));
        adapter.add(new ListCellData(this, "Notification",
        		new Intent(this, ActivityNotification.class)));
        adapter.add(new ListCellData(this, "ScrollView",
        		new Intent(this, ActivityScrollView.class)));
        adapter.add(new ListCellData(this, "RatingBar",
        		new Intent(this, ActivityRatingBar.class)));
        adapter.add(new ListCellData(this, "ImageSwitcher",
        		new Intent(this, ActivityImageSwitcher.class)));
        adapter.add(new ListCellData(this, "Gallery",
        		new Intent(this, ActivityGallery.class)));
        adapter.add(new ListCellData(this, "EditText",
        		new Intent(this, ActivityEditText.class)));
        adapter.add(new ListCellData(this, "NumberPicker",
        		new Intent(this, ActivityNumberPicker.class)));
        adapter.add(new ListCellData(this, "Pedometer",
        		new Intent(this, ActivityPedometer.class)));
        adapter.add(new ListCellData(this, "SearchView",
        		new Intent(this, ActivitySearchView.class)));
        adapter.add(new ListCellData(this, "CustomView",
        		new Intent(this, ActivityCustomView.class)));
    }
    
    //����б����¼�
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	
    	//����б�������
    	ListCellData data = adapter.getItem(position);
    	data.startActivity();
    	
    	super.onListItemClick(l, v, position, id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
