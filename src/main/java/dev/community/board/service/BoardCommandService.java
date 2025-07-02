package dev.community.board.service;

import dev.community.ErrorMessage;
import dev.community.board.controller.dto.BoardCreateRequest;
import dev.community.board.controller.dto.BoardUpdateRequest;
import dev.community.board.entity.Board;
import dev.community.board.repository.BoardJpaRepository;
import dev.community.member.repository.MemberJpaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardCommandService {

	private final BoardJpaRepository boardJpaRepository;
	private final MemberJpaRepository memberJpaRepository;

	public Long create(Principal principal, @Valid BoardCreateRequest request) {
		memberJpaRepository.existsByEmail(principal.getName());

		Board board = Board.builder()
			.title(request.title())
			.content(request.content())
			.member(memberJpaRepository.findByEmail(principal.getName()))
			.build();

		return boardJpaRepository.save(board).getId();
	}

	@Transactional
	public Long update(Principal principal, Long boardId, BoardUpdateRequest request) {

		authorizedBoard(principal.getName(), boardId);

		Optional<Board> board = boardJpaRepository.findById(boardId);
		board.get().updateBoard(request.title(), request.content());
		return board.get().getId();
	}

	public void delete(Principal principal, Long boardId) {
		authorizedBoard(principal.getName(), boardId);
		boardJpaRepository.deleteById(boardId);
	}

	private void authorizedBoard(String memberEmail, Long boardId) {
		if (!memberJpaRepository.existsByEmail(memberEmail)) {
			throw new IllegalArgumentException(ErrorMessage.NOT_FOUND_MEMBER.getMessage());
		}

		if (!boardJpaRepository.existsById(boardId)) {
			throw new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage());
		}

		if (!boardJpaRepository.getMemberEmailById(boardId).getMember().equals(memberJpaRepository.findByEmail(memberEmail))) {
			throw new IllegalArgumentException(ErrorMessage.NOT_MATCHES_MEMBER.getMessage());
		}
	}

}
