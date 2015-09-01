package artinbyte.alex.BookOfproblems;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class First extends ActionBarActivity  {
     manual1 manual1;




    preference_manager preference_manager;
    FragmentTransaction fTrans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, Music.class));
        startService(new Intent(this, Music.class));
        preference_manager = preference_manager.GetInstanse();

        manual1 = new manual1();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, manual1);
        fTrans.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.BackString1)
                .setMessage(R.string.BackString2)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        stopService(new Intent(First.this, Music.class));
                        First.super.onBackPressed();

                    }
                }).create().show();
    }


}
