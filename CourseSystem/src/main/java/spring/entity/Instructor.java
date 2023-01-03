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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Instructor")
public class Instructor {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "instructor_id", nullable = false)
	private Long instructorId;
	
	@Basic
	@Column (name = "name", nullable = false, length = 45)
	private String firstName;
	
	@Basic	
	@Column (name = "lastName", nullable = false, length = 45)
	private String lastName;
	
	@Basic
	@Column (name = "summary", nullable = false, length = 45)
	private String instructorSummary;
	
	@OneToMany (mappedBy = "instructor", fetch = FetchType.LAZY)
	private Set <Course> courses = new HashSet<>();
	
	@OneToOne (cascade = CascadeType.REMOVE)
	@JoinColumn (name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;
	
	public Instructor () {}
	
	public Instructor(String instructorFirstName, String instructorLastName, String instructorSummary, User user) {
		this.firstName = instructorFirstName;
		this.lastName = instructorLastName;
		this.instructorSummary = instructorSummary;
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(instructorId, firstName, lastName, instructorSummary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Instructor instructor = (Instructor) obj;
		return instructorId.equals(instructor.instructorId) && Objects.equals(firstName, instructor.firstName) && Objects.equals(lastName, instructor.lastName) && Objects.equals(instructorSummary, instructor.instructorSummary);
	}

	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}

	public String getInstructorFirstName() {
		return firstName;
	}

	public void setInstructorFirstName(String instructorFirstName) {
		this.firstName = instructorFirstName;
	}

	public String getInstructorLastName() {
		return lastName;
	}

	public void setInstructorLastName(String instructorLastName) {
		this.lastName = instructorLastName;
	}

	public String getInstructorSummary() {
		return instructorSummary;
	}

	public void setInstructorSummary(String instructorSummary) {
		this.instructorSummary = instructorSummary;
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
		return "Instructor [instructorId=" + instructorId + ", instructorFirstName=" + firstName
				+ ", instructorLastName=" + lastName + ", instructorSummary=" + instructorSummary + "]";
	}
	
}
