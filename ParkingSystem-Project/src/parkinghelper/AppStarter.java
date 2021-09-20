package parkinghelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import parkinghelper.model.Booking;
import parkinghelper.model.Database;
import parkinghelper.model.Enforcer;
import parkinghelper.model.ParkingSpace;
import parkinghelper.model.User;
import parkinghelper.view.AddEnforcerController;
import parkinghelper.view.AdminSelectionPageController;
import parkinghelper.view.BookingSuccessPageController;
import parkinghelper.view.CustomerPageController;
import parkinghelper.view.CustomerSelectionPageController;
import parkinghelper.view.EnforcerSelectionPage;
import parkinghelper.view.ManageBookingPageController;
import parkinghelper.view.ManagePaymentPageController;
import parkinghelper.view.ManageSpacesPageController;
import parkinghelper.view.MasterContainerController;
import parkinghelper.view.MyBookingPageController;
import parkinghelper.view.PaymentPageController;
import parkinghelper.view.RemoveEnforcerPageController;
import parkinghelper.view.SignUpPageController;
import parkinghelper.view.SignUpSuccessPageController;
import parkinghelper.view.WelcomePageController;

public class AppStarter extends Application {
	
	private Stage primaryStage;
	private BorderPane masterLayout;
	private HashMap<String, User> users;
	private HashMap<Integer, ParkingSpace> parkingSpaces;
	private HashMap<String, Booking> bookings;
	private HashMap<String, Enforcer> enforcers;
	
	private Database database;
	
	private User currentUser;
	private String bookToPay;
	
	@Override
	public void start(Stage primaryStage) {
		currentUser = new User();
		this.primaryStage = primaryStage;
		this.primaryStage.setWidth(500);
		this.primaryStage.setHeight(510);
		this.primaryStage.setTitle("Haolun's Parking helper");
		
		//Since no sql database is involved, the app needs to load the database every time it starts.
		database = new Database();
		users = database.getUserList();
		parkingSpaces = database.getSpaceList();
		bookings = database.getBookingList();
		enforcers = database.getEnforcerList();
		//database.RebuildSpacesDatabase();
		PrepareMasterContainer();
		
		LoadWelcomePage();
	}
	
	
	public void PrepareMasterContainer() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/MasterContainer.fxml"));
			masterLayout = (BorderPane) loader.load();
			//masterLayout = (BorderPane) FXMLLoader.load(getClass().getResource("view/mastercontainer.fxml"));
			
			Scene scene = new Scene(masterLayout);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
	public void LoadWelcomePage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/WelcomePage.fxml"));
			AnchorPane welcomePage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(welcomePage);
			
			WelcomePageController controller = loader.getController();
			controller.setAppStarter(this);
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadSignUpPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/SignUpPage.fxml"));
			AnchorPane SignUpPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(SignUpPage);
			
			SignUpPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadCustomerSelectionPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/CustomerSelectionPage.fxml"));
			AnchorPane CustomerSelectionPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(CustomerSelectionPage);
			
			CustomerSelectionPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadCustomerPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/CustomerPage.fxml"));
			AnchorPane CustomerPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(CustomerPage);
			
			CustomerPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadSignUpSuccessPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/SignUpSuccessPage.fxml"));
			AnchorPane SignUpSuccessPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(SignUpSuccessPage);
			
			SignUpSuccessPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadBookingSuccessPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/BookingSuccessPage.fxml"));
			AnchorPane BookingSuccessPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(BookingSuccessPage);
			
			BookingSuccessPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadMyBookingPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/MyBookingPage.fxml"));
			AnchorPane MyBookingPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(MyBookingPage);
			
			MyBookingPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadPaymentPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/PaymentPage.fxml"));
			AnchorPane PaymentPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(PaymentPage);
			
			PaymentPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadEnforcerSelectionPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/EnforcerSelectionPage.fxml"));
			AnchorPane EnforcerSelectionPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(EnforcerSelectionPage);
			
			EnforcerSelectionPage controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadAdminSelectionPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/AdminSelectionPage.fxml"));
			AnchorPane AdminSelectionPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(AdminSelectionPage);
			
			AdminSelectionPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadAddEnforcerPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/AddEnforcerPage.fxml"));
			AnchorPane AddEnforcerPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(AddEnforcerPage);
			
			AddEnforcerController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadRemoveEnforcerPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/RemoveEnforcerPage.fxml"));
			AnchorPane RemoveEnforcerPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(RemoveEnforcerPage);
			
			RemoveEnforcerPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadManagePaymentPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/ManagePaymentPage.fxml"));
			AnchorPane ManagePaymentPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(ManagePaymentPage);
			
			ManagePaymentPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadManageSpacesPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/ManageSpacesPage.fxml"));
			AnchorPane ManageSpacesPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(ManageSpacesPage);
			
			ManageSpacesPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void LoadManageBookingPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppStarter.class.getResource("view/ManageBookingsPage.fxml"));
			AnchorPane ManageBookingsPage = (AnchorPane) loader.load();
			
			masterLayout.setCenter(ManageBookingsPage);
			
			ManageBookingPageController controller = loader.getController();
			controller.setAppStarter(this);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	
	public Database getDatabase() {
		return database;
	}
	
	public HashMap<Integer, ParkingSpace> getSpaceList() {
		return parkingSpaces;
	}
	
	public HashMap<String, Booking> getBookingList(){
		return bookings;
	}
	
	public HashMap<String, Enforcer> getEnforcerList(){
		return enforcers;
	}
	
	public void setCurrentUser(User u) {
		this.currentUser = u;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setBookToPay(String s) {
		bookToPay = s;
	}
	
	public String getBookToPay() {
		return bookToPay;
	}
	
	public void launchTest() {
		
	}
	
 	public static void main(String[] args) {
		launch(args);
	}
}
