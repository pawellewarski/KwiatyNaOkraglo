package pl.pawellewarski.Kwiaty.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String userName;
    private String email;



}
