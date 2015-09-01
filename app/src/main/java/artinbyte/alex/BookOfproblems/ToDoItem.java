package artinbyte.alex.BookOfproblems;

import java.io.Serializable;

/**
 * Айтем для списка
 */
public class ToDoItem implements Serializable {

	private static final long serialVersionUID = 2008719019880549886L;
	
	private String name; // Название дела
    private String description;
	private String date;
	private boolean check; // Сделано/Не сделано
	private int index; // Позиция в списке в момент добавления
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	
	public boolean isCheck() {
		return check;
	}
	
	public void setCheck(boolean check) {
		this.check = check;
	}
			
	public int getIndex() {
		return index;
	}

     public  void inpl()
	 {
		 index+=1;
	 }
	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public ToDoItem(String name,String description, String date, int index) {
		setName(name);
		setDate(date);
		setDescription(description);
		setIndex(index);
		setCheck(false);
	}
	public ToDoItem(String name,String description, String date, int index,Boolean check) {
		setName(name);
		setDate(date);
		setDescription(description);
		setIndex(index);
		setCheck(check);
	}
	

}
