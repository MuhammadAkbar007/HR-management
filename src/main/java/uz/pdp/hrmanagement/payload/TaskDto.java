package uz.pdp.hrmanagement.payload;

import lombok.Data;
import uz.pdp.hrmanagement.enums.TaskStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class TaskDto {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Timestamp deadline;

    @Email
    @NotNull
    private String userEmail;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;
}
