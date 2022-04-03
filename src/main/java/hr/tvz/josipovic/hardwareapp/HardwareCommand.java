package hr.tvz.josipovic.hardwareapp;

import lombok.Data;
import org.springframework.util.Assert;

import javax.validation.constraints.*;
import java.util.Arrays;

@Data
public class HardwareCommand {

    @NotBlank(message = "Name must be entered")
    private String name;


    @NotNull(message = "Price must be entered")
    @Positive(message = "Price must be positive")
    private Double price;


    @NotBlank(message = "Code must be entered")
    @Size(message = "Code must be exactly 8 characters long", min = 8, max = 8)
    @Pattern(message="First character of code must be first letter of the maker", regexp = "^[a-zA-Z].*")
    private String code;


    @NotNull(message = "Type must be entered")
    private Hardware.Type type;


    @NotNull(message = "Quantity must be entered")
    @PositiveOrZero(message = "Quantity must not be negative")
    private Integer quantity;


}
