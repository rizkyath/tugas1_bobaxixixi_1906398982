package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTea;
import apap.tugas.bobaxixixi.model.BobaTeaXStore;
import apap.tugas.bobaxixixi.model.Store;
import apap.tugas.bobaxixixi.repository.BobaTeaDB;
import apap.tugas.bobaxixixi.repository.BobaTeaXStoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BobaTeaXStoreServiceImpl implements BobaTeaXStoreService {

    @Autowired
    BobaTeaXStoreDB bobaTeaXStoreDB;

    @Autowired
    BobaTeaDB bobaTeaDB;

    @Override
    public void addBobaTeaXStore(BobaTeaXStore bobaTeaXStore){
        bobaTeaXStore.setProduction_code(generateProductionCode(bobaTeaXStore));
        bobaTeaXStoreDB.save(bobaTeaXStore);
    }

    @Override
    public String generateProductionCode(BobaTeaXStore bobaTeaXStore){
        String generatedCode = "PC";
        String idStore = String.format("%03d", bobaTeaXStore.getStore().getIdStore());
        BobaTea bobaTea = bobaTeaDB.getBobaTeaByIdBobaTea(bobaTeaXStore.getBobaTea().getIdBobaTea());
        String topping = bobaTea.getTopping() == null ? "0" : "1";
        String idBoba = String.format("%03d", bobaTeaXStore.getBobaTea().getIdBobaTea());
        generatedCode = generatedCode.concat(idStore).concat(topping).concat(idBoba);
        return generatedCode;
    }

    @Override
    public void deleteRelasiByStore(Store store){
        bobaTeaXStoreDB.deleteAllByStore(store);
    }

    @Override
    public void deleteRelasiByBobaTea(BobaTea bobaTea) {
        bobaTeaXStoreDB.deleteAllByBobaTea(bobaTea);
    }

    @Override
    public List<BobaTeaXStore> filterRelasiByBoba(List<BobaTea> listBoba){
        List<BobaTeaXStore> listBobaTeaXStore = new ArrayList<>();
        for (BobaTea bobaTea : listBoba) {
            List<BobaTeaXStore> filteredBobaTeaXStore = bobaTeaXStoreDB.findAllByBobaTea(bobaTea);
            listBobaTeaXStore.addAll(filteredBobaTeaXStore);
        }
        return listBobaTeaXStore;
    }

}
