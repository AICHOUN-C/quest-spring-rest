package com.questspringrest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.questspringrest.entity.Book;
import com.questspringrest.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookDao;
	
	@GetMapping("/books")
    public List<Book> index(){
        return bookDao.findAll();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookDao.findByTitleContaining(searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookDao.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        Book bookToUpdate = bookDao.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setAuthor(book.getAuthor());
        return bookDao.save(bookToUpdate);
    }


    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id){
    	bookDao.deleteById(id);
        return true;
    }
}
