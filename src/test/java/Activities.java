
public class Activities {
	
	private int id;
	private String title;
	private String dueDate;
	private boolean completed;
	
	public Activities(){};
	
	public Activities(int id, String title, String dueDate, boolean completed){
		this.id = id;
		this.title = title;
		this.dueDate = dueDate;
		this.completed = completed;
	}
	
	public Activities(int id, String title){
		this.id = id;
		this.title = title;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDueDate(){
		return dueDate;
	}
	public void setDueDate(String dueDate){
		this.dueDate = dueDate;
	}
	public boolean isCompleted(){
		return completed;
	}
	public void setCompleted(boolean completed){
		this.completed = completed;
	}
	
	

}
