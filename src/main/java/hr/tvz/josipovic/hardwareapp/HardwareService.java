package hr.tvz.josipovic.hardwareapp;

import java.util.List;
import java.util.Optional;

public interface HardwareService
{
    List<HardwareDTO> findAll();
    Optional<HardwareDTO> findbyCode(String code);
    Optional<HardwareDTO> insert(HardwareCommand cmd);
    void deleteByCode(String code);

}
