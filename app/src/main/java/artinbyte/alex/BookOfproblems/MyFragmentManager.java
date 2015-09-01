package artinbyte.alex.BookOfproblems;

import android.app.Activity;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;



public class MyFragmentManager{
static private MyFragmentManager instance;
        public static MyFragmentManager getInstance() {
            if (instance == null) {
                instance = new MyFragmentManager();
            }
            return instance;
        }

private Context context;
    private FragmentManager fm;

    Activity activity;
    public void init(Activity activity){
        this.activity = activity;
        fm = activity.getFragmentManager();
    }

    public  void Remove (Fragment fg)
    {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.remove(fg);
    }
    public void ShiftFragment(int containerViewId, Fragment fragment) {

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.setCustomAnimations(
                R.animator.slide_in_right, R.animator.right_out,
                R.animator.slide_in_left, R.animator.left_out);

        fragmentTransaction.replace(containerViewId, fragment);


        fragmentTransaction.commit();
    }
}
