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
public class Writer {
    private String id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
    private Status status;
}
