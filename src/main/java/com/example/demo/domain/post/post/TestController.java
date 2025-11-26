package com.example.demo.domain.post.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @GetMapping("/")
    @ResponseBody
    public String test() {
        return "hahaha";
    }

    @GetMapping("/posts")
    @ResponseBody
    public String posts() {
        return "posts";
    }

    @GetMapping("/posts/12")
    public String posts12() {
        return "test";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/param")
    @ResponseBody
    public String param(String name, int age) {

        System.out.println("name: " + name);
        System.out.println("age: " + age);

        return "param";
    }

    @GetMapping("/getName")
    public String getHtml(String name, Model model) {

        model.addAttribute("name1", name);
        return "name";
    }

    @GetMapping("/list")
    public String getList(Model model) {
        List<String> list = List.of("홍길동", "김철수", "박영희");

        model.addAttribute("list", list);

        return "nameList";
    }

}
