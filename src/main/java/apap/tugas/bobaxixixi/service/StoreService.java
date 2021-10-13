package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Store;

import java.util.List;

public interface StoreService {
    String generateStoreCode(Store store);
    String generateRandStr();
    List<Store> getListStore();
    void addStore(Store store);
    Store getStoreById(long idStore);
    void updateStore(Store store);
    boolean isTutup(Store store);
    boolean  delAvail(Store store);
    void deleteById(long idStore);
}
