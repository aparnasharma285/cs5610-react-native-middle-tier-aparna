package webdev.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends BaseExamQuestion {

    @Getter
    @Setter
    private Boolean isTrue = false;
}
