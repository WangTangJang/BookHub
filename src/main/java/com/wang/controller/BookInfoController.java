package com.wang.controller;


import com.wang.model.Books;
import com.wang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookInfoController {

    @Autowired
    private BooksService service;

    @RequestMapping("/list")
    public String listBooks(
            HttpServletRequest request,
            @RequestParam(name = "page",defaultValue = "1") int page,
            @RequestParam(name = "size",defaultValue = "6") int size,
            Model model){


        model.addAttribute("RootPath",request.getContextPath());
        int total = service.count();
        // 计算总页数
        int totalPages = (int)Math.ceil((double) total/size);

        List<Books> books = service.selectPage(page,size);
        model.addAttribute("books", books);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",totalPages);
        // 用于告知前端当前所在页面，用来控制导航栏激活状态
        model.addAttribute("currentHtml" ,"list");
        return "admin/book/list";
    }
    @RequestMapping("toAddView")
    public String toAddView(Model model){
        model.addAttribute("books",new Books());
        // 用于告知前端当前页面
        model.addAttribute("currentHtml" ,"add");
        return "admin/book/add";
    }

    @RequestMapping("doAdd")
    public String doAdd(@ModelAttribute("books") Books books, RedirectAttributes redirectAttributes){
        service.adminUpload(books);
        redirectAttributes.addAttribute("message", "书籍添加成功");
        return "redirect:/books/toAddView";
    }

    @RequestMapping("del/{id}")
    public String doDel(@PathVariable("id") int id, @ModelAttribute("books") Books books,RedirectAttributes redirectAttributes){
        books = service.selectById(id);
        service.deleteById(id);
        redirectAttributes.addAttribute("delBooked",books.getTitle());
        return "redirect:/books/list";
    }

    @RequestMapping("toMod/{id}")
    public String toMod(@PathVariable("id") int id,Model model){
        Books book =  service.selectById(id);
        model.addAttribute("book",book);
        return "admin/book/mod";
    }
    @RequestMapping("doMod")
    public String doMod(@ModelAttribute("book") Books books,RedirectAttributes redirectAttributes){
        service.update(books);
        redirectAttributes.addAttribute("modBooked",books.getTitle());
        return "redirect:/books/list";
    }

    @RequestMapping("search")
    public String search(@RequestParam("keyword") String keyword,Model model){
        List<Books> books = service.search(keyword);
        model.addAttribute("books",books);
        return "admin/book/list";
    }
}
