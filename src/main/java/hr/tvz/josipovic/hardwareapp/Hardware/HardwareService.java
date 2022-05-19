package hr.tvz.josipovic.hardwareapp.Hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareService
{
    List<HardwareDTO> findAll();
    Optional<HardwareDTO> findbyCode(String code);
    Optional<HardwareDTO> insert(HardwareCommand cmd);
    Optional<HardwareDTO> update(String code, HardwareCommand updatedHardwareCommand);
    void deleteByCode(String code);


}
