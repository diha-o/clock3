package pc.dd.clock2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static MainActivity inst;
    AlarmManager alarmManager;
   static ToggleButton b;
    PendingIntent sender;
    Animation anim=null;
    static Context ct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ct = getApplicationContext();

        alarmTimePicker=(TimePicker)findViewById(R.id.alarmTimePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        b = (ToggleButton) findViewById(R.id.toggleButton);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ToggleButton) v).isChecked()==true){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                setAlarm(calendar);
                    TextView t1 = (TextView) findViewById(R.id.textView);
                    TextView t2 = (TextView) findViewById(R.id.textView2);
                    t1.setText((alarmTimePicker.getCurrentHour()).toString());
                    t2.setText((" : "+alarmTimePicker.getCurrentMinute()).toString());
                    viseb_show();
                    anim_time();

                }
                else{

                    if (alarmManager != null) {
                        Intent intent = new Intent(MainActivity.this, AlarmReciver.class);
                        alarmManager.cancel(PendingIntent.getBroadcast(MainActivity.this, 151516, intent, 0));
                    }
                    alarmManager.cancel(sender);
                    viseb_gone();
                    sender.cancel();
                    anim_time2();

                }
            }
        });
    }

    public void viseb_gone(){
        TextView t1 = (TextView) findViewById(R.id.textView);
        TextView t2 = (TextView) findViewById(R.id.textView2);
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(1.0f, 0.0f);

        animation.setDuration(2000);
        set.addAnimation(animation);

        t1.startAnimation(animation);
        t1.setVisibility(View.GONE);
        t2.startAnimation(animation);
        t2.setVisibility(View.GONE);
    }
    public void viseb_show(){
        AnimationSet set = new AnimationSet(true);
        TextView t1 = (TextView) findViewById(R.id.textView);
        TextView t2 = (TextView) findViewById(R.id.textView2);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);

        animation.setDuration(1000);
        set.addAnimation(animation);

        Animation animation2 = new AlphaAnimation(0.0f, 0.8f);

        animation2.setDuration(1200);
        set.addAnimation(animation2);


        t1.startAnimation(animation);
        t1.setVisibility(View.VISIBLE);
        t2.startAnimation(animation2);
        t2.setVisibility(View.VISIBLE);
    }
    public void anim_time(){
        AnimationSet set = new AnimationSet(true);
        RelativeLayout t1 = (RelativeLayout) findViewById(R.id.relative);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);

        animation.setDuration(4000);
        set.addAnimation(animation);

        t1.startAnimation(animation);
        t1.setVisibility(View.VISIBLE);
    }
    public void anim_time2(){
        AnimationSet set = new AnimationSet(true);
        RelativeLayout t1 = (RelativeLayout) findViewById(R.id.relative);
        Animation animation = new AlphaAnimation(1.0f, 0.0f);

        animation.setDuration(4000);
        set.addAnimation(animation);

        t1.startAnimation(animation);
        t1.setVisibility(View.GONE);
    }
    private void setAlarm(Calendar targetCal)
    {


        Intent alarmintent = new Intent(MainActivity.this, AlarmReciver.class);
        sender = PendingIntent.getBroadcast(MainActivity.this, 151516, alarmintent, 0);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        //alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), sender);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), sender);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
