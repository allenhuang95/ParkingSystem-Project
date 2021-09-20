package parkinghelper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import parkinghelper.AppStarter;
import parkinghelper.model.Booking;

public class PaymentPageController {
	@FXML
	private Label amount;
	@FXML
	private TextField name;
	@FXML
	private Button logoff;
	@FXML
	private Button pay;
	@FXML
	private Label prompt;
	
	private AppStarter appStarter;
	
	@FXML
	private void handlePay() {
		prompt.setVisible(false);
		Booking b = appStarter.getBookingList().get(appStarter.getBookToPay());
		if(!appStarter.getCurrentUser().getName().equals(name.getText())) {
			prompt.setVisible(true);
		}else {
			b.setPaymentStatus("Proccessing");
			appStarter.getDatabase().RebuildBookingDatabase();
			appStarter.LoadMyBookingPage();
		}
	}
	
	@FXML 
	private void handleLogOff() {
		appStarter.LoadWelcomePage();
	}
	
	@FXML
	private void init() {
		Booking b = appStarter.getBookingList().get(appStarter.getBookToPay());
		amount.setText("$"+Double.toString(b.getAmountDue()));
		prompt.setVisible(false);
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
		init();
	}
}
