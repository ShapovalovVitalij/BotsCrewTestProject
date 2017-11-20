package vitalii.shapovalov.library;


import vitalii.shapovalov.library.service.BookService;

/**
 * 
 * @author Vitalii Shapovalov
 * 
 * This is BotsCrewTestProject
 *
 */



public class Main {
	

	private static BookService bookService = new BookService();
	
	public static void main(String[] args) {
		welcome();
		while(true){
		listOfServices();
		}
	}
	
	private static void welcome(){
		System.out.println("Welcome to The Library." + "\n" +
	      "The Library is dedicated to providing all citizens"+ "\n" +
		  "with different book services." + "\n" +
	      "Here's a list of the most frequently demanded services:"
	      );
	}

	private static void listOfServices(){
		System.out.println("\n1. Add book" + "\n"+
	      "2. Remove book" + "\n"+
		  "3. Edit book" + "\n" +
	      "4. Show all books." + "\n" + 
		  "5. Exit" + "\n" +
		  "Please choose one that best suits your needs:" + "\n" + 
	      "(Type in index of the service)");
		int choice = 0;
		try {
			 choice = Integer.parseInt(bookService.readLine());
		} catch (NumberFormatException e) {
			System.out.println("You have entered not a number. Please," + "\n" +
		    "try again.");
		}
		
		switch (choice){
		case 1:  
		  bookService.addBook();
		  break;
		case 2: 
		  bookService.removeBook();
		  break;
		case 3:
		  bookService.editBook();
		  break;
		case 4:
		  bookService.showAll();
		  break;
		case 5:
			System.out.println("Goodbye");
			System.exit(1);
		default:
				System.out.println("The number is to large");
		}
	}
	

	
	
}
