package com.endrulis.naujienos.services;

import java.util.List;

import javax.naming.Binding;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.endrulis.naujienos.exceptions.ArticleNotFoundException;
import com.endrulis.naujienos.exceptions.InvalidArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endrulis.naujienos.entities.Author;
import com.endrulis.naujienos.entities.Article;
import com.endrulis.naujienos.repositories.AuthorRepository;
import com.endrulis.naujienos.repositories.ArticleRepository;
import org.springframework.validation.BindingResult;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository articleRep;

	@Autowired
	AuthorRepository authorRep;

	public List<Article> findAll() {
		try {
			return articleRep.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Error retrieving articles from the database: " + e.getMessage());
		}

	}

	public void update( @Valid Article oldArticle, @Valid Article updated) {
		oldArticle.setName(updated.getName());
		oldArticle.setText(updated.getText());
		oldArticle.setAuthor(updated.getAuthor());
	}

	private Author getOrCreateAuthor(@Valid Article article) {
		Author author = authorRep.findByNameAndSurname(article.getAuthor().getName(), article.getAuthor().getSurname());
		if (author == null) {
			author = new Author(article.getAuthor().getName(), article.getAuthor().getSurname());
		}
		return author;
	}

	public void updateById(@Min(1) @NotNull @Positive Long id, @Valid Article updated) {
		articleRep.findById(id).map(article -> {
			article.setName(updated.getName());
			article.setText(updated.getText());
			Author author = getOrCreateAuthor(updated);
			article.setAuthor(author);
			return articleRep.save(article);
		}).orElseThrow(() -> new ArticleNotFoundException("Article not found with ID: " + id));
	}

	public Article findById(@Min(1) @Positive Long id) {
		return articleRep.findById(id).orElseThrow(() -> new ArticleNotFoundException("Article not found with ID: " + id));
	}

	public void save(@Valid Article article, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidArticleException("Invalid article data", result);
		}
		Author author = getOrCreateAuthor(article);
		article.setAuthor(author);
		articleRep.save(article);
	}

	public void deleteById(@NotNull @Positive Long id) {
		Article article = articleRep.findById(id)
				.orElseThrow(() -> new ArticleNotFoundException("Article not found with ID: " + id));
		Author author = article.getAuthor();
		articleRep.deleteById(id);
		if (authorRep.countArticlesByAuthor(author) == 0)
			authorRep.delete(author);
	}

	public Article findByName(@NotBlank String name) {
		return articleRep.findByName(name);
	}

}
