package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.*;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.BobaTeaXStoreService;
import apap.tugas.bobaxixixi.service.StoreService;
import apap.tugas.bobaxixixi.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class BobaController {
    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @Qualifier("bobaTeaXStoreServiceImpl")
    @Autowired
    private BobaTeaXStoreService bobaTeaXStoreService;

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

    @GetMapping("/boba/update/{idBobaTea}")
    public String updateBobaTeaForm(
            @PathVariable long idBobaTea,
            Model model
    ) {
        BobaTea bobaTea = bobaTeaService.getBobaTeaById(idBobaTea);
        model.addAttribute("bobaTea", bobaTea);
        model.addAttribute("topping", bobaTea.getTopping());
        model.addAttribute("listTopping", toppingService.getListTopping());
        return "bobatea/form-update-bobatea";
    }

    @PostMapping("/boba/update/{idBobaTea}")
    public String updateBobaTeaSubmitPage(
            @PathVariable long idBobaTea,
            @ModelAttribute BobaTea bobaTea,
            Model model
    ){
        BobaTea bobaTea1 = bobaTeaService.getBobaTeaById(idBobaTea);
        Set<BobaTeaXStore> bobaTeaXStoreSet = bobaTea1.getBobaTeaXStoreSet();
        if (bobaTeaXStoreSet != null){
            for (BobaTeaXStore bobaTeaXStore : bobaTeaXStoreSet){
                Store store = bobaTeaXStore.getStore();
                if (!storeService.isTutup(store)) return "bobatea/fail-update-bobatea";
            }
        }
        bobaTeaService.updateBobaTea(bobaTea);
        model.addAttribute("namaBobaTea", bobaTea.getName());
        model.addAttribute("idBobaTea", bobaTea.getIdBobaTea());
        return "bobatea/success-update-bobatea";
    }

    @GetMapping("boba/delete/{idBobaTea}")
    public String deleteBobaTea(
            @PathVariable long idBobaTea,
            Model model
    ) {
        BobaTea bobaTea = bobaTeaService.getBobaTeaById(idBobaTea);
        Set<BobaTeaXStore> bobaTeaXStoreSet = bobaTea.getBobaTeaXStoreSet();
        model.addAttribute("namaBobaTea", bobaTea.getName());
        model.addAttribute("idBobaTea", bobaTea.getIdBobaTea());
        try {
            if (bobaTeaXStoreSet.size() == 0) {
                bobaTeaService.deleteBobaTeaById(idBobaTea);
                return "bobatea/success-delete-bobatea";
            } else {
                return "bobatea/fail-delete-bobatea";
            }
        } catch (NullPointerException e) {
            bobaTeaService.deleteBobaTeaById(idBobaTea);
            return "bobatea/success-delete-bobatea";
        }
    }

    @GetMapping("/boba/{idBoba}/assign-store")
    public String assignStoreForm(
            @PathVariable long idBoba,
            Model model
    ) {
        BobaTea bobaTea = bobaTeaService.getBobaTeaById(idBoba);
        List<Store> listStore = storeService.getListStore();
        model.addAttribute("bobaTea", bobaTea);
        model.addAttribute("listStore", listStore);
        return "bobatea/form-assign-store";
    }

    @PostMapping("/boba/{idBoba}/assign-store")
    public String assignStoreSubmitPage(
            @PathVariable long idBoba,
            @ModelAttribute BobaTea bobaTeaPassed,
            @RequestParam(value="storeAssignedId", required = false) int[] storeAssignedIds,
            Model model
    ){
        BobaTea bobaTea = bobaTeaService.getBobaTeaById(idBoba);
        bobaTeaXStoreService.deleteRelasiByBobaTea(bobaTea);
        if (storeAssignedIds != null){
            for (int i = 0; i<storeAssignedIds.length; i++) {
                BobaTeaXStore bobaTeaXStore = new BobaTeaXStore();
                Store store = storeService.getStoreById(storeAssignedIds[i]);
                bobaTeaXStore.setStore(store);
                bobaTeaXStore.setBobaTea(bobaTea);
                bobaTeaXStoreService.addBobaTeaXStore(bobaTeaXStore);
            }
            model.addAttribute("listBobaTeaXStore", bobaTea.getBobaTeaXStoreSet());
            model.addAttribute("msg", "Store successfully updated for Boba Tea " + bobaTea.getName());
        } else {
            model.addAttribute("msg", "Store unassigned from Store " + bobaTea.getName());
        }
        return "bobatea/success-assign-store";
    }

    @GetMapping("/search")
    public String searchBoba(
            @RequestParam(name="bobaName", required = false) String bobaName,
            @RequestParam(name="topping", required = false) String toppingName,
            Model model
    ) {
        List<BobaTea> listBobaTea = bobaTeaService.getListBobaTea();
        List<Topping> listTopping = toppingService.getListTopping();
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("listTopping", listTopping);
        List<BobaTea> filteredBobaByName = bobaTeaService.filterBobaTea(bobaName, toppingName);
        System.out.println(filteredBobaByName);
        List<BobaTeaXStore> listBobaTeaXStore = bobaTeaXStoreService.filterRelasiByBoba(filteredBobaByName);
        for (int i = 0; i<listBobaTeaXStore.size(); i++) {
            Store store = listBobaTeaXStore.get(i).getStore();
            if (storeService.isTutup(store)){
                listBobaTeaXStore.remove(i);
            }
        }
        model.addAttribute("listBobaTeaXStore", listBobaTeaXStore);
        return "bobatea/search";
    }

    @GetMapping("/filter/manager")
    public String filterManager(
            @RequestParam(name="nameBoba", required = false) String bobaName,
            Model model
    ) {
        List<BobaTea> listBobaTea = bobaTeaService.getListBobaTea();
        List<BobaTea> filteredBobaByName = bobaTeaService.getListBobaTeaByName(bobaName);
        List<BobaTeaXStore> listBobaTeaXStore = bobaTeaXStoreService.filterRelasiByBoba(filteredBobaByName);
        Set<Manager> listManager = new HashSet<>();
        for (BobaTeaXStore bobaTeaXStore : listBobaTeaXStore) {
            listManager.add(bobaTeaXStore.getStore().getManager());
        }
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("listManager", listManager);
        return "bobatea/filter-manager";
    }

}
