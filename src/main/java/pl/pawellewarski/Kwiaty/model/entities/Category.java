package pl.pawellewarski.Kwiaty.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "category")
public class Category {


    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String categoryName;

    private String categoryImg;

    @NotBlank
    @Size(min = 3, max = 500)
    private String categoryDescription;

    @Embedded
    private AuditEntity auditEntity = new AuditEntity();

    public Category(@NotBlank @Size(min = 3, max = 50) String categoryName, String categoryImg, @NotBlank @Size(min = 3, max = 500) String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryImg = categoryImg;
        this.categoryDescription = categoryDescription;
    }
}
