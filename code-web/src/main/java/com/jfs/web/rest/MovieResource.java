package com.jfs.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api")
public class MovieResource {

    @RequestMapping(value = "/movie/{id}",method = RequestMethod.GET)
    public String movieGet(@PathVariable("id") Integer id){

        return "movie get " + id;
    }

    @RequestMapping(value = "/movie",method = RequestMethod.POST)
    public String moviePost(Integer id){

        return "movie Post " + id;
    }

    @RequestMapping(value = "/movie/{id}",method = RequestMethod.DELETE)
    public String movieDelete(@PathVariable("id") Integer id){

        return "movie Post " + id;
    }

    @RequestMapping(value = "/movie",method = RequestMethod.PUT)
    public String moviePut(Integer id){

        return "movie Post " + id;
    }
}
