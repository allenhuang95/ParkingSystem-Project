package parkinghelper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import parkinghelper.AppStarter;
import parkinghelper.model.ParkingSpace;

public class ManageSpacesPageController {
	@FXML
	private TextField id;
	
	@FXML
	private Label lot;
	
	@FXML
	private Label status;
	
	@FXML
	private Label booking;
	
	@FXML
	private Label prompt1;
	@FXML
	private Label prompt2;
	
	private AppStarter appStarter;
	
	@FXML
	private void handleSearch() {
		prompt1.setText("");
		prompt2.setText("");
		String num = id.getText();
		if(!appStarter.getSpaceList().containsKey(Integer.parseInt(num))) {
			prompt1.setText("Invalid Space. Only space 1-10 are open for tests.");
		}else {
			ParkingSpace p = appStarter.getSpaceList().get(Integer.parseInt(num));
			
			lot.setText(Integer.toString(p.getID()));
			status.setText(p.getStatus());
			booking.setText(p.getBookingID());
		}
		
	}
	
	@FXML
	private void handleChangeStatus() {
		prompt1.setText("");
		prompt2.setText("");
		String num = lot.getText();
		if(num.equals("#")) {
			prompt2.setText("Please pick a space to start");
		}else {
			ParkingSpace p = appStarter.getSpaceList().get(Integer.parseInt(num));
			if(!booking.getText().equals("N/A")) {
				prompt2.setText("This space is currently occupied");
			}else {
				if(status.getText().equals("In use")) {
					p.setStatus("Disabled");
					prompt2.setText("Space disabled");
				}else {
					p.setStatus("In use");
					prompt2.setText("Space enabled");
				}
				appStarter.getDatabase().RebuildSpacesDatabase();
				lot.setText("#");
				status.setText("#");
				booking.setText("#");
			}
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
