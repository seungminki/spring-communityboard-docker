package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.entity.Board;
import dev.community.entity.Comment;
import dev.community.entity.Member;
import dev.community.repository.BoardRepository;
import dev.community.repository.CommentRepository;
import dev.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;

	public Comment createComment(String memberEmail, Long boardId, Comment comment) {
		Member memberEntity = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));

		Board persistedBoard = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));

		Comment newComment = Comment.builder()
			.content(comment.getContent())
			.member(memberEntity)
			.board(persistedBoard)
			.build();
		return commentRepository.save(newComment);
	}

	public List<Comment> getComments() {
		return commentRepository.findAll();
	}

	public List<Comment> getCommentsByMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));

		return commentRepository.findAllByMember(member);
	}

	public List<Comment> getCommentsByBoard(Long boardId) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));

		return commentRepository.findAllByBoard(board);
	}

	public List<Comment> getCommentsByMemberAndBoard(Long memberId, Long boardId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));

		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));

		return commentRepository.findAllByMemberAndBoard(member, board);
	}

	public Comment getSingleComment(Long commentId) {
		return commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMENT_ID.getMessage()));
	}

	public Comment updateComment(String memberEmail, Long commentId, String newContent) {
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_TOKEN.getMessage()));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMENT_ID.getMessage()));

		if (!comment.getMember().equals(member)) {
			throw new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage());
		}

		comment.setContent(newContent);

		return commentRepository.save(comment);
	}

	public void deleteComment(String memberEmail, Long commentId) {
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_TOKEN.getMessage()));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMENT_ID.getMessage()));


		if (!comment.getMember().equals(member)) {
			throw new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage());
		}

		commentRepository.delete(comment);
	}

}
