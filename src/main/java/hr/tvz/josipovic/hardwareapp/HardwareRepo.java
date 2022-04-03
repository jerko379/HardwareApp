package hr.tvz.josipovic.hardwareapp;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HardwareRepo implements HardwareRepository {


    private final List<Hardware> hardwareList = new ArrayList<>(List.of(new Hardware[]{
            new Hardware("Nvidia GTX 3080", 2000.0, "NGTX3080", Hardware.Type.GPU, 6),
            new Hardware("Nvidia GTX 2070", 2000.0, "NGTX2070", Hardware.Type.GPU, 11),
            new Hardware("Intel i5 7300HQ", 2000.0, "I57300HQ", Hardware.Type.CPU, 3)
    }));


    @Override
    public List<Hardware> findAll() {
        return hardwareList;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return hardwareList.stream().filter(hw -> Objects.equals(hw.getCode(), code)).findAny();
    }

    @Override
    public Optional<Hardware> insert (Hardware hw) {
        if(hardwareList.contains(hw)) {
            return Optional.empty();
        }
        else {
            hardwareList.add(hw);
            return Optional.of(hw);
        }
    }

    @Override
    public void deleteByCode(String code) {
        hardwareList.removeIf(hw -> Objects.equals(hw.getCode(), code));
        System.out.println(code);
    }
}