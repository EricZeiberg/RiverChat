package com.masterejay.RiverChat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import br.com.dina.ui.widget.UIButton;
import br.com.dina.ui.widget.UITableView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RiverListView extends Activity{
	/**
	 * Called when the activity is first created.
	 */
	UITableView tableView;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.riverlist);
		tableView = (UITableView) findViewById(R.id.tableView);
		createList();

	}
	private void createList() {
		CustomClickListener listener = new CustomClickListener();
		tableView.setClickListener(listener);
		grabParseData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.river_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.log_out:
				ParseUser.logOut();
				Intent in =  new Intent(RiverListView.this,LoginView.class);
				startActivity(in);
				toastMessage("You have been logged out!");
				return true;
			case R.id.new_river:
				newRiver();
				return true;
			case R.id.refresh:
				grabParseData();
				toastMessage("Refreshed");
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void grabParseData(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("River");
		//query.whereEqualTo("users", ParseUser.getCurrentUser().getUsername());
		try{
			List<ParseObject> results = query.find();
			for (ParseObject po : results){
				tableView.addBasicItem(po.getString("name"));
			}
		}catch(ParseException e){
			e.printStackTrace();
			toastMessage("Something went wrong! Please try again!");
		}
		tableView.clear();
		tableView.commit();
	}

	public void newRiver(){
		Intent in =  new Intent(RiverListView.this,NewRiverView.class);
		startActivity(in);
	}


	protected void toastMessage(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	private class CustomClickListener implements UITableView.ClickListener{
		@Override
		public void onClick(int index) {
			Toast.makeText(RiverListView.this,"item clicked: "+index,Toast.LENGTH_SHORT).show();
		}
	}
}
