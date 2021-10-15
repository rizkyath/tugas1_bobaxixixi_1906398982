package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.BobaTea;
import apap.tugas.bobaxixixi.model.BobaTeaXStore;
import apap.tugas.bobaxixixi.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BobaTeaXStoreDB extends JpaRepository<BobaTeaXStore, Long> {
    List<BobaTeaXStore> findBobaTeaXStoreByStoreAndAndBobaTea(Store store, BobaTea bobaTea);
    void deleteAllByStore(Store store);
    void deleteAllByBobaTea(BobaTea bobaTea);
}
