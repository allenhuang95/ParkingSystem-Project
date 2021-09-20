package parkinghelper.view;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import parkinghelper.AppStarter;
import parkinghelper.model.Booking;
import parkinghelper.model.Enforcer;

public class ManagePaymentPageController {
	@FXML
	private Button search;
	@FXML
	private Button back;
	@FXML
	private Button verify;
	@FXML
	private TextField id;
	@FXML
	private Label bookingid1;
	@FXML
	private Label lot1;
	@FXML
	private Label email1;
	@FXML
	private Label from1;
	@FXML
	private Label to1;
	@FXML
	private Label fee1;
	@FXML
	private Label status1;
	@FXML
	private Label prompt1;
	@FXML
	private Label prompt2;
	
	private AppStarter appStarter;
	
	@FXML
	private void handleSearch() {
		String ID = id.getText();
		if(!appStarter.getBookingList().containsKey(ID)) {
			prompt1.setText("Booking does not exist");
		}else {
			HashMap<String, Booking> h = appStarter.getBookingList();
			Booking b = h.get(ID);
			bookingid1.setText(ID);
			email1.setText(b.getEmail());
			lot1.setText(Integer.toString(b.getLot()));
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			from1.setText(b.getFrom().format(fmt));
			to1.setText(b.getTo().format(fmt));
			fee1.setText("$"+Double.toString(b.getAmountDue()));
			status1.setText(b.getPaymentStatus());
		}
	}
	
	@FXML
	private void handleBack() {
		appStarter.LoadAdminSelectionPage();
	}
	
	@FXML
	private void handleChangeStatus() {
		Booking booking = appStarter.getBookingList().get(bookingid1.getText());
		if(status1.getText().equals("#")) {
			prompt2.setText("Please choose a booking to proceed");
		}else if (status1.getText().equals("Paid")) {
			prompt2.setText("This booking is already paid");
		}else if(status1.getText().equals("Unpaid")) {
			prompt2.setText("This booking is not paid yet");
		}else {
			booking.setPaymentStatus("Paid");
			bookingid1.setText("#");
			prompt2.setText("Payment verified");
			lot1.setText("#");
			from1.setText("#");
			to1.setText("#");
			fee1.setText("#");
			status1.setText("#");
			appStarter.getDatabase().RebuildBookingDatabase();
		}
		
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
	}
}
