package pl.pawellewarski.Kwiaty.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDTO {

    private Long id;
    private String comment;
    private Date added;





}
