package spring.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Simple POJO class, that represent entity "User"
 * @author VLadislav K
 */
@Entity
@Table (name = "users")
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "user_id", nullable = false)
	private Long userId;
	
	@Basic
	@Column (name = "email", nullable = false, length = 96, unique = true)
	private String userEmail;
	
	@Basic
	@Column (name = "password", nullable = false, length = 96)
	private String userPassword;
	
	@ManyToMany (fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinTable (name = "user_role", 
	                               joinColumns = {@JoinColumn (name = "user_id")}, 
	                               inverseJoinColumns = {@JoinColumn (name = "role_id")})
	private Set <Role> roles = new HashSet<>();
	
	
	@OneToOne (mappedBy = "user")
	private Student student;
	
	@OneToOne (mappedBy = "user")
	private Instructor instructor;
	
	public User () {}
	
	public User(String userEmail, String userPassword) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userEmail, userPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		User user = (User) obj;
		return Objects.equals(userId, user.userId) && Objects.equals(userEmail, user.userEmail) && Objects.equals(userPassword, user.userPassword);
	}
	
	// add role in set 
	// get user in set role
	public void assignRoleToUser (Role role) {
		this.roles.add(role);
		role.getUsers().add(this);
		
	}
	
	// remove role from set
	// remove users from role
	public void removeRole (Role role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail + "]";
	}
	
	


}
