package parkinghelper.view;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import parkinghelper.AppStarter;
import parkinghelper.model.Booking;
import parkinghelper.model.User;

public class MyBookingPageController {
	@FXML
	private Label bookingid1;
	@FXML
	private Label bookingid2;
	@FXML
	private Label bookingid3;
	
	@FXML
	private Label lot1;
	@FXML
	private Label lot2;
	@FXML
	private Label lot3;
	
	@FXML
	private Label from1;
	@FXML
	private Label from2;
	@FXML
	private Label from3;
	
	@FXML
	private Label to1;
	@FXML
	private Label to2;
	@FXML
	private Label to3;
	
	@FXML
	private Label fee1;
	@FXML
	private Label fee2;
	@FXML
	private Label fee3;
	
	@FXML
	private Label status1;
	@FXML
	private Label status2;
	@FXML
	private Label status3;
	
	@FXML
	private Button pay1;
	@FXML
	private Button pay2;
	@FXML
	private Button pay3;
	
	@FXML
	private Button cancel1;
	@FXML
	private Button cancel2;
	@FXML
	private Button cancel3;
	
	@FXML
	private VBox box1;
	@FXML
	private VBox box2;
	@FXML
	private VBox box3;
	
	private AppStarter appStarter;
	
	private Label[] bookingid;
	private Label[] lot;
	private Label[] from;
	private Label[] to;
	private Label[] fee;
	private Label[] status;
	private Button[] pay;
	private VBox[] boxs;
	private Button[] cancel;
	
	
	@FXML
	private void init() {
		Label[] bookingid = {bookingid1,bookingid2,bookingid3};
		Label[] lot = {lot1,lot2,lot3};
		Label[] from = {from1,from2,from3};
		Label[] to = {to1,to2,to3};
		Label[] fee = {fee1,fee2,fee3};
		Label[] status = {status1,status2,status3};
		Button[] pay = {pay1,pay2,pay3};
		VBox[] boxs = {box1,box2,box3};
		Button[] cancel = {cancel1,cancel2,cancel3};
		
		
		User u = appStarter.getCurrentUser();
		String[] bookings = u.getBooking();
		for(int i=0; i<3; i++) {
			if(bookings[i].equals("N/A")) {
				boxs[i].setVisible(false);
			}else {
				Booking b = appStarter.getBookingList().get(bookings[i]);
				bookingid[i].setText(b.getBookingID());
				lot[i].setText(Integer.toString(b.getLot()));
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				from[i].setText(b.getFrom().format(fmt));
				to[i].setText(b.getTo().format(fmt));
				fee[i].setText(Double.toString(b.getAmountDue()));
				status[i].setText(b.getPaymentStatus());
				
				if(!b.getPaymentStatus().equals("Unpaid")) {
					pay[i].setVisible(false);
					cancel[i].setVisible(false);
				}else {
					
				}
				
			}
		}
	}
	
	@FXML
	private void handlePay1() {
		appStarter.setBookToPay(bookingid1.getText());
		appStarter.LoadPaymentPage();
	}
	@FXML
	private void handlePay2() {
		appStarter.setBookToPay(bookingid2.getText());
		appStarter.LoadPaymentPage();
	}
	@FXML
	private void handlePay3() {
		appStarter.setBookToPay(bookingid3.getText());
		appStarter.LoadPaymentPage();
	}
	
	@FXML
	private void handelCancel1() {
		appStarter.getDatabase().removeBooking(bookingid1.getText());
		appStarter.getDatabase().RebuildBookingDatabase();
		appStarter.getDatabase().ReBuildUserDataBase();
		appStarter.getDatabase().RebuildSpacesDatabase();
		appStarter.LoadMyBookingPage();
	}
	
	@FXML
	private void handelCancel2() {
		appStarter.getDatabase().removeBooking(bookingid2.getText());
		appStarter.getDatabase().RebuildBookingDatabase();
		appStarter.getDatabase().ReBuildUserDataBase();
		appStarter.getDatabase().RebuildSpacesDatabase();
		appStarter.LoadMyBookingPage();
	}
	
	@FXML
	private void handelCancel3() {
		appStarter.getDatabase().removeBooking(bookingid3.getText());
		appStarter.getDatabase().RebuildBookingDatabase();
		appStarter.getDatabase().ReBuildUserDataBase();
		appStarter.getDatabase().RebuildSpacesDatabase();
		appStarter.LoadMyBookingPage();
	}
	
	@FXML
	private void handleBack() {
		appStarter.LoadCustomerSelectionPage();
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
		
		init();
	}
}
