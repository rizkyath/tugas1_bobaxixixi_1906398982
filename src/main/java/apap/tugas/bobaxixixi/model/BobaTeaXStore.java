package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class BobaTeaXStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_store")
    Store store;

    @ManyToOne
    @JoinColumn(name = "id_boba_tea")
    BobaTea bobaTea;

    @NotNull
    @Size(max=255)
    @Column(name = "production_code", nullable = false, unique = true)
    private String production_code;

}
