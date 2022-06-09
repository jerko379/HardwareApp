package hr.tvz.josipovic.hardwareapp.Hardware;

import hr.tvz.josipovic.hardwareapp.Review.Review;
import hr.tvz.josipovic.hardwareapp.Type;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository

public class HardwareRepo implements HardwareRepository {


    private final List<Hardware> hardwareList = new ArrayList<>(List.of(new Hardware[]{
            new Hardware(1,"Nvidia RTX 3080", 2000.0, "NRTX3080", Type.GPU, 6),
            new Hardware(2,"Nvidia RTX 2070", 3000.0, "NRTX2070", Type.GPU, 11),
            new Hardware(3,"Intel i5 7300HQ", 4000.0, "I57300HQ", Type.CPU, 3)
    }));





     /*final List<Review> reviewList = new ArrayList<>(List.of(new Review[]{
            new Review(0,"10/10 would recommend", "buy this very good gpu", 5),
            new Review(1,"2/10 would not recommend", "dont buy this bad gpu", 3),
            new Review(2,"0/10 cpu", "cpu for microwave",  2),
            new Review(3,"0.1/10 cpu", "cpu for toaster", 1),
    }));

      */



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
        if(hardwareList.stream().anyMatch( _hw  -> _hw.getCode().equals(hw.getCode()))) {
            System.out.println("isti kod");
            return Optional.empty();

        }
        else {
            System.out.println("dobro je");
            hardwareList.add(hw);
            return Optional.of(hw);
        }
    }

    @Override
    public Optional<Hardware> update(String code, Hardware updateHw) {
        return Optional.empty();
    }

    @Override
    public void deleteByCode(String code) {
        hardwareList.removeIf(hw -> Objects.equals(hw.getCode(), code));
    }

    @Override
    public Optional<Hardware> updateQuantity(String code, Integer quantity) {
        return Optional.empty();
    }

    public Optional<Hardware> updateQuantity(String code, int nQuantity) {
        Hardware hardware = hardwareList.stream().filter(hw -> hw.getCode().equals(code)).findFirst().orElse(null);
        if(hardware == null) {
            return Optional.empty();
        }
        else {
            hardwareList.removeIf(hw -> Objects.equals(hw.getCode(), code));
            hardware.setQuantity(nQuantity);
            hardwareList.add(hardware);
            return Optional.of(hardware);
        }
    }
}
