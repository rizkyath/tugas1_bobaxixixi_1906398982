package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDB extends JpaRepository<Store, Long>{
}
