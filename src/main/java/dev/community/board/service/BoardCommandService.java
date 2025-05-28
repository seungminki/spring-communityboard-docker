package dev.community.board.service;

import dev.community.ErrorMessage;
import dev.community.board.controller.dto.BoardCreateRequest;
import dev.community.board.controller.dto.BoardUpdateRequest;
import dev.community.board.entity.Board;
import dev.community.board.repository.BoardJpaRepository;
import dev.community.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class BoardCommandService {

	private final BoardJpaRepository boardJpaRepository;
	private final MemberJpaRepository memberJpaRepository;

	public Long create(Principal principal, BoardCreateRequest request) {
		memberJpaRepository.existsByEmail(principal.getName());

		Board board = Board.builder()
			.title(request.title())
			.content(request.content())
			.member(memberJpaRepository.findByEmail(principal.getName()))
			.build();

		boardJpaRepository.save(board);

		return board.getId();
	}

	public Long update(Principal principal, Long boardId, BoardUpdateRequest request) {

		authorizedBoard(principal.getName(), boardId);

		Board board = boardJpaRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));
		board.updateBoard(request.title(), request.content());
		boardJpaRepository.save(board);
		return board.getId();
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
