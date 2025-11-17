package com.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
   
	
   
   public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
   public String getUsername() {
	return username;
   }
   public void setUsername(String username) {
	this.username = username;
   }
   public String getPassword() {
	return password;
   }
   public void setPassword(String password) {
	this.password = password;
   }
   @Override
   public String toString() {
	return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
   }
   @Override
   public int hashCode() {
	return Objects.hash(id, password, username);
   }
   @Override
   public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Admin other = (Admin) obj;
	return Objects.equals(id, other.id) && Objects.equals(password, other.password)
			&& Objects.equals(username, other.username);
   }
   
   
}

