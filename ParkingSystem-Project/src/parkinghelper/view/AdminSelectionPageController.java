package parkinghelper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import parkinghelper.AppStarter;

public class AdminSelectionPageController {
	@FXML
	private Button add;
	
	@FXML
	private Button remove;
	
	@FXML
	private Button pay;
	
	@FXML
	private Button logoff;
	
	private AppStarter appStarter;
	
	@FXML
	private void handleAdd() {
		appStarter.LoadAddEnforcerPage();
	}
	@FXML
	private void handleRemove() {
		appStarter.LoadRemoveEnforcerPage();
	}
	@FXML
	private void handlePay() {
		appStarter.LoadManagePaymentPage();;
	}
	@FXML
	private void handleLogOff() {
		appStarter.LoadWelcomePage();
	}
	
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
	}
}
