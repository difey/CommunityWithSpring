package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.Provider.GithubProvider;
import com.example.demo.Provider.GoogleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private GoogleProvider googleProvider;
    @Value("${github.client.id}")
    private String githubClientID;
    @Value("${github.client.secret}")
    private String githubClientSecret;
    @Value("${github.client.callback}")
    private String githubCallback;

    @Value("${google.client.id}")
    private String googleClientID;
    @Value("${google.client.secret}")
    private String gooleClientSecret;
    @Value("${google.client.callback}")
    private String googleCallback;
    @Value("${google.client.grant.type}")
    private String googleGrantType;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           Model model){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(githubCallback);
        accessTokenDTO.setClient_id(githubClientID);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(githubClientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO).split("&")[0].split("=")[1];
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null) System.out.println(githubUser.getId());
        model.addAttribute("name",githubUser.getName());
        return "hello";
    }

    @GetMapping("/callback_google")
    public String callbackGoogle(@RequestParam(name = "code")String code,
                                 @RequestParam(name = "state")String state,
                                 Model model){
        if(!state.equals("1")){
            return "error";
        }
        GoogleTokenDTO googleTokenDTO = new GoogleTokenDTO();
        googleTokenDTO.setClient_id(googleClientID);
        googleTokenDTO.setClient_secret(gooleClientSecret);
        googleTokenDTO.setCode(code);
        googleTokenDTO.setRedirect_uri(googleCallback);
        googleTokenDTO.setGrant_type(googleGrantType);
        GoogleIDTokenDTO googleIDTokenDTO = googleProvider.getGoogleIDToken(googleTokenDTO);
        GoogleUser googleUser = googleProvider.parseJWT(googleIDTokenDTO.getId_token());
        model.addAttribute("name",googleUser.getName());
        return "hello";
    }

}
