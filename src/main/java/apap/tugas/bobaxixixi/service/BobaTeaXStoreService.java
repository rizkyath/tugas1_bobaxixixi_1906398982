package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTea;
import apap.tugas.bobaxixixi.model.BobaTeaXStore;
import apap.tugas.bobaxixixi.model.Store;

import java.util.List;

public interface BobaTeaXStoreService {
    void addBobaTeaXStore(BobaTeaXStore bobaTeaXStore);
    String generateProductionCode(BobaTeaXStore bobaTeaXStore);
    void deleteRelasiByStore(Store store);
    void deleteRelasiByBobaTea(BobaTea bobaTea);
    List<BobaTeaXStore> filterRelasiByBoba(List<BobaTea> listBoba);
}
