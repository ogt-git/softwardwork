package com.practice.Studyspring.comment;

import com.practice.Studyspring.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostIdOrderByIdDesc(Long postId);

    Optional<Comment> findByIdAndPostId(Long CommentId, Long postId);

/*    @Query("SELECT c FROM Comment c JOIN FETCH c.post")
    List<Comment> findAll();*/

    @EntityGraph(attributePaths = "post", type = EntityGraph.EntityGraphType.LOAD)
    List<Comment> findAll();
}
