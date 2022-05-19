package hr.tvz.josipovic.hardwareapp.Hardware;

import hr.tvz.josipovic.hardwareapp.Type;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class HardwareCommand {


    @NotBlank(message = "Name must be entered")
    private String name;


    @NotNull(message = "Price must be entered")
    @Positive(message = "Price must be positive")
    private Double price;


    @NotBlank(message = "Code must be entered")
    @Size(message = "Code must be exactly 8 characters long", min = 8, max = 8)
    @Pattern(message = "First character of code must be first letter of the maker in caps", regexp = "^[A-Z].*")
    private String code;

    @NotNull(message = "Type must be entered")
    private Type type;


    @NotNull(message = "Stock must be entered")
    @PositiveOrZero(message = "Stock must not be negative")
    private Integer stock;


}
