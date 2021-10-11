package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Store;
import apap.tugas.bobaxixixi.repository.StoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDB storeDB;

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
}
