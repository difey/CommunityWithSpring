package com.example.demo.Model;

import lombok.Data;

@Data
public class Question {
    Integer id;
    String title;
    String description;
    Long gmtCreate;
    Long gmtModified;
    Integer creator;
    String tag;
    Integer viewCount;
    Integer commentCount;
    Integer likeCount;
}
