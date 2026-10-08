package com.practice.Studyspring.comment;

import com.practice.Studyspring.post.Post;
import com.practice.Studyspring.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository ;

    public CommentResponse createComment(Long postId, CommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));
        Comment comment = Comment.builder()
                .content(request.content())
                .post(post)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Comment savedComment = commentRepository.save(comment);

        if (Objects.equals(postId, 20L))
            throw new RuntimeException("애플리케이션 오류 발생");

        post.increaseCommentCount();
        //postRepository.save(post);

        return CommentResponse.from(savedComment);
    }
    public List<CommentResponse> getComments(Long postId) {
        return commentRepository.findByPostIdOrderByIdDesc(postId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    public CommentResponse updateComment(Long postId, Long commentId ,CommentRequest comment)
    {
        Comment comment1 = commentRepository.findByIdAndPostId(commentId, postId).orElseThrow(()->new IllegalStateException("Comment not found"));
        comment1.setContent(comment.content());
        return CommentResponse.from(commentRepository.save(comment1));
    }

    public  void deleteComment(Long postId, Long commentId)
    {
        Comment comment = commentRepository.findByIdAndPostId(commentId,postId).orElseThrow(()->new IllegalStateException("Comment not found"));

        Post post = comment.getPost();
        post.decreaseCommentCount();
        postRepository.save(post);
        commentRepository.delete(comment);
    }


/*    public List<CommentResponse> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> {
                    Post post = comment.getPost();
                    log.info("댓글 ID: {}, 게시글 ID: {}, 게시글 내용: {}", comment.getId(),
                            post.getId(), post.getContent());
                    return CommentResponse.from(comment);
                })
                .toList();
    }*/


     /*// N+1 문제 해결방법1 : in 절 쿼리
    public List<CommentResponse> getAllComments() {
        // 1. 모든 댓글 조회
        List<Comment> comments = commentRepository.findAll();
        // 2. 댓글에 포함된 postId 추출
        Set<Long> postIds = comments.stream()
                .map(comment -> comment.getPost().getId())
                .collect(Collectors.toSet());
        // 3. postId 로 게시글을 한 번에 조회 (쿼리 1번)
        Map<Long, Post> postMap = postRepository.findAllById(postIds).stream()
                .collect(Collectors.toMap(Post::getId, Function.identity()));
        // 4. 댓글 응답 객체 생성 시 post 를 postMap 에서 찾아 사용
        return comments.stream()
                .map(comment -> {
                    Post post = postMap.get(comment.getPost().getId());
                    log.info("댓글 ID: {}, 게시글 ID: {}, 게시글 내용: {}", comment.getId(),
                            post.getId(), post.getContent());
                    return CommentResponse.from(comment);
                })
                .toList();
    }*/


    // N+1 문제 해결방법2 : fetch join
    public List<CommentResponse> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> {
                    Post post = comment.getPost();
                    log.info("댓글 ID: {}, 게시글 ID: {}, 게시글 내용: {}", comment.getId(),
                            post.getId(), post.getContent());
                    return CommentResponse.from(comment);
                })
                .toList();
    }

}
