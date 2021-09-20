package parkinghelper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import parkinghelper.AppStarter;

public class EnforcerSelectionPage {
	@FXML
	private Button space;
	@FXML
	private Button book;
	@FXML
	private Button logoff;
	
	private AppStarter appStarter;
	
	@FXML
	private void handleManageSpace() {
		appStarter.LoadManageSpacesPage();
	}
	
	@FXML
	private void handleManageBooking() {
		appStarter.LoadManageBookingPage();
	}
	
	@FXML
	private void handleLogOff() {
		appStarter.LoadWelcomePage();
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
	}
	
}
