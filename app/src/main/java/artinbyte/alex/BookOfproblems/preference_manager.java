package artinbyte.alex.BookOfproblems;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Oleg on 21.04.2015.
 */
public class preference_manager {

    private static preference_manager Instanse;

    private preference_manager() {

    }

    public static preference_manager GetInstanse() {
        if (Instanse == null
                ) {
            Instanse = new preference_manager();
        }
        return Instanse;
    }
    private static Context context;
    public static void Init(Context Context) {
        context = Context ;
    }
    public static String GetString(String key ,String Value )
    {

            SharedPreferences share = context.getSharedPreferences("options", 0);
            return share.getString(key, "");


    }
    public static void ADDString(String key, String Value)
    {
        SharedPreferences share = context.getSharedPreferences("options",0);
        SharedPreferences.Editor edit = share.edit();
        edit.putString(key,Value);
        edit.commit();
    }
    public  void Delete ( String key)
    {
        SharedPreferences share = context.getSharedPreferences("options",0);
        SharedPreferences.Editor edit = share.edit();
        edit.remove(key);
        edit.commit();
    }

}