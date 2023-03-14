package com.endrulis.naujienos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.endrulis.naujienos.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	Article findByName(String name);
}
