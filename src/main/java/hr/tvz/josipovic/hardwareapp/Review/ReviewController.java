package hr.tvz.josipovic.hardwareapp.Review;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {


    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_UPDATER"})
    @GetMapping
    public List<ReviewDTO> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_UPDATER"})
    @GetMapping(params = "code")
    public List<ReviewDTO> getAllReviewsByHardwareCode(@RequestParam String code){
        return reviewService.getAllbyHardwareCode(code);
    }
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_UPDATER"})
    @GetMapping(params = "fromto")
    public List<ReviewDTO> getAllbyRating(@RequestParam String fromto){
        Integer from = Integer.parseInt(String.valueOf(fromto.charAt(0)));
        Integer to = Integer.parseInt(String.valueOf(fromto.charAt(2)));
        return reviewService.getByRating(from, to);
    }







}
