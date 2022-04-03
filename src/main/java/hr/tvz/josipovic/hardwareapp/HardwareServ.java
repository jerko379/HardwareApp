package hr.tvz.josipovic.hardwareapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class HardwareServ implements HardwareService{

    private final HardwareRepo repo;

    public HardwareServ(HardwareRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return repo.findAll().stream().map(this::mapHardwareToDto).collect(Collectors.toList());
    }

    @Override
    public HardwareDTO findbyCode(String code) {

        //return repo.findByCode(code).map(this::mapHardwareToDto).orElse(null);

        // 1.LAB Z
        HardwareDTO vrHardver = repo.findByCode(code).map(this::mapHardwareToDto).orElse(null);
        Hardware cHardver = repo.findByCode(code).orElse(null);
        if (cHardver.getQuantity() < 3 && cHardver.getType()== Hardware.Type.GPU) {
            vrHardver.setPrice(vrHardver.getPrice()*2);
        }
        return vrHardver;
    }

    private HardwareDTO mapHardwareToDto(Hardware hw) {
        return new HardwareDTO(hw.getName(), hw.getPrice());
    }


}
