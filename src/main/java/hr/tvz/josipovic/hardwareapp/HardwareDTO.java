package hr.tvz.josipovic.hardwareapp;


import lombok.Data;

@Data
public class HardwareDTO {


    private String name;
    private Double price;

    public HardwareDTO(String name, Double price) {
        this.name = name;
        this.price = price;
    }







}
