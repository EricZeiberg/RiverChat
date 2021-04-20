package com.masterejay.RiverChat;

import android.app.Application;
import com.masterejay.RiverChat.Boat;
import com.masterejay.RiverChat.River;
import com.masterejay.RiverChat.utils.ParseInfo;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * @author MasterEjay
 */
public class RiverChat extends Application{
	@Override
	public void onCreate(){
		super.onCreate();
		ParseObject.registerSubclass(River.class);
		ParseObject.registerSubclass(Boat.class);
		Parse.initialize(this,ParseInfo.getAPP_ID(),ParseInfo.getCLIENT_KEY());
	}
}
