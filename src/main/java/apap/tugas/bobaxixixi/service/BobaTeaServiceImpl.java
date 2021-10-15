package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTea;
import apap.tugas.bobaxixixi.model.Topping;
import apap.tugas.bobaxixixi.repository.BobaTeaDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BobaTeaServiceImpl implements BobaTeaService {
    @Autowired
    BobaTeaDB bobaTeaDB;

    @Override
    public List<BobaTea> getListBobaTea(){
        return bobaTeaDB.findAll();
    }

    @Override
    public void addBobaTea(BobaTea bobaTea){
        bobaTeaDB.save(bobaTea);
    }

    @Override
    public BobaTea getBobaTeaById(long idBobaTea){
        return bobaTeaDB.getBobaTeaByIdBobaTea(idBobaTea);
    }

    @Override
    public void updateBobaTea(BobaTea bobaTea){
        bobaTeaDB.save(bobaTea);
    }

    @Override
    public void deleteBobaTeaById(long idBobaTea){
        bobaTeaDB.deleteBobaTeaByIdBobaTea(idBobaTea);
    }

    @Override
    public List<BobaTea> filterBobaTea(String bobaName, String toppingName){
        List<BobaTea> filteredBobaTea = bobaTeaDB.findAllByName(bobaName);
        for (int i = 0; i<filteredBobaTea.size(); i++){
            if (!filteredBobaTea.get(i).getTopping().getName().equals(toppingName))
                filteredBobaTea.remove(i);
        }
        return filteredBobaTea;
    }

    @Override
    public List<BobaTea> getListBobaTeaByName(String name) {
        return bobaTeaDB.findAllByName(name);
    }

}
