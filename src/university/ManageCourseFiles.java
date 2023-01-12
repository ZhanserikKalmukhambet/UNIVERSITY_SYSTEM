package university;

public interface ManageCourseFiles {
	boolean uploadFile(File file, Course course);
	boolean removeFile(File file, Course course);
}	
