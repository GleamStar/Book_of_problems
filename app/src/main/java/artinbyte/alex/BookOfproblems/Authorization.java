package artinbyte.alex.BookOfproblems;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


public class Authorization extends Fragment {
    Button authorization;
    Button registration;
    EditText log ;
    EditText pas ;
    EditText pas2 ;
    CheckBox checkBox;
    Button button;
    Button button10;
    EditText logg ;
    EditText pass ;
    Button ookk ;
    Button Cancel;

    DatabaseConnector databaseConnector;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bxod, container, false);


        databaseConnector = new DatabaseConnector(getActivity());
        //databaseConnector.DF();
        authorization = (Button) v.findViewById(R.id.button5);
        registration = (Button)v.findViewById(R.id.button6);
        final Random rnd = new Random();
        final String [] title = new String[]{getString(R.string.mot1),getString(R.string.mot2),getString(R.string.mot3),getString(R.string.mot4),getString(R.string.mot5)};
        final Context context = getActivity();
       View.OnClickListener a = new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               switch (v.getId())
               {
                   case R.id.button5 :
                       preference_manager P_M = preference_manager.GetInstanse();
                       P_M.Init(getActivity());
                       final Dialog dialog1 = new Dialog(context);
                          dialog1.setTitle(title[rnd.nextInt(5)]);
                       dialog1.setContentView(R.layout.dialogatr);
                       checkBox =(CheckBox)dialog1.findViewById(R.id.SavePass);

                       logg = (EditText)dialog1.findViewById(R.id.editText);
                       pass = (EditText)dialog1.findViewById(R.id.editText2);
                       ookk = (Button)dialog1.findViewById(R.id.button8);
                       Cancel =(Button)dialog1.findViewById(R.id.button11);

                       if (P_M.GetString("Checked","").equals("+"))
                       {
                           logg.setText(P_M.GetString("Checked_Name",""));
                           pass.setText(P_M.GetString("Checked_PASS",""));
                       }



                       ookk.setOnClickListener(new View.OnClickListener()
                       {
                           @Override
                           public void onClick(View v) {

                               String nm = logg.getText().toString();
                               String ps = pass.getText().toString();
                               if (databaseConnector.Authorization(nm, ps)) {

                                   preference_manager PM = preference_manager.GetInstanse();
                                    PM.Init(getActivity());
                                   PM.ADDString("Name", logg.getText().toString());
                                   Intent g = new Intent(getActivity(), MainActivity.class);
                                       if(checkBox.isChecked())
                                       {
                                          PM.ADDString("Checked","+");
                                           PM.ADDString("Checked_Name",nm);
                                           PM.ADDString("Checked_PASS",ps);
                                       }
                                   else
                                       {
                                          PM.ADDString("Checked","-");
                                       }

                                   getActivity().startActivity(g);
                                   getActivity().finish();


                               }
                               else {
                                   Toast.makeText(getActivity(), R.string.Pola, Toast.LENGTH_SHORT).show();
                               }

                           }
                       });
                       Cancel.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               dialog1.dismiss();
                           }
                       });
                       dialog1.show();
                       break;
                   case R.id.button6 :
                       final Dialog dialog = new Dialog(context);
                       dialog.setTitle(title[rnd.nextInt(5)]);
                       dialog.setContentView(R.layout.dialogreg);
                       button = (Button) dialog.findViewById(R.id.button7);
                       button10 = (Button)dialog.findViewById(R.id.button10);
                       log = (EditText)dialog.findViewById(R.id.editText4);
                       pas = (EditText)dialog.findViewById(R.id.editText5);
                       pas2 = (EditText)dialog.findViewById(R.id.editText6);

                       button.setOnClickListener(new View.OnClickListener() {

                           @Override
                           public void onClick(View v) {
                               if ((pas.getText().length() == 0) || (log.getText().length() == 0) || (pas2.getText().length() == 0)) {
                                   Toast.makeText(getActivity(), R.string.Pola, Toast.LENGTH_SHORT).show();
                               }
                               final String NameUser = log.getText().toString();
                               final String PassOne = pas.getText().toString();
                               String PassTwo = pas2.getText().toString();

                               // preference_manager a = preference_manager.GetInstance();
                               //  a.Init(getActivity());

                               if (PassOne.equals(PassTwo)) {

                                                   DatabaseConnector datab = new DatabaseConnector(getActivity());
                                                   datab.insertUser(NameUser,PassOne);
                                                   Log.v("INSERT","INSERT");
                                   dialog.dismiss();
                                               }



                                else {
                                   Toast.makeText(getActivity(), R.string.erpass, Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
                       button10.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               dialog.dismiss();
                           }
                       });
                       dialog.show();

                       break;
               }

           }
       };
        authorization.setOnClickListener(a);
        registration.setOnClickListener(a);
        return v;

    }

}
