package hr.tvz.josipovic.hardwareapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
public class HardwareController {

    private final HardwareServ hardwareService;

    public HardwareController(HardwareServ hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAlHardware() {
        return hardwareService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable final String code) {
        return hardwareService.findbyCode(code).map(
                hardwareDTO -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO)
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()

        );
    }


    @PostMapping
    public ResponseEntity<HardwareDTO> insertHardware(@Valid @RequestBody final HardwareCommand cmd) {
        return hardwareService.insert(cmd)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        hardwareService.deleteByCode(code);
    }

}

