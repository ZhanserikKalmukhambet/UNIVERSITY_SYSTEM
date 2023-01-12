package library;



import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import university.CanBorrowBook;
public class LibraryRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private CanBorrowBook borrower;
    private Date borrowDate;
    private Book book;
    private Date returnDate;
    
    public LibraryRecord () {
    }
    
    public LibraryRecord(CanBorrowBook borrower, Book book, Date date) {
    	this.borrower = borrower;
    	this.book = book;
    	this.borrowDate = date;
    }
    public LibraryRecord(Book book, CanBorrowBook borrower, Date borrowDate) {
    	this(borrower, book, borrowDate);
    }
    
    public CanBorrowBook getBorrower() {
        return this.borrower;
    }
    public void setBorrower(CanBorrowBook borrower) {
        this.borrower = borrower;
    }
    public Date getBorrowDate() {
        return this.borrowDate;
    }
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
    public Book getBook() {
        return this.book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public Date getReturnDate() {
        return this.returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    public String toString() {
    	return "Record of borrow:\n " + borrower + " ,\nbook: "+book.toString() + ", borrowed on " + borrowDate.toString();
    }

	@Override
	public int hashCode() {
		return Objects.hash(book, borrower);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryRecord other = (LibraryRecord) obj;
		return Objects.equals(book, other.book) && Objects.equals(borrower, other.borrower);
	}
    
    
}
