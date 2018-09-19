package cn.koboro.points.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/menu")
public class MenuController {
     @GetMapping("/iframe")
    public String testIframe(){
        return "menu/iframe";
    }

}
