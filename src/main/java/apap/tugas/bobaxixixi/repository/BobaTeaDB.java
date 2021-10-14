package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.BobaTea;
import apap.tugas.bobaxixixi.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BobaTeaDB extends JpaRepository<BobaTea, Long> {
    BobaTea getBobaTeaByIdBobaTea(long idBobaTea);
    Long deleteBobaTeaByIdBobaTea(long idBobaTea);
    List<BobaTea> getBobaTeaByTopping(Topping topping);
    List<BobaTea> getBobaTeaByName(String name);
}
