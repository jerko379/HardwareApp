package hr.tvz.josipovic.hardwareapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Optional<HardwareDTO> findbyCode(String code) {
        return repo.findByCode(code).map(this::mapHardwareToDto);
    }

    private HardwareDTO mapHardwareToDto(Hardware hw) {
        return new HardwareDTO(hw.getName(), hw.getPrice());
    }
    @Override

    public Optional<HardwareDTO> insert(HardwareCommand cmd) {
        return repo.insert(new Hardware(cmd.getName(), cmd.getPrice(), cmd.getCode(), cmd.getType(), cmd.getQuantity())).map(this::mapHardwareToDto);
    }

    @Override
    public void deleteByCode(String code) {
        repo.deleteByCode(code);
    }
}
