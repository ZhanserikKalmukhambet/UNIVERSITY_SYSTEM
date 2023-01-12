package library;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import university.CanBorrowBook;

public class RecordBook implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<LibraryRecord> recordBook;
	
	private static final RecordBook INSTANCE = new RecordBook();
	
	private RecordBook() {
		this.recordBook = new Vector<LibraryRecord>();
	}
	
	public static RecordBook getInstance() {
		return INSTANCE;
	}
	
	public boolean addRecord(LibraryRecord rec) {
		return this.recordBook.add(rec);
	}
	
	public boolean removeRecord(LibraryRecord rec) {
		return this.recordBook.remove(rec);
	}
	
	public List<Book> getBorrowedBooks(CanBorrowBook borrower){
   	 return this.recordBook.stream().filter(u->u.getBorrower()==borrower).map(u->u.getBook()).collect(Collectors.toList());
		}
	
	public List<CanBorrowBook> getBookBorrowers(Book book){
	   	 return this.recordBook.stream().filter(u->u.getBook()==book).map(u->u.getBorrower()).collect(Collectors.toList());
		}
	
	public List<CanBorrowBook> getBookDebtors(Date date){
		return this.recordBook.stream().filter(u->(TimeUnit.DAYS.convert(Math.abs(date.getTime() - u.getBorrowDate().getTime()),
				TimeUnit.MILLISECONDS)> 180))
				.map(u->u.getBorrower()).collect(Collectors.toList());
	}
	
	public Date getBorrowDate(CanBorrowBook borrower, Book b) {
		Date borrowDate = null;
		for (LibraryRecord libRec : this.recordBook) {
			if (libRec.getBorrower()==borrower && libRec.getBook() == b) {
				return libRec.getBorrowDate();
			}
		}
		return borrowDate;
	}
	
	public void printAllLogs() {
		int id = 1;
		for (LibraryRecord libRec : this.recordBook) {
			System.out.println(id++ +". "+ libRec.toString());
		}
	}
	
	public void printBookDebtors(Date date) {
		for (CanBorrowBook borrower : this.getBookDebtors(date)) {
			System.out.println(borrower);
		};
	}
	
	public void printBookBorrowers(Book b) {
		for (CanBorrowBook borrower : this.getBookBorrowers(b)) {
			System.out.println(borrower);
		};
	}

	public List<LibraryRecord> getRecordBook() {
		return recordBook;
	}

	public void setRecordBook(Vector<LibraryRecord> recordBook) {
		this.recordBook = recordBook;
	}

}
