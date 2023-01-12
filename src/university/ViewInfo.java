package university;

import exceptions.StudentNotFoundException;
import exceptions.TeacherNotFoundException;

public interface ViewInfo {
	String viewStudentInfo(Student student) throws StudentNotFoundException;
	String viewTeacherInfo(Teacher teacher) throws TeacherNotFoundException;	
}