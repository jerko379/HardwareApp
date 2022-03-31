package hr.tvz.josipovic.hardwareapp;
import lombok.Data;



@Data
public class Hardware {

    public  enum Type {
        CPU, GPU, MBO, RAM, STORAGE, OTHER
    }
    private String name;
    private Double price;
    private String code;
    private Type type;


    private int quantity;

    public Hardware(String name, Double price, String code, Type type, int quantity) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.type = type;
        this.quantity = quantity;
    }
}
