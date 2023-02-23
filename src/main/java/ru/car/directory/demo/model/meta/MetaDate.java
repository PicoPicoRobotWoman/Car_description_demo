package ru.car.directory.demo.model.meta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Embeddable
public class MetaDate implements Cloneable {

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    private MetaDate(LocalDateTime dateCreation, LocalDateTime dateUpdate) {
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
    }

    @Override
    public MetaDate clone() {
        return new MetaDate(this.dateCreation, this.dateUpdate);
    }
}
