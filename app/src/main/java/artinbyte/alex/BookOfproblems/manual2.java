package artinbyte.alex.BookOfproblems;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class manual2 extends Fragment {
    MyFragmentManager transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);
        this.setRetainInstance(true);
        Button button =(Button)v.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manual3 manual3 = new manual3();


                transaction = MyFragmentManager.getInstance();
                transaction.init(getActivity());
                transaction.ShiftFragment(R.id.frgmCont, manual3);

            }
        });
        return  v;
    }


}
