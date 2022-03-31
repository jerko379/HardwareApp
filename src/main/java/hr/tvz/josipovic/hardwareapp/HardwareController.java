package hr.tvz.josipovic.hardwareapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hardware")
public class HardwareController {

    private final HardwareServ hardwareService;

    public HardwareController(HardwareServ hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAlHardware(){
        return hardwareService.findAll();
    }

    @GetMapping(params = "code")
    public HardwareDTO getStudentByJMBAG(@RequestParam final String code){
        return hardwareService.findbyCode(code);
    }

}
