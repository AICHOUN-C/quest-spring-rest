package com.questspringrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.questspringrest.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByTitleContainingOrDescriptionContaining(String searchTerm, String searchTerm2);
}
