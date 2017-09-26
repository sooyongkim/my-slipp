package net.slipp.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="userId", nullable=false, length=20)
	private String userId;
	@Column(name="name",length=20)
	private String name;
	@Column(name="password",length=20)
	private String password;
	@Column(name="email",length=20)
	private String email;
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password=" + password + ", email=" + email + "]";
	}
	public void update(User updatedUser) {
		
		this.userId = updatedUser.userId;
		this.password = updatedUser.password;
		this.name = updatedUser.name;
		this.email = updatedUser.email;
	}
	
	
}
