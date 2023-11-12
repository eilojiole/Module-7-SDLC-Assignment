/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * October 15, 2023
 * LMSTestCase.java
 * This is our test case class which will serve at the main feature to ensure that all our methods are working.
 * In this class we will be able to test the Add Book, Remove Book By ID, Check Out Book, Check In Book, 
 * and Remove Book By Title features. 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class LMSTestCase {
	
	@Test
	public void testAddBook() {
		
		// This Method Is To Test The Create A New Book Feature
		
		Library library = new Library ();
		int initialSize = library.getBooks().size();
		
		// This Will Assist Us in Testing The Feature That Will Add The Book To The Library
		
		library.addBook();
		
		// This Will Assist Us In Confirming If The Book Was Added
		
		int finalSize = library.getBooks().size();
		assertEquals(initialSize + 1, finalSize);
		
	}
	
	@Test
	public void testRemoveBookByID() {
		
		// This Method Is To Test The Remove A Book By It's ID Feature
		
		Library library = new Library();
		Book book1 = new Book("Cool Guy", "John", 1, true, "N/A", "N/A");
		Book book2 = new Book("Goal", "Cool", 2, true, "N/A", "N/A");
		
		// This Will Assist Us in Retrieving our Books

		library.getBooks().add(book1);
		library.getBooks().add(book2);
		
		int initialSize = library.getBooks().size();
		
		// This Method Will Assist Us in Removing Our Book By It's ID Number

		library.removeBookByID(1);
		
		// This Will Assist Us In Confirming If The Book Was Removed

		int finalSize = library.getBooks().size();
		assertEquals(initialSize - 1, finalSize);
	}
	
	@Test
	public void testBookCheckedOut() {
		
		// This Method Is To Test The Check Out A Book Feature

		Library library = new Library();
		Book book1 = new Book("Cool Guy", "John", 1, true, "N/A", "N/A");
		
		// This Will Assist Us in Retrieving our Books

		library.getBooks().add(book1);
		
		// This Will Assist Us In Confirming That The Book Was Checked Out

		library.BookCheckedOut("Cool Guy");
		assertFalse(library.getBooks().get(0).isAvailable());
	}
	
	@Test
	public void testBookCheckedBackIn() {
		
		// This Method Is To Test The Check In A Book Feature

		Library library = new Library();
		Book book1 = new Book("Cool Guy", "John", 1, false, "N/A", "N/A");
		
		// This Will Assist Us in Retrieving our Books

		library.getBooks().add(book1);
		
		// This Will Assist Us In Confirming That The Book Was Checked In

		library.BookCheckedBackIn("Cool Guy");
		assertTrue(library.getBooks().get(0).isAvailable());
	}
	
	@Test
	public void testRemoveBookByTitle() {
		
		// This Method Is To Test The Remove A Book By It's Title Feature

		
		Library library = new Library();
		Book book1 = new Book("Cool Guy", "John", 1, true, "N/A", "N/A");
		Book book2 = new Book("Goal", "Cool", 2, true, "N/A", "N/A");
		
		// This Will Assist Us in Retrieving our Books

		library.getBooks().add(book1);
		library.getBooks().add(book2);
		
		int initialSize = library.getBooks().size();
		
		// This Method Will Assist Us in Removing Our Book By It's Title

		library.removeBookByTitle("Cool Guy");
		
		// This Will Assist Us In Confirming If The Book Was Removed
		
		int finalSize = library.getBooks().size();
		assertEquals(initialSize - 1, finalSize);
	}

}