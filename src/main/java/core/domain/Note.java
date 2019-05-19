package core.domain;

import core.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.LocalDate;

import java.util.Date;

@Getter
@Setter
public class Note {
    private Long id;

    private String title;

    private String content;

    private Date createDate;

    @Override
    public String toString() {
        return "id: " + id
                + ", title: " + title
                + ", content: " + content
                + ", create date: " + DateUtils.formatDate(new LocalDate(createDate));
    }
}
