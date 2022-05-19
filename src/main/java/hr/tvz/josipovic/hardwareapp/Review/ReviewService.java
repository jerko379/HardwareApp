package hr.tvz.josipovic.hardwareapp.Review;

import hr.tvz.josipovic.hardwareapp.Hardware.HardwareRepo;
import hr.tvz.josipovic.hardwareapp.Review.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewService


{

    private final ReviewJpaRepo repo;

    public ReviewService(ReviewJpaRepo repo) {
        this.repo = repo;
    }


    public List<ReviewDTO> getAllReviews() {
        return repo.findAll().stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    public List<ReviewDTO> getAllbyHardwareCode(String code) {
        return repo.findAllByHardware_code(code).stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    public List<ReviewDTO> getByRating(Integer from, Integer to) {
        return repo.findByRatingGreaterThanEqualAndRatingLessThanEqual(from, to).stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }




    private ReviewDTO mapCourseToDTO(Review review){
        return new ReviewDTO(review.getHardware().getCode(),review.getTitle(), review.getText() ,review.getRating());
    }
}
