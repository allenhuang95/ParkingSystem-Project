package parkinghelper.model;

import java.util.LinkedList;

public class User {
	private String firstName;
	private String password;
	private String lastName;
	private String email;
	private String[] bookingID;
	
	public User(String e, String p, String fn, String ln, String id1, String id2, String id3) {
		email = e;
		password = p;
		firstName = fn;
		lastName = ln;
		
		bookingID = new String[3];
		bookingID[0] = id1;
		bookingID[1] = id2;
		bookingID[2] = id3;
	}
	
	public User() {
		
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return firstName+" "+lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String[] getBooking() {
		return bookingID;
	}
	
	public Boolean addBooking(Booking b) {
		for(int i=0; i<3; i++) {
			if(bookingID[i].equals("N/A")) {
				bookingID[i] = b.getBookingID();
				return true;
			}
		}
		return false;
	}
	
	public void removeBooking(String s) {
		for(int i=0; i<3; i++) {
			if(bookingID[i].equals(s)) {
				bookingID[i] = "N/A";
			}
		}
	}
}
