package menu; 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Date;

import library.*; 
import university.*; 

public class LibrarianMenu extends Menu{ 
	private static final long serialVersionUID = 1L;

	public LibrarianMenu() { 
		super(); 
	} 

	public LibrarianMenu(Librarian user) { 
		super(user); 
	} 

	@Override 
	public void displayMenu() { 
		System.out.println("***********************Desktop***********************"); 
		System.out.println("====================================================="); 
		System.out.println("           1.Lend Book                               "); 
		System.out.println("           2.Get Book Back                           "); 
		System.out.println("           3.Add Book                                "); 
		System.out.println("           4.Personal info                           "); 
		System.out.println("           5.News                                    "); 
		System.out.println("           6.View Active Logs                        "); 
		System.out.println("           7.View Books                              "); 
		System.out.println("           8.View Book Debts                         "); 
		System.out.println("           9.View Book Borrowers                     "); 
		System.out.println("           0. Exit                                  "); 
		System.out.println("====================================================="); 
	} 

	BufferedReader inp = new BufferedReader(new InputStreamReader(System.in)); 

	private void viewPersonalInfo() throws Exception {
		System.out.println(((Librarian)user).toString());
	}

	public CanBorrowBook getBorrower() throws FileNotFoundException, IOException { 
		int ind = 1; 
		CanBorrowBook borrower = null;
		System.out.println("Enter book borrower : "); 
		for(User u : UniSystem.getDatabase().getCanBorrowBook()) { 
			System.out.println(ind++ + ". " + u.getName() + " " + u.getSurname()); 
		} 
		boolean inputOk = false; 
		while (!inputOk) {
			int borrowerId;
			try {
				borrowerId = Integer.parseInt(inp.readLine());
				borrower = (CanBorrowBook) UniSystem.getDatabase().getCanBorrowBook().get(borrowerId-1); 
				inputOk = true;
			} catch (NumberFormatException | IOException e) {
				System.out.println("Something went wrong... Please try again."); 
			} 
		}
		return borrower; 
	} 

	public Book getBookByTitle(){ 
		boolean inputOk = false; 
		Book bookFound = null;
		while (!inputOk) {
			System.out.println("Enter book title : "); 
			String bookTitle;
			try {
				bookTitle = inp.readLine();
				bookFound = ((Librarian)user).getBook(bookTitle); 	
				inputOk = true;
			} catch (IOException e) {
				System.out.println("Something went wrong... Please try again."); 
			}
		}

		return bookFound; 
	}

	public Book getBookByTitle(String bookTitle){ 
		Book bookFound = new Book();
		bookFound = ((Librarian)user).getBook(bookTitle); 
		return bookFound; 
	}

	public Date getDate(String message) { 
		Date date = null; 
		boolean inputOk = false; 
		while (!inputOk) { 
			String dateStr;
			try {
				System.out.println(message);
				dateStr = inp.readLine();
				try { 
					date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(dateStr); 
					inputOk = true; 
					return date; 
				} catch (ParseException e) { 
					System.out.println("Invalid date format! Please follow the format: dd/MM/yyyy"); 
				}   
			} catch (IOException e1) {
				System.out.println("Something went wrong... Please try again."); 
			} 
		} 
		return date; 
	} 

	public void lendBook() throws FileNotFoundException, IOException{ 
		Book bookFound = getBookByTitle();
		if (bookFound == null) {
			lendBook();
			return;
		}
		if (((Librarian)user).getBooks().get(bookFound) == 0) {
			System.out.println("Sorry, the book is out of stock!");
			return;
		}
		CanBorrowBook borrower = getBorrower(); 
		Date borrowDate = getDate("Enter borrow date : dd/mm/yyyy"); 
		//		LibraryRecord libRec = new LibraryRecord(borrower, bookFound, borrowDate); 
		//		if (RecordBook.getInstance().addRecord(libRec)) { 
		//			System.out.println("New log has successfully been added."); 
		//		} else { 
		//			System.out.println("Error adding a new log!"); 
		//		} 
		if (((Librarian)user).lendBook(borrower, bookFound, borrowDate)) {
			System.out.println("New log has successfully been added."); 
		} else {
			System.out.println("Error adding a new log!"); 
		}
	}  

	public String getBookTitle() {
		String bookTitle = null;
		boolean inputOk = false;
		while (!inputOk) {
			System.out.println("Enter book title : "); 
			try {
				bookTitle = inp.readLine();
				inputOk = true;
			} catch (IOException e) {
				System.out.println("Something went wrong... Please try again."); 
			}
		}
		return bookTitle;
	}

	public String getAuthor() {
		String author = null;
		boolean inputOk = false;
		while (!inputOk) {
			System.out.println("Enter book author : "); 
			try {
				author = inp.readLine();
				inputOk = true;
			} catch (IOException e) {
				System.out.println("Something went wrong... Please try again."); 
			}
		}
		return author;
	}

	public int getBookQuantity() {
		int quantity = 0;
		boolean inputOk = false;
		while (!inputOk) {
			System.out.println("Enter book quantity : "); 
			try {
				quantity = Integer.parseInt(inp.readLine());
				inputOk = true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid number format! Please try again."); 
			} catch (IOException e) {
				System.out.println("Something went wrong... Please try again."); 
			} 
		}
		return quantity;
	}

	public void addBook() { 
		String title = getBookTitle();
		String author = getAuthor();
		int quantity = getBookQuantity();
		Book b = new Book(title, author);
		if (((Librarian) user).addBook(b, quantity)){
			System.out.println("Book successfully added.");
		} else {
			System.out.println("Book not added!");
		}
	}

	public void viewBooks() {
		((Librarian) user).printBooks();
	}

	public void viewBookDebts() { 
		Date dueDate = getDate("Enter due date : dd/mm/yyyy"); 
		RecordBook.getInstance().printBookDebtors(dueDate); 
	} 

	public void viewBookBorrowers() { 
		Book borrowedBook = getBookByTitle(); 
		if (borrowedBook != null) { 
			RecordBook.getInstance().printBookBorrowers(borrowedBook); 
		}
	}

	public void printAllLogs() { 
		//		if (!RecordBook.getInstance().getRecordBook().isEmpty()) { 
		RecordBook.getInstance().printAllLogs(); 
		//		}
	} 

	public void getBookBack() throws FileNotFoundException, IOException {
		Book b = this.getBookByTitle();
		CanBorrowBook borrower = this.getBorrower();
		Date returnDate = this.getDate("Enter return date : dd/mm/yyyy");
		((Librarian) user).getBookBack(b, borrower, returnDate);
	}

	public void viewNews() throws FileNotFoundException, IOException {
		if(UniSystem.getDatabase().getNews().isEmpty()) {
			System.out.println("No views yet !");
		}
		else {
			int ind = 1;
			for(News news : UniSystem.getDatabase().getNews()) {
				System.out.println(ind++ + ". " + news);
			}
		}
	}

	@SuppressWarnings("unused")
	@Override 
	public void action() throws Exception{ 
		menu : while (true) { 
			displayMenu(); 
			System.out.print("Enter Your Choice:\n"); 
			int ch = 0;
			boolean inputOk = false;
			while (!inputOk) {
				try {
					ch = Integer.parseInt(inp.readLine());
					inputOk = true;
				} catch (NumberFormatException e) {
					System.out.println("Invalid number format! Please try again.");
				} catch (IOException e) {
					System.out.println("Error occured! Please try again.");
				} 
			}

			if (ch == 1) { 
				lendBook : while(true) {
					((Librarian)user).printBooks();
					lendBook(); 
					System.out.println("\n 1. Lend another book \n 2. Return back \n 3. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue lendBook;
					if(ch==2) continue menu;
					if(ch==3) {exit(); break menu;}
					break;
				}
			}
			if (ch == 2) { 
				getBookBack : while(true) {
					getBookBack();
					System.out.println("\n 1. Get another book back \n 2. Return back \n 3. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue getBookBack;
					if(ch==2) continue menu;
					if(ch==3) {exit(); break menu;}
					break;
				}
			}
			if (ch == 3) {
				addBook : while(true) {
					addBook(); 
					System.out.println("\n 1. Add another book\n 2. Return back \n 3. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue addBook;
					if(ch==2) continue menu;
					if(ch==3) {exit(); break menu;}
					break;
				}
			}
			if (ch == 4) {
				while(true) {
					this.viewPersonalInfo();
					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) {exit(); break menu;}
					break;
				}
			}

			if (ch == 5) {
				viewNews : while(true) {
					viewNews();

					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) { exit(); break menu;}

					break;
				}
			}

			if (ch == 6) { 
				viewActiveLogs : while(true) {
					printAllLogs(); 

					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) {exit(); break menu;}
					break;
				}
			}

			if (ch == 7) { 
				viewBooks : while(true) {
					viewBooks(); 
					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) {exit(); break menu;}
					break;
				}
			}

			if (ch == 8) { 
				viewBookDebts : while(true) {
					viewBookDebts(); 
					System.out.println("\n 1. View book debts for another date\n 2. Return back \n 3. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue viewBookDebts;
					if(ch==2) continue menu;
					if(ch==3) {exit(); break menu;}
					break;
				}
			}

			if (ch == 9) { 
				viewBookBorrowers : while(true) {
					viewBooks(); 
					System.out.println("\n 1. View borrowers list for another book\n 2. Return back \n 3. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue viewBookBorrowers;
					if(ch==2) continue menu;
					if(ch==3) {exit(); break menu;}
					break;
				}
			} 
			
			if(ch == 0) {
				exit();
			}
		} 
	}
}


