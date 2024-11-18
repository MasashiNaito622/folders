package com.example.fukushu1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fukushu1.service.UserService;
import com.example.fukushu1.model.User;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model){
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "users";//ビュー(users.html)の名前を返す
    }
    @GetMapping("/add")
    public String addUserForm(Model model){
        model.addAttribute("user",new User());
        return "add-user";//ユーザー追加フォームのビュー
    }
    @PostMapping("/add")
    public String addUser(User user){
        userService.saveUser(user);
        return "redirect:/users";//登録完了時には一覧へリダイレクトする
    }

}
