package parkinghelper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import parkinghelper.AppStarter;
import parkinghelper.model.Database;
import parkinghelper.model.User;

public class SignUpPageController {
	@FXML
	private Button signUp;
	
	@FXML
	private Button signIn;
	
	@FXML
	private TextField email;
	private String emailString;
	
	@FXML
	private PasswordField password;
	private String passwordString;
	
	@FXML 
	private TextField fn;
	private String fname;
	
	@FXML 
	private TextField ln;
	private String lname;
	
	@FXML
	private Label prompt;
	
	private AppStarter appStarter;
	
	@FXML
	private void handleSignUp() {
		emailString = email.getText();
		passwordString = password.getText();
		fname = fn.getText();
		lname = ln.getText();
		
		SignUp(emailString, passwordString, fname, lname, appStarter.getDatabase());
	}
	
	private void SignUp(String e, String p, String f, String l, Database d) {
		if(d.getUserList().containsKey(e)) {
			prompt.setText("Email already exists. Please click sign in.");
		}else if (e.isEmpty()||p.isEmpty()||f.isEmpty()||l.isEmpty()) {
			prompt.setText("Please do not leave any field empty.");
		}else {
			User u = new User(e, p, f, l, "N/A", "N/A", "N/A");
			d.addUser(u);
			appStarter.setCurrentUser(u);
			appStarter.getDatabase().ReBuildUserDataBase();
			appStarter.LoadSignUpSuccessPage();
		}
	}
	
	@FXML
	private void handleSignIn() {
		appStarter.LoadWelcomePage();
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
		
	}
}
