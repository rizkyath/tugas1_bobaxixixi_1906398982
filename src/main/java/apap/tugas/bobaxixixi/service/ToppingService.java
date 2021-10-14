package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.Topping;

import java.util.List;

public interface ToppingService {
    List<Topping> getListTopping();
    List<Topping> getListToppingByName(String toppingName);
}
