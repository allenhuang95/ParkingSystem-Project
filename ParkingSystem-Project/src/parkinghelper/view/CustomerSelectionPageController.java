package parkinghelper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import parkinghelper.AppStarter;

public class CustomerSelectionPageController {
	@FXML
	private Button book;
	
	@FXML
	private Button my_booking;
	
	@FXML
	private Button logoff;
	
	@FXML
	private Label hi;
	
	private AppStarter appStarter;
	
	public  CustomerSelectionPageController() {
	}
	
	@FXML
	private void setHi() {
		hi.setText("Hi "+appStarter.getCurrentUser().getName()+"!");
	}
	
	@FXML
	private void handleLogOff() {
		appStarter.LoadWelcomePage();
	}
	
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
		setHi();
	}
}
