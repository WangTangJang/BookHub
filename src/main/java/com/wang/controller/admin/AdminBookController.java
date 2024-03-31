package com.wang.controller.admin;

import com.wang.model.Books;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/book")
public class AdminBookController {

    @Autowired
    BooksService booksService;

    @GetMapping("/list")
    public String listBooks(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Books> booksPage = booksService.findAllBooks(pageable);
        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("currentPage", booksPage.getNumber());
        model.addAttribute("totalPages", booksPage.getTotalPages());
        model.addAttribute("totalItems", booksPage.getTotalElements());
        return "admin/book/bookList";
    }

    @GetMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") Long bookId, Model model){

        Books book = booksService.selectById(bookId);
        model.addAttribute("book", book);

        return "admin/book/bookEdit";
    }
    @PostMapping("/doEdit")
    public String doEdit(@ModelAttribute("book") Books book ,@RequestParam("coverFile") MultipartFile coverFile,@RequestParam("bookFile") MultipartFile bookFile){
        if (!coverFile.isEmpty()){
            String cover = booksService.uploadCover(coverFile);
            book.setCover(cover);
        }
        if (!bookFile.isEmpty()){
            String filePath = booksService.uploadFile(bookFile);
            book.setFilePath(filePath);
            long fileSizeKB = bookFile.getSize() / 1024;
            book.setFileSize(fileSizeKB);
        }
        booksService.update(book);

        return "redirect: /admin/book/list";
    }
    @GetMapping("doDel/{id},{pageNum}")
    public String doDel(@PathVariable("id") int bookId,@PathVariable("pageNum")  int pageNum){
        booksService.deleteById(bookId);
        return "redirect: /admin/book/list?page="+pageNum;
    }

    @GetMapping("/toAdd")
    public String toAdd(){
        return "admin/book/bookAdd";
    }

    @PostMapping("/doAdd")
    public String doAdd(@ModelAttribute("book") Books book ,@RequestParam("coverFile") MultipartFile coverFile,@RequestParam("bookFile") MultipartFile bookFile){
        String filePath = booksService.uploadFile(bookFile);
        String cover = booksService.uploadCover(coverFile);
        long fileSizeKB = bookFile.getSize() / 1024;
        book.setFileSize(fileSizeKB);
        book.setCover(cover);
        book.setFilePath(filePath);
        booksService.adminUpload(book);
        return "redirect: /admin/book/list";
    }
    @GetMapping("/toAuditList")
    public String toAudit(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          Model model){
        Pageable pageable = PageRequest.of(page, size);
        Page<Books> booksPage = booksService.getUnauditedByPage(pageable);
        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("currentPage", booksPage.getNumber());
        model.addAttribute("totalPages", booksPage.getTotalPages());
        model.addAttribute("totalItems", booksPage.getTotalElements());
        return "admin/book/auditList";
    }

    @GetMapping("/toAudit/{id}")
    public String toAudit(@PathVariable("id") Long bookId, Model model){

        Books book = booksService.selectUnaudited(bookId);
        model.addAttribute("book", book);

        return "admin/book/auditView";
    }
    @RequestMapping("/pass/{id}")
    public String pass(@PathVariable("id") Long bookId, Model model){
        booksService.pass(bookId);
        return "redirect: /admin/book/toAuditList";
    }
}
