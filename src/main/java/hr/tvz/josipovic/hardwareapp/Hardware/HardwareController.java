package hr.tvz.josipovic.hardwareapp.Hardware;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {



    private final HardwareServ hardwareService;

    public HardwareController(HardwareServ hardwareService) {
        this.hardwareService = hardwareService;
    }


    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_UPDATER"})
    @GetMapping
    public List<HardwareDTO> getAlHardware() {
        System.out.println("GET");
        return hardwareService.findAll();

    }



    /*
    @GetMapping(params  = "str")
    public ResponseEntity<List<HardwareDTO>> getHardwarebyString(@RequestParam final String str) {
        return hardwareService.findByString(str).forEach(hardwareDTO
    }

     */

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_UPDATER"})
    @GetMapping("/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable final String code) {
        return hardwareService.findbyCode(code).map(
                hardwareDTO -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO)
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()

        );


    }




    @Secured({"ROLE_ADMIN"})
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HardwareDTO> insertHardware(@Valid @RequestBody  final HardwareCommand cmd) {
        return hardwareService.insert(cmd)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO)
                ).orElseGet(    
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );

    }


    @Secured({"ROLE_ADMIN", "ROLE_UPDATER"})
    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> updateHardware(@PathVariable String code, @Valid @RequestBody final HardwareCommand updateHardwareCommand){
        return hardwareService.update(code, updateHardwareCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @Secured({"ROLE_ADMIN", "ROLE_UPDATER"})
    @PutMapping(value = "/{code}", params = "quantity")
    public ResponseEntity<HardwareDTO> updateQuantity(@PathVariable String code, @RequestParam Integer quantity){
        return hardwareService.updateQuantity(code,quantity)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }


    @Secured({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        System.out.println("Doslooooooooooooooooooooooooooooooooooooooooooooooooo");
        hardwareService.deleteByCode(code);
    }

}

