package artinbyte.alex.BookOfproblems;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;


public class manual1 extends Fragment implements CompoundButton.OnCheckedChangeListener {

    MyFragmentManager transaction ;
    preference_manager PM;
    int count ;
    manual2 manual2;
    Switch sw;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.setRetainInstance(true);

        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);

        transaction = MyFragmentManager.getInstance();
        transaction.init(getActivity());

        PM = preference_manager.GetInstanse();
        PM.Init(getActivity());

        Button button =(Button)v.findViewById(R.id.button);
        sw =(Switch)v.findViewById(R.id.skip);
        sw.setOnCheckedChangeListener(this);
        if (PM.GetString("Yes","").equals("Yes"))
        {
            sw.setChecked(true);
            Authorization authorization = new Authorization();
            transaction.ShiftFragment(R.id.frgmCont, authorization);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (count)
                {
                    case (0):
                    {
                        manual2 = new manual2();
                        transaction.ShiftFragment(R.id.frgmCont, manual2);
                        break;
                    }
                    case (1):
                    {
                        Authorization authorization = new Authorization();
                        transaction.ShiftFragment(R.id.frgmCont, authorization);

                        PM.ADDString("Yes", "Yes");
                    }
                }



            }
        });
        return  v;
    }
    public  void  onClickStart (View view)
    {

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked)
        {

               count = 1;


        }
        else
        {
             count = 0;


        }

    }
}