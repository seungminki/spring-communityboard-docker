package dev.community.board.service;

import dev.community.board.controller.BoardCreateRequestDto;
import dev.community.board.controller.BoardUpdateRequestDto;
import dev.community.board.entity.Board;
import dev.community.board.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardCommandService {

	private final BoardJpaRepository boardJpaRepository;

	public void create(BoardCreateRequestDto request) {
		Board board = Board.builder()
			.title(request.title())
			.content(request.content())
			.build();
		boardJpaRepository.save(board);
	}

	public void update(Long id, BoardUpdateRequestDto request) {

	}

	public void delete(Long boardId) {
		boardJpaRepository.deleteById(boardId);
	}

	private void validateAccess(Long postId, Long memberId) {

	}

}
