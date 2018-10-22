package pl.pawellewarski.Kwiaty.model.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(exclude = "password")
public class RegisterForm {

    @Size(min = 3, message = "Nazwa użytkowanika powinna mieć minimum 3 znaki")
    private String userName;

    private String email;

    @Size(min = 6, max = 70)
    private String password;


}
