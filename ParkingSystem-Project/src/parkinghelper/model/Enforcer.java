package parkinghelper.model;

public class Enforcer  {
	private String email;
	private String id;
	private String firstname;
	private String lastname;
	
	public Enforcer(String e, String id, String fn, String ln) {
		email = e;
		this.id = id;
		firstname = fn;
		lastname = ln;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getfirstName() {
		return firstname;
	}
	
	public String getlastName() {
		return lastname;
	}
	
	public String getID() {
		return id;
	}
}
