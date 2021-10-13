package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTea;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BobaController {

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @GetMapping("/boba")
    public String listBoba(Model model){
        List<BobaTea> listBobaTea = bobaTeaService.getListBobaTea();
        model.addAttribute("listBobaTea", listBobaTea);
        return "bobatea/viewall-bobatea";
    }

    @GetMapping("/boba/add")
    public String addBobaForm(Model model){
        model.addAttribute("listTopping", toppingService.getListTopping());
        model.addAttribute("bobaTea", new BobaTea());
        return "bobatea/form-add-bobatea";
    }

    @PostMapping("/boba/add")
    public String addBobaSubmitPage(
            @ModelAttribute BobaTea bobaTea,
            Model model){
        bobaTeaService.addBobaTea(bobaTea);
        model.addAttribute("namaBobaTea", bobaTea.getName());
        model.addAttribute("idBobaTea", bobaTea.getIdBobaTea());
        return "bobatea/success-add-bobatea";
    }

    @GetMapping("/search")
    public String search(){
        return "home";
    }

}
