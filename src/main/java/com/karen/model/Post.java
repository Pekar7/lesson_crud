package com.karen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post {
    private String id;
    private String title;
    private String content;
    private List<Label> labels;
    private Status status;
}
