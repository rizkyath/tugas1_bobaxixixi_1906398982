package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.BobaTea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BobaTeaDB extends JpaRepository<BobaTea, Long> {
    BobaTea getBobaTeaByIdBobaTea(long idBobaTea);
}
