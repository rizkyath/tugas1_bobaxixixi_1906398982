package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDB extends JpaRepository<Manager, Long>{
}
