package university;

import java.util.*;

import enums.Language;
import enums.RequestForm;
import enums.RequestStatus;

import java.io.Serializable;
import java.time.*;
public abstract class Request implements Serializable, Cloneable, Comparable<Object>{
    private static final long serialVersionUID = 1L;
    
	private RequestStatus Status;
    private LocalDate date;
    private Language language;
    private RequestForm form;
    
    public Request() {
    	
    }
	public Request(Language language, RequestForm form) {
		Status = RequestStatus.PROCESSING;
		this.date = LocalDate.now();
		this.language = language;
		this.form = form;
	}
	
	public RequestStatus getStatus() {
		return Status;
	}
	public void setStatus(RequestStatus status) {
		Status = status;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public RequestForm getForm() {
		return form;
	}
	public void setForm(RequestForm form) {
		this.form = form;
	}

	@Override
	public String toString() {
		return "Request [Status=" + Status + ", date=" + date + ", language=" + language + ", form=" + form + "]";
	}  
}
