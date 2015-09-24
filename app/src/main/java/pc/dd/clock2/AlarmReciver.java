package pc.dd.clock2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by User on 24.09.2015.
 */
public class AlarmReciver extends WakefulBroadcastReceiver {


    @Override
    public void onReceive(final Context context, Intent intent) {

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

    Ringtone  ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
        //setResultCode(MainActivity.RESULT_OK);
    }
}