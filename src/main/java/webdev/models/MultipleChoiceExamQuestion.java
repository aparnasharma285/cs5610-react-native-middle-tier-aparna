package webdev.models;

import lombok.Getter;
import lombok.Setter;

public class MultipleChoiceExamQuestion extends BaseExamQuestion {

    @Getter
    @Setter
    private String options;


    @Getter
    @Setter
    private int correctOption;

}
