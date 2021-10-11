package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaXStore;

public interface BobaTeaXStoreService {
    void setProductionCode(BobaTeaXStore bobaTeaXStore);
    String generateProductionCode(BobaTeaXStore bobaTeaXStore);
}
