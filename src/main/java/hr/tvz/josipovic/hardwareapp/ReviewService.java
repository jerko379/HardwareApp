package hr.tvz.josipovic.hardwareapp;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewService


{

    private final HardwareRepo repo;

    public ReviewService(HardwareRepo repo) {
        this.repo = repo;
    }


    public List<Review> getAllReviews() {
        return repo.getReviews();
    }

}
