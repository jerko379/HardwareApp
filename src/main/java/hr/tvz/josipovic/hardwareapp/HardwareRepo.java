package hr.tvz.josipovic.hardwareapp;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class HardwareRepo implements HardwareRepository {

    private final List<Hardware> hardwareList = List.of(new Hardware[]{
            new Hardware("Nvidia RTX 3080", 4000.0, "NRTX3080", Hardware.Type.GPU, 3),
            new Hardware("Nvidia RTX 2070", 6000.0, "NRTX2070", Hardware.Type.GPU, 2),
            new Hardware("Intel i5 7300HQ", 3000.0, "I57300HQ", Hardware.Type.CPU, 2),
    });

    @Override
    public List<Hardware> findAll() {
        return hardwareList;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return hardwareList.stream().filter(hw -> Objects.equals(hw.getCode(), code)).findAny();
    }
}