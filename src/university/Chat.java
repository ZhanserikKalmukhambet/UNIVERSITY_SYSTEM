package university;

import java.io.Serializable;
import java.util.*;


public class Chat implements Serializable{
    private static final long serialVersionUID = 1L;
    
	private Employee receiver;
    private Vector<Message> messages;
    private Message firstMessage;
    
    public Chat() {
    	
    }
    public Chat(Employee receiver, Message first) {
    	this.receiver = receiver;
    	this.setMessage(firstMessage);
    }
    
    public Employee getReceiver() {
        return this.receiver;
    }
    public void setReceiver(Employee receiver) {
        this.receiver = receiver;
    }
    
    public Message getMessage() {
		return firstMessage;
	}
	public void setMessage(Message message) {
		this.firstMessage = message;
		messages.add(message);
	}
    
    public void writeMessage(Message messsage) {
    	messages.add(messsage);
    }
    
    public boolean equals(Object o) {
    	if(o == null)
    		return false;
    	if(this == o)
    		return true;
    	if(this.getClass() != o.getClass())
    		return false;
    	
    	Chat other = (Chat) o;
    	return Objects.equals(this.receiver, other.receiver);
    }
}
