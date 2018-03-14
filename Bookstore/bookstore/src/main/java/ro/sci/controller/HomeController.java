package ro.sci.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tripura on 3/13/2018.
 */

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "index";}
}
