import java.util.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

class Contact {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String email;

	public Contact(String firstName, String lastName, String address, String city, String state,
                   String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

	public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
	public String getCity() {
        return city;
    }
	
	public String getState() {
        return state;
    }
	
	 public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }
	
	public void setState(String state) {
		this.state = state;
	}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

	public void display() {
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Address: " + address + ", " + city + ", " + state + " - " + zip);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("----------------------------");
    }
}


class AddressBook {
    private ArrayList<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact newContact) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(newContact.getFirstName()) &&
                    contact.getLastName().equals(newContact.getLastName())) {
                System.out.println("Person already exists, try updating");
                return;
            }
        }
        contacts.add(newContact);
        System.out.println("Contact added successfully!");
        newContact.display();
    }
	
	public void displayContacts() {
		if(contacts.size()==0)
			System.out.println("No Contacts to display");
		else
			System.out.println("***********************\nAddressBook Contacts\n***********************\n\n");
			
        for (Contact contact : contacts) {
            contact.display();
        }
    }

	private void createContact(Scanner sc) {
        System.out.println("Enter contact details:");

        System.out.print("First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("State: ");
        String state = sc.nextLine();

        System.out.print("ZIP Code: ");
        String zip = sc.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);

		this.addContact(newContact);
    }

	public void editContact(Scanner sc) {
		System.out.println("Enter the name of the contact to edit:");

        System.out.print("First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName = sc.nextLine();

        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) 
			{
                System.out.print("Enter new address: ");
                contact.setAddress(sc.nextLine());
                System.out.print("Enter new city: ");
                contact.setCity(sc.nextLine());
                System.out.print("Enter new state: ");
                contact.setState(sc.nextLine());
                System.out.print("Enter new zip: ");
                contact.setZip(sc.nextLine());
                System.out.print("Enter new phone number: ");
                contact.setPhoneNumber(sc.nextLine());
                System.out.print("Enter new email: ");
                contact.setEmail(sc.nextLine());

                System.out.println("Contact updated successfully!");
				contact.display();
                return;
            }
        }
        System.out.println("Contact not found!");
    }

	public void deleteContact(Scanner sc) {
		System.out.println("Enter the name of the contact to delete:");

        System.out.print("First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName = sc.nextLine();

        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                contacts.remove(contact);
                System.out.println("Contact deleted successfully!");
                return;
            }
        }
        System.out.println("Contact not found!");
    }

	void bookSelected(){

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Address Book");

		while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Display Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. View Contacts from a City");
            System.out.println("6. View Contacts from a State");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
			int choice = -1;
			while(choice == -1){
				String input = sc.nextLine();
				try {
					choice = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Enter valid input");
					continue;
				}
			}

			switch (choice) {
                case 1:
                    this.createContact(sc);
                    break;
                case 2:
                    this.displayContacts();
                    break;
				case 3:
					this.editContact(sc);
                    break;
                case 4:
					this.deleteContact(sc);
                    break;
                case 5:
                    System.out.print("Enter city to view persons: ");
                    String city = sc.nextLine();
                    List<Contact> personsByCity = this.getPersonsByCity(city);
                    if (!personsByCity.isEmpty()) {
                        System.out.println("Persons in " + city + ":");
                        personsByCity.forEach(Contact::display);
                    } else {
                        System.out.println("No persons found in " + city);
                    }
                    break;
                case 6:
                    System.out.print("Enter state to view persons: ");
                    String state = sc.nextLine();
                    List<Contact> personsByState = this.getPersonsByState(state);
                    if (!personsByState.isEmpty()) {
                        System.out.println("Persons in " + state + ":");
                        personsByState.forEach(Contact::display);
                    } else {
                        System.out.println("No persons found in " + state);
                    }
                    break;
                case 7:
                    System.out.println("Exiting Address Book CLI. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    public List<Contact> getPersonsByCity(String city) {
        return contacts.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Contact> getPersonsByState(String state) {
        return contacts.stream()
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }
}

public class SystemManager {
    private Map<String, AddressBook> addressBooks;
    private AddressBook currentAddressBook; 

    public SystemManager() {
        this.addressBooks = new HashMap<>();
        this.currentAddressBook = null;
    }

    public void addAddressBook(String name) {
        if (!addressBooks.containsKey(name)) {
            addressBooks.put(name, new AddressBook());
            System.out.println("Address book '" + name + "' added successfully!");
        } else {
            System.out.println("Address book with name '" + name + "' already exists!");
        }
    }

    public void setCurrentAddressBook(String name) {
        if (addressBooks.containsKey(name)) {
            currentAddressBook = addressBooks.get(name);
            System.out.println("Address book '" + name + "' selected.");
        } else {
            System.out.println("Address book with name '" + name + "' not found!");
        }
    }

    public AddressBook getCurrentAddressBook() {
        return currentAddressBook;
    }

	public AddressBook getAddressBook(String name) {
        return addressBooks.get(name);
    }

    public void displayOptions(SystemManager systemManager, Scanner sc){
		System.out.println("\nMain Menu:");
            System.out.println("1. Create Address Book");
            System.out.println("2. View all Address Books");
            System.out.println("3. Select Address Book");
			System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = -1;
			while(choice == -1){
				String input = sc.nextLine();
				try {
					choice = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Enter valid input");
					continue;
				}
			}

            switch (choice) {
                case 1:
                    System.out.print("Enter name for new address book: ");
                    String newName = sc.nextLine();
                    systemManager.addAddressBook(newName);
                    break;
                case 2:
					for(String ab : addressBooks.keySet())
						System.out.println("# "+ ab);
					break;
                case 3:
                    System.out.print("Enter name of address book: ");
                    String selectedName = sc.nextLine();
                    AddressBook selectedAddressBook = systemManager.getAddressBook(selectedName);
                    if (selectedAddressBook != null) {
                             selectedAddressBook.bookSelected();
                    } else {
                        System.out.println("Address book with name '" + selectedName + "' not found!");
                    }
                    break;
				case 4:
                    System.out.println("Exiting System.");
					System.exit(0);
					break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
	}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Address Book System");
        SystemManager systemManager = new SystemManager();

        while (true) {
            systemManager.displayOptions(systemManager, sc);
        }
    }
    // Display options and other methods remain the same
}
