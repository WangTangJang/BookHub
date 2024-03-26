package com.wang.controller.admin;

import com.wang.model.Books;
import com.wang.model.User;
import com.wang.model.Users;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public String listUser(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.findAllUser(pageable);
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", userPage.getNumber());
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("totalItems", userPage.getTotalElements());
        return "admin/book/userList";
    }

    @GetMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") int userId, Model model){

        User user = userService.selectById(userId);
        model.addAttribute("user", user);

        return "admin/book/userEdit";
    }
    @PostMapping("/doEdit")
    public String doEdit(@ModelAttribute Users user , @RequestParam("profilePictureFile") MultipartFile profilePictureFIle){

        if (!profilePictureFIle.isEmpty()){
            String cover = userService.uploadFile(profilePictureFIle);
            //user.setProfilePicture(cover);
        }
        //userService.updateUserProfile(user);
        return "redirect: /admin/user/list";
    }
}
