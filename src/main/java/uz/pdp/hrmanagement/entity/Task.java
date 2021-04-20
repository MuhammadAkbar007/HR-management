package uz.pdp.hrmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.hrmanagement.entity.template.AbsEntity;
import uz.pdp.hrmanagement.enums.TaskStatus;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private Timestamp deadline;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.STATUS_NEW;

    @ManyToOne(optional = false)
    private User taskTaker;

    @ManyToOne(optional = false)
    private User taskGiver;
}
