package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaXStore;
import apap.tugas.bobaxixixi.model.Store;
import apap.tugas.bobaxixixi.repository.StoreDB;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDB storeDB;

    @Override
    public List<Store> getListStore(){
        List<Store> listStore = storeDB.findAll();
        return listStore;
    }

    @Override
    public String generateStoreCode(Store store){
        String generatedCode = "SC";
        StringBuilder reverseStoreName = new StringBuilder();
        String storeName = store.getName().substring(0,3).toUpperCase();
        reverseStoreName.append(storeName);
        reverseStoreName.reverse();
        String reversedStoreName = reverseStoreName.toString();
        String openHH = store.getOpenHour().toString().substring(0,2);
        String closeHH = store.getCloseHour().toString().substring(0,2);
        Integer cls = Integer.parseInt(closeHH)/10;
        closeHH = cls.toString();
        String randString = generateRandStr();
        generatedCode = generatedCode.concat(reversedStoreName).concat(openHH).concat(closeHH).concat(randString);
        return generatedCode;
    }

    @Override
    public String generateRandStr(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(2);
        for (int i = 0; i < 2; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public void addStore(Store store){
        store.setStoreCode(generateStoreCode(store));
        storeDB.save(store);
    }

    @Override
    public Store getStoreById(long idStore){
        return storeDB.getStoreByIdStore(idStore);
    }

    @Override
    public void updateStore(Store store){
        storeDB.save(store);
    }

    @Override
    public boolean isTutup(Store store){
        LocalTime openHour = store.getOpenHour();
        LocalTime closeHour = store.getCloseHour();
        LocalTime sekarang = LocalTime.now();
        if (sekarang.compareTo(openHour) < 0 || sekarang.compareTo(closeHour) > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean delAvail(Store store){
        Set<BobaTeaXStore> bobaTeaXStoreSet = store.getBobaTeaXStoreSet();
        if (isTutup(store) && bobaTeaXStoreSet.size() == 0)
            return true;
        else
            return false;
    }

    @Override
    public void deleteById(long idStore){
        storeDB.deleteStoreByIdStore(idStore);
    }
}
