package artinbyte.alex.BookOfproblems;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class Restoring_service extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {



    //��� Service
    Intent serviceIntent = new Intent(context, MyAlarmService.class);
    context.startService(serviceIntent);}
}
