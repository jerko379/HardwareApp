package hr.tvz.josipovic.hardwareapp;

import java.util.List;

public interface HardwareService
{
    List<HardwareDTO> findAll();
    HardwareDTO findbyCode(String code);
}
