package hr.tvz.josipovic.hardwareapp.Review;

import hr.tvz.josipovic.hardwareapp.Hardware.Hardware;
import lombok.Data;

@Data
public class ReviewDTO {

    private String hwCode;

    private String title;

    private String text;

    private Integer rating;


    public ReviewDTO(String title, String text, Integer rating) {
        this.title = title;
        this.text = text;
        this.rating = rating;
    }


    public ReviewDTO(String hwCode, String title, String text, Integer rating) {
        this.hwCode = hwCode;
        this.title = title;
        this.text = text;
        this.rating = rating;
    }
}
