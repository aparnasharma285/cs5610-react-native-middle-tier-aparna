package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;


    @Getter
    @Setter
    int points = 0;

    @ManyToOne
    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String widgetType;

    @Getter
    @Setter
    String description;
    @JsonIgnore
    @Getter@Setter
    private Topic topic;

}
