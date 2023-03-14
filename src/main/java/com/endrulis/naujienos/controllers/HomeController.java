package com.endrulis.naujienos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.endrulis.naujienos.entities.Article;
import com.endrulis.naujienos.services.ArticleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private ArticleService articleService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("articles", articleService.findAll());
		return "index";
	}

	@GetMapping("/article/{id}")
	public String article(@PathVariable("id") Long id, Model model) {
		Article article = articleService.findById(id);
		model.addAttribute("article", article);
		return "article";
	}

	@GetMapping("/article/add")
	public String addForm(Model model) {
		model.addAttribute("article", new Article());
		return "add-article";
	}

	@PostMapping("/addArticle")
	public String add(@Valid @ModelAttribute("article") Article updatedArticle, BindingResult result) {

		if (articleService.findByName(updatedArticle.getName()) != null) {
			result.rejectValue("pavadinimas", "duplicate", "article with this name doesn't exist");
			return "add-article";
		}

		Article article = new Article();
		articleService.update(article, updatedArticle);
		articleService.save(updatedArticle, result);
		return "redirect:/";
	}

	@GetMapping("/article/{id}/edit")
	public String updateForm(@PathVariable Long id, Model model) {
		Article article = articleService.findById(id);
		model.addAttribute("article", article);
		return "update-article";
	}

	@PostMapping("/article/{id}")
	public String update( @PathVariable Long id, @Valid @ModelAttribute("article") Article updated, BindingResult result) {
		articleService.updateById(id, updated);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		articleService.deleteById(id);
		return "redirect:/";
	}
}
