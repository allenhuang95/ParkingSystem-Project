package parkinghelper.view;


import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import parkinghelper.AppStarter;
import parkinghelper.model.Booking;
import parkinghelper.model.User;

public class CustomerPageController {
	@FXML
	private Button search;
	
	@FXML
	private Button book;
	
	@FXML
	private Button back;
	
	@FXML
	private ChoiceBox<String> hourbox;
	private ObservableList<String> values;
	//ObservableList<String> values = FXCollections.observableArrayList("0.5","1","1.5","2","2.5","3");
	
	
	@FXML 
	private Label hi;
	
	@FXML
	private TextField lot;
	
	
	@FXML 
	private Label status;
	@FXML 
	private Label lotnumber;
	@FXML 
	private Label prompt1;
	@FXML 
	private Label prompt2;
	@FXML
	private Label fees;
	
	private AppStarter appStarter;
	private int lot_number;
	private double parking_hour;
	
	public CustomerPageController() {
		lot_number = 0;
		parking_hour = 0;
		values = FXCollections.observableArrayList("0.5","1","1.5","2","2.5","3","3.5","4","4.5","5");
	}
	
	@FXML
	private void initialize() {
		hourbox.setValue("0.5");
		hourbox.setItems(values);
	}
	
	@FXML
	private void setHi() {
		hi.setText("Hi "+appStarter.getCurrentUser().getName()+"!");
	}
	
	@FXML
	private void handleSearch() {
		prompt1.setText("");
		prompt2.setText("");
		try {
			lot_number = Integer.parseInt(lot.getText());
		} catch (NumberFormatException e) {
			prompt1.setText("Lot# must be a number!");
		}
		if(lot_number > 10 || lot_number <1) {
			prompt1.setText("Lot# invalid! Please note only spot 1-10 are available for tests since we are using a csv database.");
		}else {
			parking_hour = Double.parseDouble(hourbox.getValue());
			
			lotnumber.setText(Integer.toString(lot_number));
			if(appStarter.getSpaceList().get(lot_number).getStatus().equals("Disabled")) {
				status.setText("Disabled");
			}else {
				if(appStarter.getSpaceList().get(lot_number).getBookingID().equals("N/A")) {
					status.setText("Available");
					fees.setText("Fees of "+parking_hour+" hours' parking: $" + parking_hour/0.5);
				}else {
					status.setText("Occupied");
				}
			}
		}
		
	}
	
	@FXML
	private void handleBack() {
		appStarter.LoadCustomerSelectionPage();
	}
	
	@FXML
	private void handleBook() {
		prompt1.setText("");
		prompt2.setText("");
		if(lot_number==0 || parking_hour==0) {
			prompt2.setText("Please pick your lot and decide how long do you want to park.");
		}else if (!status.getText().equals("Available")) {
			prompt2.setText("Oops! The spot you pick is "+status.getText()+". Please pick another spot.");
		}else if(parking_hour != Double.parseDouble(hourbox.getValue())){
			prompt2.setText("Parking hout has changed. Please search again to check if avaliable");
		}
		else {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime to = now.plusMinutes((long)((long) 60*parking_hour));
			Booking b = new Booking(appStarter.getCurrentUser().getEmail(), lot_number, now, to, "0", parking_hour/0.5, "Unpaid");
			b.generateID();
			boolean flag = appStarter.getCurrentUser().addBooking(b);
			if(flag==false) {
				prompt2.setText("Oops! You have 3 bookings already!");
			}else {
				appStarter.getSpaceList().get(lot_number).setBookingID(b);
				appStarter.getDatabase().addBooking(b);
				appStarter.getDatabase().RebuildBookingDatabase();
				appStarter.getDatabase().ReBuildUserDataBase();
				appStarter.getDatabase().RebuildSpacesDatabase();
				appStarter.LoadBookingSuccessPage();
			}
			
		}
	}
	
	public void setAppStarter(AppStarter appStarter) {
		this.appStarter = appStarter;
		setHi();
	}
}