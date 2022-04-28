package hr.tvz.josipovic.hardwareapp;


import lombok.Data;

@Data
public class Review {

    private String naslov;
    private String text;
    private String hwCode;
    private Integer ocjena;

    public Review(String naslov, String text, String hwCode, Integer ocjena) {
        this.naslov = naslov;
        this.text = text;
        this.hwCode = hwCode;
        this.ocjena = ocjena;
    }
}
