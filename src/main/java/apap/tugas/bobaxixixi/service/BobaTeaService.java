package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTea;

import java.util.List;

public interface BobaTeaService {
    List<BobaTea> getListBobaTea();
    void addBobaTea(BobaTea bobaTea);
    BobaTea getBobaTeaById(long idBobaTea);
    void updateBobaTea(BobaTea bobaTea);
    void deleteBobaTeaById(long idBobaTea);
    List<BobaTea> filterBobaTea(String name, String Topping);
    List<BobaTea> getListBobaTeaByName(String name);
}
