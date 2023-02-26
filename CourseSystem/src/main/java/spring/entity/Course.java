package spring.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Simple POJO class, that represent entity "Course"
 * @author VLadislav K
 */
 
@Entity
@Table (name = "courses")
public class Course {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "course_id", nullable=false)
	private Long courseId;
	
	@Basic 
	@Column (name = "name", nullable=false, length = 45)
	private String courseName;
	@Basic
	
	@Column (name = "duration", nullable=false, length = 45)
	private String courseDuration;
	
	@Basic
	@Column (name = "description", nullable=false, length = 45)
	private String courseDescription;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "instructor_id", referencedColumnName = "instructor_id", nullable = false)
	private Instructor instructor;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable (name = "enrolled_in", 
	                                 joinColumns = {@JoinColumn (name = "course_id")}, 
	                                 inverseJoinColumns = {@JoinColumn (name = "student_id")})
	private Set<Student> listStudent = new HashSet<>();

	@Override
	public int hashCode() {
		return Objects.hash(courseId, courseName, courseDuration, courseDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Course course = (Course) obj;
		return courseId.equals(course.courseId) && Objects.equals(courseName, course.courseName) && Objects.equals(courseDuration, course.courseDuration) && Objects.equals(courseDescription, course.courseDescription);
	}

	// add object student to the list students;
	// add course to the List of Students;
	public void assignStudentToCourse (Student student) {
		this.listStudent.add(student);
		student.getCourses().add(this);

	}
	
	// remove student from course
	
	public void removeStudents (Student students) {
		this.listStudent.remove(students);
		students.getCourses().remove(this);
	}
	

	public Course () {}
	
	public Course(String courseName, String courseDuration, String courseDescription, Instructor instructor) {
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseDescription = courseDescription;
		this.instructor = instructor;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Set<Student> getListStudent() {
		return listStudent;
	}

	public void setListStudent(Set<Student> listStudent) {
		this.listStudent = listStudent;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDuration=" + courseDuration
				+ ", courseDescription=" + courseDescription + "]";
	}
	
	
	
	
}

	
	


