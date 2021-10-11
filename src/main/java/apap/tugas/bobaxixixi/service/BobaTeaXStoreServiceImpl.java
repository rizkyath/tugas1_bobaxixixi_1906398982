package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTea;
import apap.tugas.bobaxixixi.model.BobaTeaXStore;
import apap.tugas.bobaxixixi.repository.BobaTeaDB;
import apap.tugas.bobaxixixi.repository.BobaTeaXStoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BobaTeaXStoreServiceImpl implements BobaTeaXStoreService {

    @Autowired
    BobaTeaXStoreDB bobaTeaXStoreDB;

    @Autowired
    BobaTeaDB bobaTeaDB;

    @Override
    public void setProductionCode(BobaTeaXStore bobaTeaXStore){
        bobaTeaXStore.setProduction_code(generateProductionCode(bobaTeaXStore));
    }

    @Override
    public String generateProductionCode(BobaTeaXStore bobaTeaXStore){
        String generatedCode = "PC";
        String idStore = String.format("%03d", bobaTeaXStore.getStore().toString());
        BobaTea bobaTea = bobaTeaDB.getBobaTeaByIdBobaTea(bobaTeaXStore.getBobaTea().getIdBobaTea());
        String topping = bobaTea.getTopping() == null ? "0" : "1";
        String idBoba = String.format("%03d", bobaTeaXStore.getBobaTea().toString());
        generatedCode = generatedCode.concat(idStore).concat(topping).concat(idBoba);
        return generatedCode;
    }
}
