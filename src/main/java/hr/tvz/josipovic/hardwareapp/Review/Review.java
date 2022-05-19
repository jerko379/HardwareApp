package hr.tvz.josipovic.hardwareapp.Review;


import hr.tvz.josipovic.hardwareapp.Hardware.Hardware;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Review {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String title;

    @Column(name = "content")
    private String text;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name="hardware_Id")
    private Hardware hardware;

}
