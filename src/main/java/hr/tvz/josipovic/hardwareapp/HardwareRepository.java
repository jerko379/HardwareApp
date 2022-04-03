package hr.tvz.josipovic.hardwareapp;

import java.util.List;
import java.util.Optional;


public interface HardwareRepository {
    List <Hardware> findAll();
    Optional <Hardware> findByCode(String code);
    Optional<Hardware> insert(Hardware hw);
    void deleteByCode(String code);
}
