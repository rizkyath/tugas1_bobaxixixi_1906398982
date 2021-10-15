package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaXStore;
import apap.tugas.bobaxixixi.model.Store;

public interface BobaTeaXStoreService {
    void addBobaTeaXStore(BobaTeaXStore bobaTeaXStore);
    String generateProductionCode(BobaTeaXStore bobaTeaXStore);
    boolean bobaTeaXStoreNotExist(BobaTeaXStore bobaTeaXStore);
    void deleteRelasiByStore(Store store);
}
