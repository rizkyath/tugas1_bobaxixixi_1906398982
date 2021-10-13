package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingDB extends JpaRepository<Topping, Long> {

}
