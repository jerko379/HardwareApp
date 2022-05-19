package hr.tvz.josipovic.hardwareapp.Hardware;
import hr.tvz.josipovic.hardwareapp.Review.Review;
import hr.tvz.josipovic.hardwareapp.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Double price;
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip")
    private Type type;

    private Integer quantity;

    @OneToMany(mappedBy="hardware",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();




    public Hardware(Integer id, String name, Double price, String code, Type type, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.code = code;
        this.type = type;
        this.quantity = quantity;
    }

    public Hardware(String name, Double price, String code, Type type, Integer quantity) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.type = type;
        this.quantity = quantity;
    }
}
