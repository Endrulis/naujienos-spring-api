package com.endrulis.naujienos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.endrulis.naujienos.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByNameAndSurname(String name, String surname);

	@Query("SELECT COUNT(a) FROM Article a WHERE a.author = :author")
	long countArticlesByAuthor(@Param("author") Author author);
}
