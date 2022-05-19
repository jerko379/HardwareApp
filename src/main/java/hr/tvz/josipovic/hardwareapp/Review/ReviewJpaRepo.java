package hr.tvz.josipovic.hardwareapp.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface ReviewJpaRepo extends JpaRepository<Review, Long> {

    //List<Review> findAll();
    List<Review> findAllByHardware_code(String code);

    List<Review> findByRatingGreaterThanEqualAndRatingLessThanEqual(Integer from, Integer to);
}
