package webdev.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class Assignment extends Widget {

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    String answer;

    @Getter
    @Setter
    String fileLink;

    @Getter
    @Setter
    String link;
}
