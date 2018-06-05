package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Exam extends Widget{
    
    @OneToMany(mappedBy = "exam")
    @JsonIgnore
    @Getter
    @Setter
    List<BaseExamQuestion> questions;
}
