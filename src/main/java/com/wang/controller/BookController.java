package com.wang.controller;

import com.wang.model.Books;
import com.wang.model.Bookshelf;
import com.wang.model.User;
import com.wang.model.request.SearchCriteria;
import com.wang.model.result.BookDetailResult;
import com.wang.service.BookRatingsService;
import com.wang.service.BooksService;
import com.wang.service.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BooksService bookService;
    @Autowired
    private BookRatingsService bookRatingsService;
    @Autowired
    private BookshelfService bookshelfService;

    /**
     * 获取全部书籍
     * @return 书籍列表
     */
    @GetMapping("/getBooks")
    public ResponseEntity<?> getBooks(){
        // 从数据库中获取书籍信息
        List<Books> books = bookService.selectPage(1, 12);
        // 将数据封装到map中
        Map<String,Object> data = new HashMap<>();
        data.put("books",books);
        // 返回数据
        return ResponseEntity.ok(data);
    }
    /**
     * 获取某个书籍的详细信息
     * @return 书籍信息
     */
    @GetMapping("/getInfo/{bookId}")
    public ResponseEntity<?> toBookInfo(@PathVariable Long bookId, HttpSession session){
        BookDetailResult result = new BookDetailResult();
        // 获取书籍信息根据id
        Books books = bookService.selectById(bookId);
        result.setBookInfo(books);
        // 如果书籍存在
        if (books !=null ){
            // 判断用户是否登录
            User user = (User) session.getAttribute("user");
            if (user!=null){
                if (bookRatingsService.isRated(user.getId(),bookId)){
                    // 如果用户已经登录，就查看这个用户对数据的评分
                    result.setUserRating(bookRatingsService.selectBookRating(user.getId(),bookId));
                }
                // 查看用户是否收藏了这本书
                result.setCollected(bookshelfService.isCollected(user.getId(),bookId));
            }
            // 返回书籍信息
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.badRequest().body("Book not found");
        }
    }

    /**
     * 保存用户对书籍的评分
     * @param data 书籍id和评分
     * @param session 会话
     * @param model 模型
     * @return 保存结果
     */
    @PostMapping("saveRating")
    public ResponseEntity<?> saveRating(@RequestBody Map<String,Integer> data, HttpSession session, Model model){
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if (user != null) {
            bookRatingsService.rateBook(user.getId(),data.get("bookId"), data.get("rating"));
            // 更新书籍数据
            return ResponseEntity.ok("success");
        }
        else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
    /**
     * 用户 收藏书籍
     * @param bookId 书籍id
     * @param session 会话
     * @param model 模型
     * @return 收藏结果
     */
    @GetMapping("collect/{bookId}")
    public ResponseEntity<?> collect(@PathVariable Long bookId,HttpSession session, Model model){
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 执行收藏操作
            bookshelfService.addToBookshelf(user.getId(),bookId);
            return ResponseEntity.ok("success");
        }
        else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
    /**
     * 用户 取消收藏书籍
     * @param bookId 书籍id
     * @param session 会话
     * @param model 模型
     * @return 取消收藏结果
     */
    @GetMapping("cancelCollect/{bookId}")
    public ResponseEntity<?> cancelCollect(@PathVariable Long bookId,HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 执行取消收藏操作
            bookshelfService.delFormBookshelf(user.getId(),bookId);
            return ResponseEntity.ok("success");
        }
        else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
    /**
     * 搜索书籍
     * @param searchCriteria 搜索条件
     * @param session 会话
     * @return 搜索结果
     */
    @PostMapping("/doSearch")
    public ResponseEntity<?> doSearch(@RequestBody SearchCriteria searchCriteria, HttpSession session){
        List<Books> booksList = bookService.search(searchCriteria.getKeyword());
        Map<String,Object> result = new HashMap<>();
        result.put("books",booksList);
        return ResponseEntity.ok(result);
    }

    /**
     * 用户上传书籍
     * @param book 书籍元信息
     * @param coverFile 书籍封面二进制数据
     * @param bookFile 书籍文件二进制信息
     * @param session session
    */
    @PostMapping("/userUpload")
    public ResponseEntity<?> userUpload(@ModelAttribute("book") Books book ,
                                        @RequestParam("coverFile") MultipartFile coverFile,
                                        @RequestParam("bookFile") MultipartFile bookFile,
                                        HttpSession session){
        String filePath = bookService.uploadFile(bookFile);
        String cover = bookService.uploadCover(coverFile);
        book.setFileSize(bookFile.getSize());
        book.setCover(cover);
        book.setFilePath(filePath);
        User user = (User) session.getAttribute("user");
        String uploadResult = bookService.userUpload(book,user.getUsername());
        Map<String,Object> result = new HashMap<>();
        result.put("result",uploadResult);
        result.put("message","上传成功等待审核");
        return ResponseEntity.ok(result);
    }
    /**
     * 去往书架页面
     */
    @GetMapping("/toShelf")
    public String toShelf(Model model, HttpSession session){ // 不再去用什么json了,直接返回页面. 够吧分离
        User user = (User) session.getAttribute("user");
        List<Books> books = bookshelfService.getBooksByUserId(user.getId());
        model.addAttribute("books",books);
        return "userDisplay/component/bookShelf :: #bookDisplay";
    }
}
