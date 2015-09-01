package artinbyte.alex.BookOfproblems;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity {

	private PendingIntent pendingIntent;
	public static final int MSG_UPDATE_ADAPTER = 0;
	public static final int MSG_CHANGE_ITEM = 1;
	public  String  NameUser ;
	Button buttonadd;
	Button buttondelete;
     String nazva;


	private List<ToDoItem> list = new ArrayList<ToDoItem>();

	private CustomListAdapter adapter;
	private ListView listview;



	preference_manager z = preference_manager.GetInstanse();

	final Context context = this;
	DatabaseConnector connector = new DatabaseConnector(context);




	private View footer;



	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what)
			{
				case MSG_UPDATE_ADAPTER: // Обновление ListView
					adapter.notifyDataSetChanged();
					setCountPurchaseProduct();
					break;
				case MSG_CHANGE_ITEM: // Сделать/Не сделать дело
					ToDoItem item = list.get(msg.arg1);
					item.setCheck(!item.isCheck());
					Utils.sorting(list, 0);
					adapter.notifyDataSetChanged();
					setCountPurchaseProduct();
					break;
			}
		}
	};


	public Handler getHandler() {
		return handler;
	}
	private void handleNotification() {
		Calendar calendar = Calendar.getInstance();
int t = calendar.get(Calendar.HOUR_OF_DAY);



	switch ( t)
	{
		case 9:
			calendar.set(Calendar.HOUR_OF_DAY,10);
			break;
		case 12:
			calendar.set(Calendar.HOUR_OF_DAY, 13);
			break;
		case 15:
			calendar.set(Calendar.HOUR_OF_DAY,16);
			break;
		case 18:
			calendar.set(Calendar.HOUR_OF_DAY,19);
			break;
		case 22:
			calendar.set(Calendar.HOUR_OF_DAY,22);
			break;
	}


		Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

		AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		handleNotification();
		stopService(new Intent(this, Music.class));
		startService(new Intent(this, Music2.class));


		z.Init(context);//TODO:pref


		initList(); // Инициализация списка
		listview = (ListView) findViewById(R.id.listview);


		View header = LayoutInflater.from(this).inflate(R.layout.list_header, null, false);
		((TextView) header.findViewById(R.id.header_title)).setText(R.string.txt_header_title);
		listview.addHeaderView(header);

		footer = LayoutInflater.from(this).inflate(R.layout.list_footer, null, false);
		listview.addFooterView(footer);
		buttonadd = (Button) findViewById(R.id.addzadach);
		buttondelete = (Button) findViewById(R.id.button);


		buttonadd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.dialog1);
				dialog.setTitle("Добавляем задачу =)");




				Button dialogButton = (Button) dialog.findViewById(R.id.button2);

				dialogButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				Button dialogButton2 = (Button) dialog.findViewById(R.id.button1);

				dialogButton2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {


						EditText a = (EditText) dialog.findViewById(R.id.editText);
						String nameproblam = a.getText().toString();
						EditText a1 = (EditText) dialog.findViewById(R.id.editText2);
						String description = a1.getText().toString();
						EditText a2 = (EditText) dialog.findViewById(R.id.editText3);
						String date = a2.getText().toString();
						EditText a3 = (EditText) dialog.findViewById(R.id.editText4);
						int b3 = Integer.parseInt(a3.getText().toString());



						NameUser = z.GetString("Name", "");
						connector.insertTsk(NameUser, nameproblam, description, date,0);
						adapter.FAr(nameproblam, description, date, b3);
						Utils g = new Utils();
						g.sorting(list, 1);
						dialog.dismiss();
					}
				});
				dialog.show();
			}
		});
		buttondelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.delete);
				dialog.setTitle("Удаляем ненужное =)");
				Button dialogButton = (Button) dialog.findViewById(R.id.cancel);
				dialogButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				Button dialogButton2 = (Button) dialog.findViewById(R.id.OK1);

				dialogButton2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						EditText a = (EditText) dialog.findViewById(R.id.editText5);
						int b = Integer.parseInt(a.getText().toString());
						connector.deletetask(b);
						adapter.DeleteIndex(b);
						dialog.dismiss();

					}
				});
				dialog.show();

			}
		});
		adapter = new CustomListAdapter(list, this);
		setCountPurchaseProduct(); // Расчет сделанных дел //TODO:DOPILIT
		listview.setAdapter(adapter);


		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id) {

				if (position == 0 || position == list.size() + 1)
					return;
				Message msg = new Message();
				msg.arg1 = position - 1;
				msg.what = MSG_CHANGE_ITEM;
				handler.sendMessage(msg);
			}
		});

		//  \ по галочкам
		//Utils.setList(listview, this);
		//Utils.sorting(list, 0);

	}

	  //Инициализация списка

	private void initList() {
        preference_manager pf = preference_manager.GetInstanse();
		pf.Init(this);
		Log.v("All",pf.GetString("Name", ""));
			list = connector.onCursorClick(pf.GetString("Name", ""));
	}



	private void setCountPurchaseProduct() {
		int count = 0;
		Iterator<ToDoItem> it = list.iterator();
		while (it.hasNext()) {
			ToDoItem item = it.next();
			if (item.isCheck())
				count++;
		}

		((TextView) footer.findViewById(R.id.footer_title)).setText(String.format(getString(R.string.txt_footer_title), count));
	}
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setTitle(getString(R.string.BackString1))
				.setMessage(getString(R.string.BackString2))
				.setNegativeButton(android.R.string.no, null)
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frgmCont)).commit();
						stopService(new Intent(MainActivity.this, Music2.class));
						System.exit(0);
					}
				}).create().show();
	}

}



