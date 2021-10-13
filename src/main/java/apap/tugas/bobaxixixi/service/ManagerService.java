package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> getListManager();
    List<Manager> getAvailableManagerForStore();
}
