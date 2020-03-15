package com.twelve.latesleeper.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import com.twelve.latesleeper.R;
interface RingtoneHelper {
    void stopRingtone();
}

class Utility {

    public static RingtoneHelper ringtoneHelper;


}
public class MyBroadcastReceiverAlarm extends BroadcastReceiver {

    @Override
  public void onReceive(Context context, Intent intent)
  {
      Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
      vibrator.vibrate(2000); //needed to make phone vibrate
      //note i had to allow permissions in order to make phone vibrate
      //user may also need to enable them in order for that to work

      Notification notif = new Notification.Builder(context) //set up notifications
          .setContentTitle("Alarm is on").setContentText("you have set up the alarm")
  .setSmallIcon(R.mipmap.ic_launcher).build();

      NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
      //notif.flags!= Notification.FLAG_AUTO_CANCEL;
      notif.flags|=Notification.FLAG_AUTO_CANCEL; //check it might be !=, not sure
      manager.notify(0,notif); //send notification object


      Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE); //get default rington
      final Ringtone r = RingtoneManager.getRingtone(context,notification);
      r.play();//play the ringtone

      //add receiver to android manifest
    Utility.ringtoneHelper = new RingtoneHelper() {
        @Override
        public void stopRingtone() {
            if(r.isPlaying()){r.stop();}
        }
    };
  }
}
