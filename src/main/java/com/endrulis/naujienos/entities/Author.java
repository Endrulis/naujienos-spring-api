package com.endrulis.naujienos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Author's name cannot be null")
	@NotBlank(message = "Author's name is mandatory")
	@Size(min = 2, max = 50, message = "Author's name must be between 2 and 50 characters")
	private String name;
	@NotNull(message = "Author's suname cannot be null")
	@NotBlank(message = "Author's surname is mandatory")
	@Size(min = 2, max = 50, message = "Author's surname must be between 2 and 50 characters")
	private String surname;

	public Author() {

	}

	public Author(String name, String surname) {
		if (name == null || name.trim().isEmpty())
			throw new IllegalArgumentException("Author's name cannot be null or empty");
		if (surname == null || surname.trim().isEmpty())
			throw new IllegalArgumentException("Author's surname cannot be null or empty");
		this.name = name;
		this.surname = surname;
	}

	public Author(Long id, String name, String surname) {
		if (id == null || id <= 0)
			throw new IllegalArgumentException("Id must be greater than zero");
		if (name == null || name.trim().isEmpty())
			throw new IllegalArgumentException("Author's name cannot be null or empty");
		if (surname == null || surname.trim().isEmpty())
			throw new IllegalArgumentException("Author's surname cannot be null or empty");
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
