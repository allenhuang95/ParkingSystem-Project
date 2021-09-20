package parkinghelper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import parkinghelper.AppStarter;

public class BookingSuccessPageController {
	@FXML
	private Button mybooking;
	
	@FXML 
	private Button book;
	
	private AppStarter appStarter;
	
	@FXML
	private void handleBook() {
		appStarter.LoadCustomerPage();
	}
	
	@FXML
	private void handleMyBooking() {
		appStarter.LoadMyBookingPage();
	}
	
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
		
	}
}
