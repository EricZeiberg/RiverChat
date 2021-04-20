package com.masterejay.RiverChat;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

/**
 * @author MasterEjay
 */
@ParseClassName("River")
public class River extends ParseObject{

	public River(){

	}

	public String getName(){
		return getString("name");
	}

	public void setName(String name){
		put("name", name);
	}

	public List<ParseUser> getRiverUsers(){
		return getList("users");
	}

	public void setRiverUsers(List<ParseUser> users){
		put("users", users);
	}

	public List<ParseUser> getBoats(){
		return getList("boats");
	}

	public void setBoats(List<Boat> boats){
		put("boats", boats);
	}
}
