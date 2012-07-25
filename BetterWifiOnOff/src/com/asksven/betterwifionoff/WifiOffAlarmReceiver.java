/*

 * Copyright (C) 2012 asksven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.asksven.betterwifionoff;

import com.asksven.betterwifionoff.services.EventWatcherService;
import com.asksven.betterwifionoff.services.SetWifiStateService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Handles alarms set to turn off Wifi with a delay 
 * @author sven
 *
 */
public class WifiOffAlarmReceiver extends BroadcastReceiver
{		 
	private static String TAG = "WifiOffAlarmReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.d(TAG, "Alarm received: turning Wifi off");
		try
		{
        	EventWatcherService myService = EventWatcherService.getInstance();
        	if (myService != null)
        	{
        		myService.getEventLogger().addSystemEvent("Timeout to turn Wifi off reached, turning Wifi off");
        	}

			// start service to turn off wifi
			Intent serviceIntent = new Intent(context, SetWifiStateService.class);
			serviceIntent.putExtra(SetWifiStateService.EXTRA_STATE, false);
			context.startService(serviceIntent);
		}
		catch (Exception e)
		{
			Log.e(TAG, "An error occured receiving the alarm");
		}
	}
}
