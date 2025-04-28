package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.dto.CommentRequestDto;
import dev.community.dto.CommentResponseDto;
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
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;

	public CommentResponseDto createComment(String memberEmail, Long boardId, CommentRequestDto commentRequestDto) {
		Member member = validateMemberEmail(memberEmail);
		Board board = validateBoardId(boardId);

		Comment newComment = Comment.builder()
			.content(commentRequestDto.content())
			.member(member)
			.board(board)
			.build();

		Comment savedComment = commentRepository.save(newComment);
		return new CommentResponseDto(savedComment);
	}

	public List<CommentResponseDto> getComments() {
		return commentRepository.findAll().stream()
			.map(CommentResponseDto::new)
			.collect(Collectors.toList());
	}

	public List<CommentResponseDto> getCommentsByMember(String memberEmail) {
		Member member = validateMemberEmail(memberEmail);
		return commentRepository.findAllByMember(member).stream()
			.map(CommentResponseDto::new)
			.collect(Collectors.toList());
	}

	public List<CommentResponseDto> getCommentsByBoard(Long boardId) {
		Board board = validateBoardId(boardId);
		return commentRepository.findAllByBoard(board).stream()
			.map(CommentResponseDto::new)
			.collect(Collectors.toList());
	}

	public CommentResponseDto getSingleComment(Long commentId) {
		Comment comment = validateCommentId(commentId);
		return new CommentResponseDto(comment);
	}

	public CommentResponseDto updateComment(String memberEmail, Long commentId, CommentRequestDto commentRequestDto) {
		Member member = validateMemberEmail(memberEmail);
		Comment comment = validateCommentId(commentId);

		if (!comment.getMember().equals(member)) {
			throw new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage());
		}

		Comment newComment = Comment.builder()
			.content(commentRequestDto.content())
			.member(comment.getMember())
			.board(comment.getBoard())
			.build();

		Comment savedComment = commentRepository.save(newComment);

		return new CommentResponseDto(savedComment);
	}

	public void deleteComment(String memberEmail, Long commentId) {
		Member member = validateMemberEmail(memberEmail);
		Comment comment = validateCommentId(commentId);

		if (!comment.getMember().equals(member)) {
			throw new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage());
		}

		commentRepository.delete(comment);
	}

	private Member validateMemberEmail(String memberEmail) {
		return memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));
	}

	private Board validateBoardId(Long boardId) {
		return boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));
	}

	private Comment validateCommentId(Long commentId) {
		return commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COMMENT_ID.getMessage()));
	}

}
