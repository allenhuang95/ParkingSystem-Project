package parkinghelper.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;


public class Database {
	private HashMap<String, User> users;
	private HashMap<Integer, ParkingSpace> parkingSpaces;
	private HashMap<String, Booking> bookings;
	private HashMap<String, Enforcer> enforcers;
	
	public Database() {
		ReloadBookingDatabase();
		RebuildBookingDatabase();
		ReloadUserDatabase();
		ReBuildUserDataBase();
		ReloadSpaceDatabase();
		RebuildSpacesDatabase();
		ReloadEnforcerDatabase();
		RebuildEnforcerDatabase();
	}
	
	public HashMap<String, User> getUserList() {
		return users;
	}
	
	public HashMap<Integer, ParkingSpace> getSpaceList() {
		return parkingSpaces;
	}
	
	public HashMap<String, Booking> getBookingList() {
		return bookings;
	}
	
	public HashMap<String, Enforcer> getEnforcerList(){
		return enforcers;
	}
	
	public void addUser(User u) {
		this.users.put(u.getEmail(), u);
	}
	
	public void addBooking(Booking b) {
		this.bookings.put(b.getBookingID(), b);
	}
	
	public void addEnforcer(Enforcer e) {
		this.enforcers.put(e.getID(), e);
	}
	
	
	//Repopulate Booking objects to database
	public void RebuildBookingDatabase() {
		try {
			FileWriter writer = new FileWriter("Bookings.csv");
			for(String id: bookings.keySet()) {
				writer.write(id+",");
				writer.write(bookings.get(id).getEmail()+",");
				writer.write(bookings.get(id).getLot()+",");
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				writer.write(bookings.get(id).getFrom().format(fmt)+",");
				writer.write(bookings.get(id).getTo().format(fmt)+",");
				writer.write(bookings.get(id).getAmountDue()+",");
				writer.write(bookings.get(id).getPaymentStatus()+"\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Repopulate ParkingSpace objects to database
	public void RebuildSpacesDatabase() {
		try {
			FileWriter writer = new FileWriter("ParkingSpaces.csv");
			for(int id:parkingSpaces.keySet()) {
				writer.write(id+",");
				writer.write(parkingSpaces.get(id).getStatus()+",");
				writer.write(parkingSpaces.get(id).getBookingID()+"\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Repopulate User objects to database
	public void ReBuildUserDataBase() {
		try {
			FileWriter writer = new FileWriter("Users.csv");
			for(String email: users.keySet()) {
				writer.write(email+",");
				writer.write(users.get(email).getPassword()+",");
				writer.write(users.get(email).getFirstName()+",");
				writer.write(users.get(email).getLastName()+",");
				String[] booking = users.get(email).getBooking();
				writer.write(booking[0]+",");
				writer.write(booking[1]+",");
				writer.write(booking[2]+"\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void RebuildEnforcerDatabase() {
		try {
			FileWriter writer = new FileWriter("Enforcers.csv");
			for(String id: enforcers.keySet()) {
				writer.write(id+",");
				writer.write(enforcers.get(id).getEmail()+",");
				writer.write(enforcers.get(id).getfirstName()+",");
				writer.write(enforcers.get(id).getlastName()+"\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Scan through current database, remove bookings which have expired or cancelled. Used to maintain objects as well.
	public void ReloadBookingDatabase() {
		bookings = new HashMap<String, Booking>();
		try {
			Scanner dataReader = new Scanner(new File("Bookings.csv")).useDelimiter("[\n\r,]+");
			while(dataReader.hasNext()) {
				String id = dataReader.next();
				String email = dataReader.next();
				//int lot = dataReader.nextInt();
				String lot = dataReader.next();
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime from = LocalDateTime.parse(dataReader.next(), fmt);
				LocalDateTime to = LocalDateTime.parse(dataReader.next(), fmt);
				//System.out.print(to.isBefore(LocalDateTime.now()));
				if(!to.isBefore(LocalDateTime.now()) && !id.equals("cancelled")) {
					double amountDue = dataReader.nextDouble();
					String paymentStatus = dataReader.next();
					Booking b = new Booking(email, Integer.parseInt(lot), from, to, id, amountDue, paymentStatus);
					bookings.put(id, b);
				}else {
					dataReader.nextDouble();
					dataReader.next();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ReloadSpaceDatabase() {
		parkingSpaces = new HashMap<Integer, ParkingSpace>();
		try {
			Scanner dataReader = new Scanner(new File("ParkingSpaces.csv")).useDelimiter("[\n\r,]+");
			for(int i=0; i<10; i++) {
				int id = dataReader.nextInt();
				String status = dataReader.next();
				String bid = dataReader.next();
				if(!bid.equals("N/A")) {
					if(!bookings.containsKey(bid)) {
						bid="N/A";
					}
				}
				ParkingSpace p = new ParkingSpace(id, status, bid);
				parkingSpaces.put(i+1, p);
			}
			dataReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ReloadUserDatabase() {
		users = new HashMap<String, User>();
		try {
			Scanner dataReader = new Scanner(new File("Users.csv")).useDelimiter("[\n\r,]+");
			while(dataReader.hasNext()) {
				String email = dataReader.next();
				String password = dataReader.next();
				String fn = dataReader.next();
				String ln = dataReader.next();
				String[] ids = new String[3];
				for(int j=0; j<3; j++) {
					ids[j] = dataReader.next();
					if (!ids[j].equals("N/A")) {
						if(!bookings.containsKey(ids[j])) {
							ids[j] = "N/A";
						}
					}
				}
				User u = new User(email, password, fn, ln, ids[0], ids[1], ids[2]);
				users.put(email, u);
			}
			dataReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void ReloadEnforcerDatabase() {
		enforcers = new HashMap<String, Enforcer>();
		try {
			Scanner dataReader = new Scanner(new File("Enforcers.csv")).useDelimiter("[\n\r,]+");
			while(dataReader.hasNext()) {
				String id = dataReader.next();
				String email = dataReader.next();
				String fn = dataReader.next();
				String ln = dataReader.next();
				
				Enforcer e = new Enforcer(email, id, fn, ln);
				enforcers.put(id, e);
			}
			dataReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeBooking(String s) {
		users.get(bookings.get(s).getEmail()).removeBooking(s);
		parkingSpaces.get(bookings.get(s).getLot()).removeBooking();
		bookings.remove(s);
	}
	
	public void removeEnforcer(String s) {
		enforcers.remove(s);
	}
}
