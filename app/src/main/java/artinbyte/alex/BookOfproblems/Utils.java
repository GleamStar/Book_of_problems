package artinbyte.alex.BookOfproblems;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/**
 * Специализированный класс для различных статических методов
 */
public class Utils {

	/**
	 * Устанавливает различные параметры для листа. В частности, убирает полосу разделения 
	 * у элементов списка.
	 */
	public static void setList(ListView list, Context context) {
		list.setSelector(android.R.color.transparent);
		ColorDrawable sage = new ColorDrawable(context.getResources().getColor(
				android.R.color.transparent));
		list.setDivider(sage);
		list.setDividerHeight(0);
	}
	public void SortIndex (List<ToDoItem> list,int a)
	{
		for ( int i=0;i<list.size();i++) {
			if (i == list.size()-1) {
				break;
			} else {
				int b = list.get(i).getIndex();
				if (b == a) {
					for (int j = i; j < list.size() -1; j++) {

						list.get(j).inpl();

					}
				} else {
					continue;
				}
			}
		}
		sorting(list, 1);
	}
	public  void DIndexDelete (List<ToDoItem> list,int a)
	{

		int k =0;
		for (ListIterator< ToDoItem> i = list.listIterator(); i.hasNext(); ) {
			ToDoItem  el = i.next();
			if (el.getIndex()==a) {
				i.remove();
			}
		}
		for (ListIterator< ToDoItem> i = list.listIterator(); i.hasNext(); ) {
			ToDoItem  el = i.next();
			el.setIndex(k+1);
			k+=1;
		}
	}
	public void poradok ( List<ToDoItem> list)
	{
		int k = 0;
		for (ListIterator< ToDoItem> i = list.listIterator(); i.hasNext(); ) {
			ToDoItem  el = i.next();
			el.setIndex(k+1);
			k+=1;
		}
	}
	/**
	 * Сортировка списка
	 * @param type 0 - сортировка по галочке
	 * @param type 1 - сортировка по индексу
	 */
	public static void sorting(List<ToDoItem> list, final int type)
	{
		Collections.sort(list, new Comparator<ToDoItem>() {

			@Override
			public int compare(ToDoItem item1, ToDoItem item2) {
				int compare = 0;
				switch (type) {
					case 0:
						Boolean bool_value1 = Boolean.valueOf(item1.isCheck());
						Boolean bool_value2 = Boolean.valueOf(item2.isCheck());
						compare = bool_value1.compareTo(bool_value2);
						if (compare == 0)
							compare = (item1.getIndex() > item2.getIndex()) ? 1 : -1;
						break;
					case 1:
						Integer int_value1 = Integer.valueOf(item1.getIndex());
						Integer int_value2 = Integer.valueOf(item2.getIndex());
						compare = int_value1.compareTo(int_value2);
						break;
				}
				return compare;
			}
		});
	}
}
