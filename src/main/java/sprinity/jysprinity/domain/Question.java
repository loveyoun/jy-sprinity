package sprinity.jysprinity.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {

    @Id @GeneratedValue
    private Long id;

    private String writer;
    private String title;
    private String contents;

    //jpa mapping 할 떄, default 생성자 필요하다
    public Question(){}
    public Question(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

}
