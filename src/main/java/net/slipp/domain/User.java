package net.slipp.domain;


import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

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
	@Column(name="id", unique=true)
	private Long id;
	
	@Column(name="userId", nullable=false, length=20)
	private String userId;
	@Column(name="name",length=20)
	private String name;
	@Column(name="password",length=20)
	private String password;
	@Column(name="email",length=20)
	private String email;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public boolean matchId(Long newId) {

		if(newId==null){
			return false;
		}
		return newId.equals(id);
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean matchPassword(String newPassword) {
		
		if(password==null){
			return false;
		}
		return newPassword.equals(password);
	}
	
	public String getPassword() {
		return password;
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
