package parkinghelper.model;

public class ParkingSpace {
	private int id;
	private String status;
	private String bookingID;
	
	public ParkingSpace(int i, String s, String b) {
		id = i;
		status = s;
		bookingID = b;
	}
	
	public int getID() {
		return id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String s) {
		status = s;
	}
	
	public String getBookingID() {
		return bookingID;
	}
	
	public void setBookingID(Booking b) {
		bookingID = b.getBookingID();
	}
	
	public void removeBooking() {
		bookingID = "N/A";
	}
}
