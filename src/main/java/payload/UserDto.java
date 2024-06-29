package payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private long id;
    public String name;
    public String email;
    public String password;


}
