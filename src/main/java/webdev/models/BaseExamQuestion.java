package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class BaseExamQuestion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int points;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String instructions;


    @Getter
    @Setter
    String typeOfQuestion;

    @ManyToOne
    @JsonIgnore
    @Getter
    @Setter
    private Exam exam;


}
