package artinbyte.alex.BookOfproblems;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class manual3 extends Fragment {
    MyFragmentManager transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment3, container, false);

        Button button = (Button) v.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               Authorization authorization = new Authorization();
                transaction = MyFragmentManager.getInstance();
                transaction.init(getActivity());
                transaction.ShiftFragment(R.id.frgmCont, authorization);

            }
        });
        return v;

    }
}
