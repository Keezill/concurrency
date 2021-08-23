
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class Laureates implements Serializable {

    private Integer id;
    private String firstname;
    private String surname;
    private String born;
    private String died;
    private String bornCountry;
    private String bornCountryCode;
    private String bornCity;
    private String diedCountry;
    private String diedCountryCode;
    private String diedCity;
    private String gender;
}
