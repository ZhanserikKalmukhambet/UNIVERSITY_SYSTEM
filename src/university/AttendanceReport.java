package university;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;

import pairs.Pair2;
public class AttendanceReport implements Serializable{
	private static final long serialVersionUID = 1L;

	private Vector<Pair2> attendance = new Vector<Pair2>();
	private LocalTime closeTime;

	public AttendanceReport() {};
	public AttendanceReport(int minute){
		closeTime = LocalTime.now().plusMinutes(minute);
	}

	public Vector<Pair2> getAttendance(){
		return attendance;
	}
	public LocalTime getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(int minute) {
		this.closeTime = LocalTime.now().plusMinutes(minute);
	}
	@Override
	public String toString() {
		String res = "";

		for(Pair2 i : attendance) {
			res += i.getStudent().getName() + " " + i.getStudent().getSurname() + " - " + i.p + "\n";
		}

		return res;
	}
}