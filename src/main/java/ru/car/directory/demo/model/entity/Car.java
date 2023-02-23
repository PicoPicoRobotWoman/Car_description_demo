package ru.car.directory.demo.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import ru.car.directory.demo.model.meta.MetaDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "car")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_car", discriminatorType = DiscriminatorType.STRING)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
    @GenericGenerator(
        name = "task_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "task_sequence"),
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "register_number", unique = true)
    private String registerNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "issue")
    private Integer issue;

    @Embedded
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private MetaDate metaDate = new MetaDate();

    @PrePersist
    private void setCreateDate() {
        LocalDateTime now = LocalDateTime.now();
        metaDate.setDateCreation(now);
        metaDate.setDateUpdate(now);
    }

    @PreUpdate
    private void setDateUpdate() {
        LocalDateTime now = LocalDateTime.now();
        metaDate.setDateUpdate(now);
    }

    public MetaDate getMetaDate() {
        return metaDate.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
