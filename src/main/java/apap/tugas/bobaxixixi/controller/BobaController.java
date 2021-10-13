package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BobaController {

    @GetMapping("/boba")
    public String listBoba(){
        return "home";
    }

    @GetMapping("/search")
    public String search(){
        return "home";
    }
}
