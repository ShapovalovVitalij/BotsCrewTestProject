package vitalii.shapovalov.library.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.mysql.jdbc.StringUtils;

import vitalii.shapovalov.library.dao.BookDao;
import vitalii.shapovalov.library.entity.Book;

public class BookService {
	
	private BufferedReader bf  = new BufferedReader(new InputStreamReader(System.in));
	private BookDao bookDao = new BookDao();
	
	
	public void addBook(){		
			Book book = new Book();
			book.setName(getName("Please, enter book title:"));
			book.setAuthor(getName("and book author name:"));
			bookDao.addBook(book);
			
			System.out.println("Book " + book.getAuthor()
			+ " " + book.getName() + " was added");
	}
	
	public void removeBook(){
		List<Book> list = bookDao.findByName(getName("Please, enter book title:"));
		
		if(list.isEmpty()){
		  System.out.println("Sorry, we have not a book with such name");
		  
		}else if(list.size() > 1){
		  System.out.println("We​ ​have​ ​few​ ​books​ ​with​ ​such​ ​name​.​" + "\n" +
		      "Please​ ​choose​ ​one​ ​by​ ​typing​ ​a​ ​number​ ​of book:");
		  
		  list.forEach(e -> System.out.println(list.indexOf(e) + ". " + e));
		  int index = Integer.parseInt(readLine());
		  bookDao.delete(list.get(index));
		
		}else{
			bookDao.delete(list.get(0));
			
		}
	}
	
	public void editBook(){
		List<Book> list = bookDao.findByName(getName("Please, enter book title:"));
		if(list.isEmpty()){
			  System.out.println("Sorry, we have not a book with such name");
			  return;
		}
		Book book=list.get(0);
		if(list.size() > 1){
			  System.out.println("We​ ​have​ ​few​ ​books​ ​with​ ​such​ ​name​.​" + "\n" +
			      "Please​ ​choose​ ​one​ ​by​ ​typing​ ​a​ ​number​ ​of book:");
			  
			  list.forEach(e -> System.out.println(list.indexOf(e) + ". " + e));
			  int index = Integer.parseInt(readLine());
			  book=list.get(index);
		}
		book.setName(getName("Please, enter new name for the book"));
		bookDao.update(book);
	}
	
	public void showAll(){
		List<Book> list = bookDao.getAll();
		list.sort((b1,b2) -> b1.getName().compareTo(b2.getName()));
		list.forEach( e -> System.out.println(list.indexOf(e) + ". " + e));
	}
	
	
	private String getName(String message){
		System.out.println(message);
		String name = readLine();
		if(StringUtils.isEmptyOrWhitespaceOnly(name)){
			System.out.println("Sorry, but this string can't be empty or whitespace. Try again");
			getName(message);
		}
		return name;
	}
	
	public String readLine(){
		String line="";
		try{
			line = bf.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return line;
	}
	
	
}
