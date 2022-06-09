package hr.tvz.josipovic.hardwareapp.Hardware;

import java.util.List;
import java.util.Optional;


public interface HardwareRepository {
    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> insert(Hardware hw);

    Optional<Hardware> update(String code, Hardware updateHw);

    void deleteByCode(String code);

    Optional<Hardware> updateQuantity(String code, Integer quantity);
}
