package com.example.demo.book.Functions;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class SortAndPages {
    public static Pageable sortAndPages(String s, Integer page, Integer eachPage){
        Pageable toReturn;
        if (s == null){
            return toReturn = PageRequest.of(page, eachPage);
        }
        s = s.replace(" ", "");
        String first = String.valueOf(s.charAt(0));
        if (Objects.equals(first, "-") && (Objects.equals(s.substring(1), "title") || Objects.equals(s.substring(1), "author") || Objects.equals(s.substring(1), "price") || Objects.equals(s.substring(1), "id") || Objects.equals(s.substring(1), "write"))){
            toReturn = PageRequest.of(page, eachPage, Sort.by(s.substring(1)).descending());
        }else if(Objects.equals(s, "title") || Objects.equals(s, "author") || Objects.equals(s, "write") || Objects.equals(s, "id") || Objects.equals(s, "price")){
            toReturn = PageRequest.of(page, eachPage,Sort.by(s));
        }else{
            toReturn = PageRequest.of(page, eachPage);
        }
        return toReturn;
    }
}
