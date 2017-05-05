package User;

public class TechTalk {
	protected int id;
	protected String date;
	protected String title;
	protected String Description;
	protected String Presenter;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPresenter() {
		return Presenter;
	}

	public void setPresenter(String presenter) {
		Presenter = presenter;
	}

	public TechTalk() {
	}

	public TechTalk(int id) {
		this.id = id;
	}

	public TechTalk(int id,String date, String title, String Description, String Presenter) {
		this(date,title,Description , Presenter);
		this.id = id;
	}
	
	public TechTalk(String date,String title, String Description, String Presenter) {
		this.date=date;
		this.title = title;
		this.Description = Description;
		this.Presenter = Presenter;
	}
	
	public TechTalk(int id,String date, String title) {
		this.title=title;
		this.id = id;
		this.date=date;
	}

}
