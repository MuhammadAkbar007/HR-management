package uz.pdp.hrmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.hrmanagement.entity.template.AbsEntity;
import uz.pdp.hrmanagement.enums.Months;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalaryTaken extends AbsEntity {

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    private Months period;

    private boolean paid = false;
}
