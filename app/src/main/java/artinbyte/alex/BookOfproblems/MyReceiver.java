package artinbyte.alex.BookOfproblems;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Oleg on 13.06.2015.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {

        Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);

    }
}