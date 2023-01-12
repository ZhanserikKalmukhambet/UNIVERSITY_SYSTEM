package library;


import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import university.*;

public class Librarian extends Employee implements Comparable<Object>{
	private HashMap<Book, Integer> books = new HashMap<Book, Integer>();
    private static double fine;	
    private static final long serialVersionUID = 1L;
    
    public Librarian() {
    	super();
    }
    public Librarian(String name, String surname) {
    	super(name, surname);
    }
    public Librarian(String name, String surname, double workExperience, int salary, LocalDate hireDate) {
    	super(name, surname, workExperience, salary, hireDate);
    }
    
    public Book getBook(String title) {

    	if (this.getBookTitles().contains(title)) {
    		for (Book b : this.books.keySet()) {
        		if (b.getTitle().equalsIgnoreCase(title)) return b;
        	}
    	}
    	return null;
    }
    
    public boolean lendBook(CanBorrowBook borrower, Book book, Date borrowDate) {
    	if (this.books.get(book) == 0) return false;
    	LibraryRecord rec = new LibraryRecord(borrower, book, borrowDate);
    	this.books.replace(book, this.books.get(book)-1);
        return RecordBook.getInstance().addRecord(rec);
    }
    
    public List<String> getBookTitles(){
    	Vector<String> bookTitles = new Vector<String>();

   	   	 for (Book book : this.books.keySet()) {
   	   		 bookTitles.add(book.getTitle());
   	   	 }
   	   	 return bookTitles;
    }
     
    public boolean getBookBack(LibraryRecord rec, Date returnDate) {
    	Date borrowDate = rec.getBorrowDate();
    	long diffInMillies = Math.abs(returnDate.getTime() - borrowDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    if (diff > 180) {
	    	
	    	if (rec.getBorrower() instanceof Student) {
	    		Student s = (Student) rec.getBorrower();
	    		s.setFee(s.getFee()+fine);
	    	}
	    	else if (rec.getBorrower() instanceof Teacher) {
	    		Teacher t = (Teacher) rec.getBorrower();
	    		t.setFee(t.getFee()+fine);
	    	}
	    }
//	    	Admin.getInstance().addFeeToStudent(rec.getBorrower(), count);
	    
        return RecordBook.getInstance().removeRecord(rec);
    }
    
    public void printBookBorrowers(Book b) {
    	RecordBook.getInstance().printBookBorrowers(b);
    }
    
    public boolean getBookBack(CanBorrowBook borrower, Book book, Date borrowDate, Date returnDate) {
    	LibraryRecord rec = new LibraryRecord(borrower, book, borrowDate);
    	this.books.replace(book, this.books.get(book)+1);
    	return this.getBookBack(rec, returnDate);
//	    	Admin.getInstance().addFeeToStudent(rec.getBorrower(), count);
    }
    
    public boolean getBookBack(Book book, CanBorrowBook borrower, Date borrowDate, Date returnDate) {
    	return this.getBookBack(borrower, book, borrowDate, returnDate);
    }
    
    public boolean getBookBack(Book book, CanBorrowBook borrower, Date returnDate) {
    	if (!RecordBook.getInstance().getBookBorrowers(book).contains(borrower)) return false;
    	Date borrowDate = RecordBook.getInstance().getBorrowDate(borrower, book);
    	if (borrowDate != null) {
    		return this.getBookBack(book, borrower, borrowDate, returnDate);
    	}
		return false;
    }

	@Override
	public int compareTo(Object o) {
		Librarian l = (Librarian) o;
		return this.getName().compareTo(l.getName());
	}
    
	public boolean equals(Object o) {
		if (!super.equals(o)) return false;
		Librarian l = (Librarian) o;
		return this.books.equals(l.books);
	}
	
	public int hashCode() {
		return Objects.hash(books);
	}
	
	public HashMap<Book, Integer> getBooks() {
		return books;
	}
	public void setBooks(HashMap<Book, Integer> books) {
		this.books = books;
	}
	
	public boolean addBook(Book b, int quantity) {
		if (this.books.containsKey(b)) {
			return this.books.replace(b, books.get(b)+quantity) != null;
		}

		return this.books.put(b, quantity) == null;
	}
	
	public void printBooks() {
		int ind = 1;
		for (HashMap.Entry<Book, Integer> set : this.getBooks().entrySet()) {
			System.out.print(ind++ +". ");
			System.out.println(set.getKey()+", quantity: "+set.getValue());
		}

	}
	@SuppressWarnings("unchecked")
	public Librarian clone() throws CloneNotSupportedException {
		Librarian clone = (Librarian)super.clone();
		clone.books = (HashMap<Book, Integer>)books.clone();
		return clone;
	}
    
}
