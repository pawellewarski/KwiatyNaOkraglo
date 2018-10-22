package pl.pawellewarski.Kwiaty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pawellewarski.Kwiaty.model.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



}
