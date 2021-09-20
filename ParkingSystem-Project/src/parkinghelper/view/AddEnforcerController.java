package parkinghelper.view;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import parkinghelper.AppStarter;
import parkinghelper.model.Enforcer;

public class AddEnforcerController {
	@FXML
	private TextField email;
	@FXML
	private Label id;
	@FXML
	private TextField fn;
	@FXML
	private TextField ln;
	@FXML
	private Label prompt;
	
	private AppStarter appStarter;
	
	@FXML
	private void handleBack() {
		appStarter.LoadAdminSelectionPage();
	}
	
	@FXML
	private void handleAdd() {
		String e = email.getText();
		String f = fn.getText();
		String l = ln.getText();
		
		Enforcer enforcer = new Enforcer(e, id.getText(), f, l);
		appStarter.getDatabase().addEnforcer(enforcer);
		appStarter.getDatabase().RebuildEnforcerDatabase();
		prompt.setText("Enforcer "+id.getText()+" added!");
		init();
		email.clear();
		fn.clear();
		ln.clear();
	}
	
	private String generateID() {
		Random gen = new Random();
		String id = Integer.toString((gen.nextInt(900)+100))+Integer.toString((gen.nextInt(900)+100))+Integer.toString((gen.nextInt(900)+100));
		return id;
	}
	
	@FXML
	private void init() {
		String ID = this.generateID();
		while(appStarter.getEnforcerList().containsKey(ID)) {
			ID = this.generateID();
		}
		id.setText(ID);
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
		init();
	}
}
