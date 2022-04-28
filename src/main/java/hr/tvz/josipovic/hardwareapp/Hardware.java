package hr.tvz.josipovic.hardwareapp;
import lombok.Data;



@Data
public class Hardware {

    private Integer id;
    private String name;
    private Double price;
    private String code;
    private Type type;
    private Integer quantity;

    public Hardware( String name, Double price, String code, Type type, Integer quantity) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.type = type;
        this.quantity = quantity;
    }
}
