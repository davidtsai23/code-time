package com.jfs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class MovieController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {

        return "index";
    }
}
