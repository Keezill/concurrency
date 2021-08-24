package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Prize {
    private String year;
    private String category;
    private int share;
}
