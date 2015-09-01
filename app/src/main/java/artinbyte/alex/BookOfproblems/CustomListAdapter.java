package artinbyte.alex.BookOfproblems;

import android.content.Context;
import android.graphics.Paint;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер для ListView
 */
public class CustomListAdapter  extends BaseAdapter {

	private LayoutInflater inflater_;
	private List<ToDoItem> list = new ArrayList<ToDoItem>();
     DatabaseConnector databaseConnector;
	private Context context;

        @Override
        public boolean isEmpty() {
            return false;
        }


 // }
	public  void FAr ( String a , String b, String c,int i )
	{
		list.add(new ToDoItem(a,b,c,i));
		Utils v = new Utils();
		v.SortIndex(list,i);
	}
	public CustomListAdapter(List<ToDoItem> list, Context context)
	{
		this.list = list;
		inflater_ = LayoutInflater.from(context);

		this.context = context;


	}
	public void  DeleteIndex (int b)
	{
		Utils f = new Utils();
		f.DIndexDelete(list,b);
	}

	@Override
	public int getCount() {
		return this.list.size();
	}

	@Override
	public Object getItem(int position) {
		return this.list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void setList(List<ToDoItem> list)
	{
		this.list = list;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		View v = view;
		if (view == null)
			v = inflater_.inflate(R.layout.listitem,
				parent, false);
		
		v.setVisibility(View.VISIBLE);
		

		final ToDoItem item = list.get(position);

		TextView listitem_name = (TextView)v.findViewById(R.id.listitem_name);
		listitem_name.setText( item.getIndex()+". "+  item.getName()  );

        TextView description = (TextView)v.findViewById(R.id.description);
        description.setText(item.getDescription());

        TextView clock = (TextView)v.findViewById(R.id.clock);
         clock.setText(item.getDate());
		// Перечеркивание завершенных дел
		String name = description.getText().toString();
		if (item.isCheck()) {
			Log.v("UP",name );
			Log.v("UP","не");

			((MainActivity)context).connector.updateTask(name, 1);

			listitem_name.setPaintFlags(Paint.ANTI_ALIAS_FLAG | Paint.STRIKE_THRU_TEXT_FLAG|Paint.LINEAR_TEXT_FLAG);
			description.setPaintFlags(Paint.ANTI_ALIAS_FLAG | Paint.STRIKE_THRU_TEXT_FLAG);
			clock.setPaintFlags(Paint.ANTI_ALIAS_FLAG | Paint.STRIKE_THRU_TEXT_FLAG);
		}
		else {
			((MainActivity)context).connector.updateTask(name, 0);
			listitem_name.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
			description.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
			clock.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
		}
		// Установка чекбокса и события на нажатие
		CheckBox listitem_check = (CheckBox)v.findViewById(R.id.listitem_check);
		listitem_check.setChecked(item.isCheck());	
		listitem_check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
        		Message msg = new Message();
        		msg.arg1 = position;
				msg.what = MainActivity.MSG_CHANGE_ITEM;
				((MainActivity)context).getHandler().sendMessage(msg);
				
			}
		});

		return v;
	}
}
