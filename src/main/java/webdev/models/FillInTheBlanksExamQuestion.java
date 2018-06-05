package webdev.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class FillInTheBlanksExamQuestion extends BaseExamQuestion{

    @Getter
    @Setter
    private String variables;
}
