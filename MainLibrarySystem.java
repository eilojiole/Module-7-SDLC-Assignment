/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * October 15, 2023
 * MainLibrarySystem.java
 * This is our Main System Class. In this class we will be able to define the users options.
 * In This class we will also be able to retrieve our users input as well. This is also the class
 * where we will be able to save and update our library file based on the previous selections made
 * by our users.
 */

import java.util.Scanner;

public class MainLibrarySystem {
	
	public static void main(String[] args) {
		Library library = new Library();
		Scanner scanner = new Scanner(System.in);
		boolean quit = false;
		
		System.out.println("Welcome To The New Library System!");
		
		while(!quit) {
			
			// This Method Is to Display Our Menu Options To Our Users.

			System.out.println("\nSelect An Option: ");
			System.out.println("Enter 1. To Load A Library From A File");
			System.out.println("Enter 2. To Add A Book To Your Collection");
			System.out.println("Enter 3. To Remove A Book By ID");
			System.out.println("Enter 4. To Remove A Book By Title");
			System.out.println("Enter 5. To List All Books Within Your Collection");
			System.out.println("Enter 6. To Check Out A Book");
			System.out.println("Enter 7. To Check In/Return A Book");
			System.out.println("Enter 8. To Save Your Collection To A File And Quit");
			System.out.print("Enter Your Choice: ");
			
			// This Method Will Allow Us To Access The Users Options That They Select

			int choice = scanner.nextInt();
			scanner.nextLine();
			
			// This Method Will Perform The Actions Based On The Selection Made By The User

			switch (choice) {
			
			case 1:
				
				System.out.print("Enter The Filename To Load Your Library: ");
				String filename = scanner.nextLine();
				library.loadLibraryFromFile(filename);
				
				break;
				
			case 2:
				
				library.addBook();
				
				break;
				
			case 3:
				
				System.out.print("Enter The ID of The Book You Would Like To Remove: ");
				int RemoveBookByIDNumber = scanner.nextInt();
				scanner.nextLine();
				library.removeBookByID(RemoveBookByIDNumber);
				
				break;
				
			case 4:
				
				System.out.print("Enter The Title Of The Book You Would Like To Remove: ");
				String RemoveBookByName = scanner.nextLine();
				library.removeBookByTitle(RemoveBookByName);
				
				break;
				
			case 5:
				
				library.listBooks();
				
				break;
				
			case 6:
				
				System.out.print("Enter The Title Of The Book That You Would Like To Check Out: ");
				String NameOfTheBookToCheckOut = scanner.nextLine();
				library.BookCheckedOut(NameOfTheBookToCheckOut);
				
				break;
				
			case 7:
				
				System.out.print("Enter The Title Of The Book That You Would Like To Check In: ");
				String NameOfTheBookToCheckIn = scanner.nextLine();
				library.BookCheckedBackIn(NameOfTheBookToCheckIn);
				
				break;
				
			case 8:
				
				System.out.print("Enter The Name of The File To Save You Collection To: ");
				String FileSaved = scanner.nextLine();
				library.SaveYourLibraryCollection(FileSaved);
				quit = true;
				
				break;
				
			default:
				
				System.out.println("Invalid Choice. Please Make Your Selection Again. ");
				
			}
		}
		
		// This Method Is To Close Our Scanner Once We Are Done With Our Application

		scanner.close();
	}
}