package com.example.backend.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Role")
@Getter
@Setter
@AllArgsConstructor
public class Role {
	
	@Id
	@Column(name="Role_Id", nullable=false)
	private int roleId;
	@Column(name="Role_Name", nullable=false)
	private String  roleName;
	
	@ManyToMany(mappedBy = "role")
	Set<User> user = new HashSet<>();
	
	public Role(){}

}
