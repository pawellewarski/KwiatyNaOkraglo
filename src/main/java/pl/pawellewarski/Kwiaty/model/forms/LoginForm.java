package pl.pawellewarski.Kwiaty.model.forms;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
public class LoginForm {

    private String userName;
    private String password;




}
