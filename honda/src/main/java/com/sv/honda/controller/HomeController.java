package com.sv.honda.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    /**
     * 首页
     * @return 跳转到首页
     */
    @GetMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }


}
