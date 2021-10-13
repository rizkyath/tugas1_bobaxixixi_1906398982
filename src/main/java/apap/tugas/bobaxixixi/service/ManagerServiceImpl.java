package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Manager;
import apap.tugas.bobaxixixi.repository.ManagerDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    ManagerDB managerDB;

    @Override
    public List<Manager> getListManager(){
        return managerDB.findAll();
    }

    @Override
    public List<Manager> getAvailableManagerForStore() {
        List<Manager> allManager = getListManager();
        List<Manager> availableManager = new ArrayList<>();
        for (Manager manager : allManager){
            if (manager.getStore() == null) availableManager.add(manager);
        }
        return availableManager;
    }
}
