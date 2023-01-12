package university;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable{
    private static final long serialVersionUID = 1L;
    
	public int number;
    public int capacity;
    
    public Room() {
    	
    }
    public Room(int number) {
    	this.number = number;
    }
    public Room(int number, int capacity) {
    	this(number);
    	this.capacity = capacity;
    }
    
	@Override
	public int hashCode() {
		return Objects.hash(capacity, number);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Room other = (Room) obj;
		return capacity == other.capacity && number == other.number;
	}
	
	@Override
	public String toString() {
		return "Room [number=" + number + ", capacity=" + capacity + "]";
	}
    
    
}
