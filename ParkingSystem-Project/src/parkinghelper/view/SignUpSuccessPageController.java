package parkinghelper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import parkinghelper.AppStarter;

public class SignUpSuccessPageController {
	@FXML
	private Label hi;
	
	@FXML
	private Button login;
	
	private AppStarter appStarter;
	
	@FXML
	private void setHi() {
		hi.setText("Congratulation "+appStarter.getCurrentUser().getName()+"!");
	}
	
	@FXML
	private void handlelogin() {
		appStarter.LoadWelcomePage();
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
		setHi();
	}
}
