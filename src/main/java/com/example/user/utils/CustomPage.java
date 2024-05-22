package com.example.user.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomPage<T> {
    private String next;
    private String previous;
    private int total;
    private List<T> results;
}
