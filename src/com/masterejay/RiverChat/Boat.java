package com.masterejay.RiverChat;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * @author MasterEjay
 */
@ParseClassName("Boat")
public class Boat extends ParseObject {

	public Boat(){

	}

	public ParseUser getUserTo(){
		return getParseUser("userTo");
	}

	public void setUserTo(ParseUser user){
		put("userTo", user);
	}

	public ParseUser getUserFrom(){
		return getParseUser("userFrom");
	}

	public void setUserFrom(ParseUser user){
		put("userFrom", user);
	}

	public String getMessage(){
		return getString("message");
	}

	public void setMessage(String message){
		put("message", message);
	}
}
