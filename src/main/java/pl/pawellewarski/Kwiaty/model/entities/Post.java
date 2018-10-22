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
public class Post {

    // Id, title, zawartość
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 15)
    private String postTitle;


    @NotBlank
    @Size(min = 1, max = 256)
    private String postContent;

    @Embedded
    private AuditEntity auditEntity = new AuditEntity();


    @OneToMany(mappedBy = "post", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<PostComment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "userId")
    @Getter
    @Setter
    private User user;


    public Post(@NotBlank @Size(min = 3, max = 15) String postTitle, @NotBlank @Size(min = 1, max = 256) String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public void addComment(PostComment postComment) {
        comments.add(postComment);
        postComment.setPost(this);
    }
}
