package com.example.demo.controller;

import com.example.demo.DTO.QuestionDTO;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Model.User;
import com.example.demo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return "index";
        }
        for(Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions", questionList);
        return "index";
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
