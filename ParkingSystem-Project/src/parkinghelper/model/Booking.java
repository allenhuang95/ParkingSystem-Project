package parkinghelper.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Booking {
	private String email;
	private int lot;
	private LocalDateTime from;
	private LocalDateTime to;
	
	private String bookingID;
	
	private double amountDue;
	private String paymentStatus;
	
	public Booking(String u, int l, LocalDateTime f, LocalDateTime t, String id, double amount, String payment) {
		email = u;
		lot = l;
		from = f;
		to = t;
		
		//LocalDateTime time = LocalDateTime.now();
		//bookingID = Integer.toString(l) + Integer.toString(time.getDayOfYear())+Integer.toString(time.getDayOfMonth())+Integer.toString(time.getMinute());
		bookingID = id;
		amountDue = amount;
		paymentStatus = payment;
	}
	
	public void generateID() {
		LocalDateTime time = LocalDateTime.now();
		bookingID = Integer.toString(lot) + Integer.toString(time.getDayOfYear())+Integer.toString(time.getDayOfMonth())+Integer.toString(time.getMinute());
	}
	
	public LocalDateTime getTo() {
		return to;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getLot() {
		return lot;
	}
	
	public LocalDateTime getFrom() {
		return from;
	}
	
	public String getBookingID() {
		return bookingID;
	}
	
	public double getAmountDue() {
		return amountDue;
	}
	
	public String getPaymentStatus() {
		return paymentStatus;
	}
	
	public void setPaymentStatus(String s) {
		paymentStatus = s;
	}
	
}
