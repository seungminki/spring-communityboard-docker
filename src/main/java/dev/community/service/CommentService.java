package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.entity.Comment;
import dev.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository repository;

	public Comment createComment(Comment comment) {
		Comment newComment = Comment.builder()
			.content(comment.getContent())
			.member(comment.getMemberId())
			.board(comment.getBoardId())
			.build();

		return this.repository.save(newComment);
	}

	public List<Comment> getComments() {
		return repository.findAll();
	}

	public Comment getSingleComment(Long commentId) {
		return repository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMENT_ID.getMessage()));
	}

	public Comment updateComment(Long commentId, Comment newComment) {
		Comment oldComment = repository.findById(commentId)
				.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMENT_ID.getMessage()));

		Comment comment = Comment.builder().content(newComment.getContent()).member(oldComment.getMemberId())
				.board(oldComment.getBoardId()).build();

		return repository.save(comment);
	}

	public void deleteComment(Long commentId) {
		Comment comment = repository.findById(commentId)
				.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMENT_ID.getMessage()));
		repository.delete(comment);
	}

}
