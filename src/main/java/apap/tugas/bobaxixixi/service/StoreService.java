package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Store;

public interface StoreService {
    String generateStoreCode(Store store);
    String generateRandStr();
    void addStore(Store store);
}
