package apap.tugas.bobaxixixi.service;


import apap.tugas.bobaxixixi.model.BobaTea;

import java.util.List;

public interface BobaTeaService {
    List<BobaTea> getListBobaTea();
    void addBobaTea(BobaTea bobaTea);
}
