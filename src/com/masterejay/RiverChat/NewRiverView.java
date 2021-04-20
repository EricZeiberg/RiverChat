package com.masterejay.RiverChat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MasterEjay
 */
public class NewRiverView extends Activity{

	EditText name;
	EditText user;
	Button create;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_river);
		name =(EditText)findViewById(R.id.riverName);
		user =(EditText)findViewById(R.id.riverUser);
		create =(Button)findViewById(R.id.btnCreateRiver);

		create.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				 onCreate();
			}
		});
	}

	public void onCreate(){
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereEqualTo("username", user.getText().toString());
		query.findInBackground(new FindCallback<ParseUser>() {
			public void done(List<ParseUser> objects, ParseException e) {
				if (e == null) {
				  if (objects.size() > 1 || objects.size() < 0){
					toastMessage("That user does not exist!");
					return;
				  }
					ParseObject river = ParseObject.create("River");
					List<String> users = new ArrayList<>();
					users.add(ParseUser.getCurrentUser().getUsername());
					users.add(objects.get(0).getUsername());
					river.put("users", users);
					river.put("name", name.getText().toString());
					river.put("boats", new ArrayList<>());
					try{
						river.save();
					}catch(ParseException e1){
						e1.printStackTrace();
						toastMessage("Oops, something went wrong! Try again please!");
					}

					toastMessage("River created!");
					Intent in =  new Intent(NewRiverView.this, RiverListView.class);
					startActivity(in);
				} else {
					toastMessage("Oops! Something went wrong!");
				}
			}
		});


	}

	protected void toastMessage(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
	}
}
