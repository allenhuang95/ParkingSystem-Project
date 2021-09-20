package parkinghelper.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import parkinghelper.AppStarter;
import parkinghelper.model.Database;
import parkinghelper.model.Enforcer;
import parkinghelper.model.User;

public class WelcomePageController {
	@FXML
	private Button signin_button;
	
	@FXML
	private Button signup_button;
	
	@FXML
	private TextField signin_email;
	private String email;
	
	@FXML
	private PasswordField signin_password;
	private String password;
	
	@FXML
	private Label password_prompt;
	
	@FXML
	private ChoiceBox<String> role;
	private ObservableList<String> values;
	
	private AppStarter appStarter;
	
	@FXML
	private void handleSignin() {
		email = signin_email.getText();
		password = signin_password.getText();
		if(role.getValue().equals("Customer")) {
			AuthenticationCustomer(email, password, appStarter.getDatabase());
		}else if (role.getValue().equals("Enforcer")) {
			AuthenticationEnforcer(email, password, appStarter.getDatabase());
		}else {
			AuthenticationAdmin(email, password, appStarter.getDatabase());
		}
		
	}
	
	@FXML
	private void handleSignUp() {
		appStarter.LoadSignUpPage();
	}
	
	private void AuthenticationCustomer(String e, String p, Database d) {
		if(!d.getUserList().containsKey(e)) {
			password_prompt.setText("Email does not exist. Please click create account.");
		}else {
			User u = d.getUserList().get(e);
			if(u.getPassword().equals(p))
			{
				appStarter.setCurrentUser(u);
				appStarter.LoadCustomerSelectionPage();
			}else {
				password_prompt.setText("Your email is correct but password does not match, please re-enter your password.");	
			}
		}
	}
	
	private void AuthenticationEnforcer(String e, String p, Database d) {
		if(!d.getEnforcerList().containsKey(p)) {
			password_prompt.setText("ID does not exist. Please contact Admin.");
		}else {
			Enforcer u = d.getEnforcerList().get(p);
			if(u.getID().equals(p))
			{
				//appStarter.setCurrentUser(u);
				appStarter.LoadEnforcerSelectionPage();;
			}else {
				password_prompt.setText("Does not match. Please contact Admin");	
			}
		}
	}
	
	private void AuthenticationAdmin(String e, String p, Database d) {
		if(e.equals("admin")) {
			if(p.equals("admin")) {
				appStarter.LoadAdminSelectionPage();
			}else {
				password_prompt.setText("Wrong Password. Please choose other options if you're not Admin");
			}
		}else {
			password_prompt.setText("Wrong Account. Please choose other options if you're not Admin");
		}
	}
	
	
	@FXML
	private void init() {
		values = FXCollections.observableArrayList("Customer","Enforcer","Admin");
		role.setValue("Customer");
		role.setItems(values);
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
		init();
		
	}
}
