/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * October 15, 2023
 * Library.java
 * This is the Library Class. In this class we will be able to not only direct our program to retrieve our 
 * Text File from where it is located but it will also assist us in implementing our Library Class to 
 * the rest of our program.
 * This class is also where we will store our present book collection along with adding any new 
 * book titles to our collection. Within this class we will also be able to remove and modify
 * our existing collection as well.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
import java.util.Scanner;

public class Library {

	// This Method Will Store Our Book Collection.

	private ArrayList<Book> books = new ArrayList<>();
	private int idCounter = 1;

	// This Method Will Assist Us In Loading Our Current Library That Was Previously Saved

	public void loadLibraryFromFile(String filename) {
		System.out.println("*************************************");

		// This Will Assist Us In Clearing Out The Existing Books Inside Our Collection
		try {
			books.clear();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 7) {
					String title = parts[0];
					String author = parts[1];
					int id = Integer.parseInt(parts[2]);
					boolean available = Boolean.parseBoolean(parts[3]);
					String checkedOutDate = parts[4];
					String dueDate = parts[5];

					if (checkedOutDate.equals("N/A")) {
						checkedOutDate = null;
					}
					if (dueDate.equals("N/A")) {
						dueDate = null;
					}

					Book book = new Book(title, author, id, available, checkedOutDate, dueDate);

					// This Will Assist Us In Adding The Book To Our ArrayList

					books.add(book);

				}

			}

			reader.close();
			System.out.println("Your Collection Was Successfully Loaded. ");

		} catch (IOException e) {
			System.err.println("There Was An Error Loading Your Collection");
		}
	}

	// This Method Will Assist Us In Adding A Book To The User's Collection And Also Updating The Collection Once The Book Has Been Added.

	public void addBook() {
		System.out.println("*************************************");
		Scanner scanner = new Scanner(System.in);

		System.out.println("Add A New Book To Your Collection: ");
		System.out.print("Enter The Title Of The Book That You Would Like To Add To Your Collection: ");
		String title = scanner.nextLine();
		System.out.print("Enter the author: ");
		String author = scanner.nextLine();
		
		System.out.print("Enter the book ID: ");
		int bookId = scanner.nextInt();
		scanner.nextLine();

		Book newBook = new Book();
		newBook.setTitle(title);
		newBook.setAuthor(author);
		newBook.setId(bookId);
		newBook.setAvailable(true);
		newBook.setDueDate(null);
		newBook.setCheckedOutDate(null);
		books.add(newBook);
		System.out.println("This Book Is Now Added To Your Collection ");
		System.out.println("********************************************************");

	}
	
	public void addBook(String title, String author) {
		Book newBook = new Book();
		newBook.setTitle(title);
		newBook.setAuthor(author);
		newBook.setId(idCounter);
		newBook.setAvailable(true);
		newBook.setDueDate(null);
		newBook.setCheckedOutDate(null);
		books.add(newBook);
		idCounter++;
	}
	
	public boolean hasBook(String title) {
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title))
				return true;
		}
		return false;
	}
	
	// This Method Is For Us To Remove A Book From The User's Collection By The Books ID. 

	public void removeBookByID(int RemoveBookByIDNumber) {
		System.out.println("_________________________________________");

		for (Book book : books) {
			if (book.getId() == RemoveBookByIDNumber) {
				books.remove(book);
				System.out.println("Book With ID " + RemoveBookByIDNumber + " Has Been Removed From Your Collection ");

				return;

			}
		}

		System.out.println("Book With ID " + RemoveBookByIDNumber + " Not Found In The Library. Try Again");
		System.out.println("_________________________________________");

	}

	// This Method Is For Us To Remove A Book By It's Title From The User's Collection. 

	public void removeBookByTitle(String RemoveBookByIDNumber) {
		System.out.println("_________________________________________");

		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(RemoveBookByIDNumber)) {
				books.remove(book);
				System.out.println("Book With Title " + RemoveBookByIDNumber
						+ "\nThis Book Has Now Been Deleted From Your Library Collection");

				return;
			}
		}

		System.out.println("Book With Title " + RemoveBookByIDNumber + "\nNot Found In The Library. Try Again");
		System.out.println("_________________________________________");

	}

	// This Method Will Assist Us In Checking Out A Book From The Library.

	public void BookCheckedOut(String NameOfTheBookToCheckOut) {
		System.out.println("_________________________________________");

		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(NameOfTheBookToCheckOut)) {
				if (book.isAvailable()) {
					book.checkOut();
				} else {
					System.out.println("Book With Title " + NameOfTheBookToCheckOut
							+ "\n This Book Is Already Check Out. My Appologies. You Are Able To Try Another One");

				}
				listBooks();

				return;

			}

		}

		System.out.println("Book With Title " + NameOfTheBookToCheckOut + "\nNot Found In The Library. Try Again");
		System.out.println("_________________________________________");

	}

	// This Method Will Assist Us In Returning A Book.

	public void BookCheckedBackIn(String NameOfTheBookToCheckIn) {
		System.out.println("_________________________________________");

		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(NameOfTheBookToCheckIn)) {
				if (!book.isAvailable()) {
					book.checkIn();

				} else {
					System.out.println("Book With Title " + NameOfTheBookToCheckIn + "\nThis Book Is Already Available ");
				}
				
				listBooks();

				return;
			}
		}

		System.out.println("Book With Title " + NameOfTheBookToCheckIn + "\nNot Found In The Library. Try Again");
		System.out.println("_________________________________________");

	}

	// This Method Will Assist Us In Taking A Look At Available Books To Check Out.

	public void listBooks() {
		System.out.println("Listing All Books");
		if (books.isEmpty()) {
			System.out.println("The Library Is Currently Empty. There Are No Books To List");
		} else {
			System.out.println("List of Books In The Library: ");
			for (Book book : books) {
				System.out.println(book.toString());
			}
		}
	}
	
	// This Method Will Assist Us In Saving, Updating, and Exiting Our Program

	public void SaveYourLibraryCollection(String FileSaved) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(FileSaved));
			
			for (Book book : books) {
				writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getId() + "," + book.isAvailable()
						+ "," + formatNullableDate(book.getCheckedOutDate()) + "," 
						+ formatNullableDate(book.getDueDate()));
				writer.newLine();
				
			}
			
			writer.close();
			System.out.println("Your Collection Has Now Been Saved ");
			
		} catch (IOException e) {
			System.err.println("There Was An Error When Saving Your Collection: " + e.getMessage());
		}
		
	}

	private String formatNullableDate(Date date) {
			if (date == null) {
				return "N/A";
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
				return dateFormat.format(date);	
			}
			
		}
	
	
	public ArrayList<Book> getBooks() {
		return books;
	}
	
	public int getIdCounter() {
		return idCounter;
	}
	
	public void setIdCounter(int idCounter) {
		this.idCounter = idCounter;
	}
	
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
}