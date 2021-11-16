package com.epam.sk.bookservice.controller;

import com.epam.sk.bookservice.model.Book;
import com.epam.sk.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("books")
    List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("books/{id}")
    Optional<Book> getBooksById(@PathVariable("id") Long id){
        return bookRepository.findById(id);
    }

    @PostMapping("books")
    Book addBooks(@RequestBody Book book){
         return bookRepository.save(book);
    }

    @DeleteMapping("books/{id}")
    void deleteBookByID(@PathVariable("id") Long id){
         bookRepository.deleteById(id);
    }

    @PutMapping("books/{id}")
    Book updateBooks(@RequestBody Book book,@PathVariable("id") Long id){
        Optional<Book> entity =  bookRepository.findById(id);
        if(entity.isPresent()){
           Book object =  entity.get();
            object.setAuthor(book.getAuthor());
            object.setCategory(book.getCategory());
            object.setDescription(book.getDescription());
            object.setTitle(book.getTitle());
            return bookRepository.save(object);
        }else
            return null;
    }

}