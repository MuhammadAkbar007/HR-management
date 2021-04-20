package uz.pdp.hrmanagement.payload;

import lombok.Data;
import uz.pdp.hrmanagement.enums.Months;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class SalaryTakenDto {
    @Email
    @NotNull
    private String email;

    @NotNull
    private double amount;

    @Enumerated
    private Months period;
}
