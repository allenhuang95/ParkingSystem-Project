package parkinghelper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import parkinghelper.AppStarter;
import parkinghelper.model.Enforcer;

public class RemoveEnforcerPageController {
	@FXML
	private TextField id;
	@FXML
	private Button search;
	@FXML
	private Button remove;
	@FXML
	private Label email;
	@FXML
	private Label name;
	@FXML
	private Label prompt1;
	@FXML
	private Label prompt2;
	@FXML
	private Label id2;
	@FXML
	private Button back;
	
	private AppStarter appStarter;
	
	@FXML
	private void handleSearch() {
		prompt1.setText("");
		prompt2.setText("");
		String ID = id.getText();
		if(!appStarter.getEnforcerList().containsKey(ID)) {
			prompt1.setText("Enforcer does not exist");
		}else {
			Enforcer e = appStarter.getEnforcerList().get(ID);
			email.setText(e.getEmail());
			name.setText(e.getfirstName()+" "+e.getlastName());
			id2.setText(e.getID());
		}
	}
	
	@FXML
	private void handleBack() {
		appStarter.LoadAdminSelectionPage();
	}
	
	@FXML
	private void handelRemove() {
		prompt1.setText("");
		prompt2.setText("");
		if(email.getText().equals("#")) {
			prompt2.setText("Please enter enforcer ID to start!");
		}else {
			appStarter.getDatabase().removeEnforcer(id2.getText());
			appStarter.getDatabase().RebuildEnforcerDatabase();
			prompt2.setText("Enforcer removed");
			email.setText("#");
			name.setText("#");
			id2.setText("#");
		}
		
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
		
	}
}
