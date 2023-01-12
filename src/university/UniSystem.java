package university;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import library.Librarian;

public final class UniSystem implements Serializable{

	private static final long serialVersionUID = 1L;

	private static UniSystem DATABASE = new UniSystem();
	
	static {
		if(new File("data").exists()) {
			try {
				DATABASE = read();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else DATABASE = new UniSystem();
	}
	
	private UniSystem() {
		
	}

	public static UniSystem getDatabase() {
		return DATABASE;
	}

	private Vector<User> users = new Vector<User>();
	private Vector<Faculty> faculties = new Vector<Faculty>();
	private Vector<News> news = new Vector<News>();
	private Vector<Room> rooms = new Vector<Room>();
	private Vector<Request> requests = new Vector<Request>();
	private Vector<Organization> organizations = new Vector<Organization>();
	private Vector<Librarian> librarians = new Vector<Librarian>();

	public Vector<Librarian> getLibrarian() {
		return this.librarians;
	}

	public List<Student> getStudents() {
		return this.users.stream().filter(u->u instanceof Student).map(u->(Student)u).collect(Collectors.toList());
	}

	public List<Employee> getEmployees() {
		return this.users.stream().filter(u->u instanceof Employee).map(u->(Employee)u).collect(Collectors.toList());
	}

	public List<Manager> getManagers(){
		return this.getEmployees().stream().filter(u->u instanceof Manager).map(u->(Manager)u).collect(Collectors.toList());
	}

	public List<Teacher> getTeachers(){
		return this.getEmployees().stream().filter(u->u instanceof Teacher).map(u->(Teacher)u).collect(Collectors.toList());
	}

	public Vector<Faculty> getFaculties() {
		return this.faculties;
	}
	public Vector<Librarian> getLibrarians() {
		return librarians;
	}

	public void setLibrarians(Vector<Librarian> librarians) {
		this.librarians = librarians;
	}
	
	public Vector<Course> getCourses() {
		Vector<Course> courses = new Vector<Course>();
		for(Faculty f : faculties) {
			courses.addAll(f.getCourses());
		}
		return courses;
	}

	public Vector<News> getNews() {
		return this.news;
	}

	public Vector<Room> getRooms() {
		return this.rooms;
	}

	public Vector<Request> getRequests() {
		return this.requests;
	}

	public Vector<Organization> getOrganizations() {
		return this.organizations;
	} 
	
	public List<User> getCanBorrowBook() { 
	    return this.users.stream().filter(u->u instanceof CanBorrowBook).collect(Collectors.toList()); 
	  }

	public static void write() throws FileNotFoundException, IOException {
		FileOutputStream fos = 	new FileOutputStream("data");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(DATABASE);
		fos.close();
		oos.close();
	}

	public static UniSystem read() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("data");
		ObjectInputStream ois = new ObjectInputStream(fis);

		DATABASE = (UniSystem) ois.readObject();
		
		ois.close();
		fis.close();
		
		return DATABASE;
	}

	public Vector<User> getUsers() {
		return this.users;
	}
}
