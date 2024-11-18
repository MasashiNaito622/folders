package com.example.dbappsample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dbappsample.model.User;
import com.example.dbappsample.repository.UserRepository;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "users";//ビューの名前を返す
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user"; // ユーザー追加フォームのビュー
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userRepository.save(user);
        return "redirect:/users"; // ユーザーリストページへリダイレクト
    }
}
