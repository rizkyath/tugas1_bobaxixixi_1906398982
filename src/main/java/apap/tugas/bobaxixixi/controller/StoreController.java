package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTea;
import apap.tugas.bobaxixixi.model.BobaTeaXStore;
import apap.tugas.bobaxixixi.model.Manager;
import apap.tugas.bobaxixixi.model.Store;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.BobaTeaXStoreService;
import apap.tugas.bobaxixixi.service.ManagerService;
import apap.tugas.bobaxixixi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class StoreController {

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("bobaTeaXStoreServiceImpl")
    @Autowired
    private BobaTeaXStoreService bobaTeaXStoreService;

    @GetMapping("/store")
    public String listStore(Model model){
            List<Store> listStore = storeService.getListStore();
            model.addAttribute("listStore", listStore);
        return "store/viewall-store";
    }

    @GetMapping("/store/add")
    public String addStoreForm(Model model){
        model.addAttribute("store", new Store());
        model.addAttribute("listManager", managerService.getAvailableManagerForStore());
        return "store/form-add-store";
    }

    @PostMapping(value="/store/add")
    public String addAgensiSubmitPage(
            @ModelAttribute Store store,
            Model model
    ){
        storeService.addStore(store);
        model.addAttribute("namaStore", store.getName());
        model.addAttribute("storeCode", store.getStoreCode());
        return "store/success-add-store";
    }

    @GetMapping("/store/{idStore}")
    public String viewDetailStore(
            @PathVariable Long idStore,
            Model model
    ){
        Store store = storeService.getStoreById(idStore);
        Set<BobaTeaXStore> bobaTeaXStore = store.getBobaTeaXStoreSet();
        model.addAttribute("store", store);
        model.addAttribute("bobaTeaXStore", bobaTeaXStore);
        for (BobaTeaXStore bobaTeaXStore1 : store.getBobaTeaXStoreSet()){
            System.out.println(bobaTeaXStore1.getBobaTea().getName());
        }
        return "store/detail-store";
    }

    @GetMapping("/store/update/{idStore}")
    public String updateStoreForm(
            @PathVariable Long idStore,
            Model model
    ){
        Store store = storeService.getStoreById(idStore);
        Manager storeManager = store.getManager();
        model.addAttribute("storeManager", storeManager);
        model.addAttribute("listManager", managerService.getAvailableManagerForStore());
        model.addAttribute("store", store);
        return "store/form-update-store";
    }

    @PostMapping("/store/update")
    public String updateStoreSubmitPage(
            @ModelAttribute Store store,
            Model model
    ){
        model.addAttribute("namaStore", store.getName());
        model.addAttribute("storeCode", store.getStoreCode());
        if (storeService.isTutup(store)){
            storeService.updateStore(store);
            return "store/success-update-store";
        } else {
            return "store/fail-update-store";
        }
    }

    @GetMapping("/store/delete/{idStore}")
    public String deleteStore(
            @PathVariable long idStore,
            Model model
    ){
        Store store = storeService.getStoreById(idStore);
        model.addAttribute("namaStore", store.getName());
        model.addAttribute("storeCode", store.getStoreCode());
        if (storeService.delAvail(store)){
            storeService.deleteById(idStore);
            return "store/success-delete-store";
        } else {
            return "store/fail-delete-store";
        }
    }

    @GetMapping("/store/{idStore}/assign-boba")
    public String assignBobaForm(
            @PathVariable long idStore,
            Model model
    ) {
        Store store = storeService.getStoreById(idStore);
        List<BobaTea> listBoba = bobaTeaService.getListBobaTea();
        model.addAttribute("store", store);
        model.addAttribute("listBobaTea", listBoba);
        return "store/form-assign-boba";
    }

    @PostMapping("/store/{idStore}/assign-boba")
    public String assignBobaSubmitPage(
            @ModelAttribute Store storePassed,
            @RequestParam(value="bobaAssignedId", required = false) int[] bobaAssignedIds,
            Model model
    ){
        Store store = storeService.getStoreById(storePassed.getIdStore());
        bobaTeaXStoreService.deleteRelasiByStore(store);
        if (bobaAssignedIds != null){
            for (int i = 0; i<bobaAssignedIds.length; i++) {
                BobaTeaXStore bobaTeaXStore = new BobaTeaXStore();
                BobaTea bobaTea = bobaTeaService.getBobaTeaById(bobaAssignedIds[i]);
                bobaTeaXStore.setStore(store);
                bobaTeaXStore.setBobaTea(bobaTea);
                bobaTeaXStoreService.addBobaTeaXStore(bobaTeaXStore);
            }
            model.addAttribute("listBobaTeaXStore", store.getBobaTeaXStoreSet());
            model.addAttribute("msg", "Boba Teas successfully updated for Store" + store.getName());
        } else {
            model.addAttribute("msg", "Boba unassigned from Store " + store.getName());
        }
        return "store/success-assign-boba";
    }

}
