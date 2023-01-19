package spring.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name ="Students")
public class Student {
	
	@Id
	@GeneratedValue (strategy  = GenerationType.IDENTITY)
	@Column (name = "student_id", nullable = false)
	private Long studentId;
	
	@Basic
	@Column (name = "first_name", nullable = false, length = 45)
	private String firstName;
	
	@Basic
	@Column (name = "last_name", nullable = false, length = 45)
	private String lastName;
	
	@Basic
	@Column (name = "level", nullable = false, length = 45)
	private String level;
	
	@ManyToMany (mappedBy = "listStudent", fetch = FetchType.LAZY)
	private Set <Course> courses = new HashSet<>();
	
	@OneToOne (cascade = CascadeType.REMOVE)
	@JoinColumn (name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;
	
	public Student () {}
	
	public Student(String firstName, String lastName, String level, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.level = level;
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentId, firstName, lastName, level);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Student student = (Student) obj;
		return studentId.equals(student.studentId) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(level, student.level);
	}

	
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentFirstName=" + firstName + ", studentLastName="
				+ lastName + ", studentLevel=" + level + "]";
	}
	
}
