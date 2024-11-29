package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}