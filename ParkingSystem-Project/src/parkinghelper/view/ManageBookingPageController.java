package parkinghelper.view;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import parkinghelper.AppStarter;
import parkinghelper.model.Booking;

public class ManageBookingPageController {
	@FXML
	private Button search;
	@FXML
	private Button back;
	@FXML
	private TextField id;
	@FXML
	private Label bookingid1;
	@FXML
	private Label lot1;
	@FXML
	private Label email;
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
	
	private AppStarter appStarter;
	
	@FXML
	private void handleSearch() {
		prompt1.setText("");
		//prompt2.setText("");
		String ID = id.getText();
		if(!appStarter.getBookingList().containsKey(ID)) {
			prompt1.setText("Booking does not exist");
		}else {
			HashMap<String, Booking> h = appStarter.getBookingList();
			Booking b = h.get(ID);
			bookingid1.setText(ID);
			email.setText(b.getEmail());
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
		appStarter.LoadEnforcerSelectionPage();
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
	}
}
