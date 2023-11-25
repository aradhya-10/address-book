import java.util.*;

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

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully!");
		contact.display();
    }

	public void displayContacts() {
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

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Address Book");
		AddressBook addressBook = new AddressBook();

		while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Display Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

			switch (choice) {
                case 1:
                    addressBook.createContact(sc);
                    break;
                case 2:
                    addressBook.displayContacts();
                    break;
				case 3:
					addressBook.editContact(sc);
                    break;
                case 4:
                    System.out.println("Exiting Address Book CLI. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
		}
	}
}

