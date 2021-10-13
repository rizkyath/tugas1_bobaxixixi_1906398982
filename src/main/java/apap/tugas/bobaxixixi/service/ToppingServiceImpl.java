package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Topping;
import apap.tugas.bobaxixixi.repository.ToppingDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ToppingServiceImpl implements ToppingService {

    @Autowired
    ToppingDB toppingDB;

    @Override
    public List<Topping> getListTopping(){
        return toppingDB.findAll();
    }
}
