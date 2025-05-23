package dev.community.board.service;

import dev.community.board.entity.BoardResponseDto;
import dev.community.board.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardQueryService {

	private final BoardJpaRepository boardJpaRepository;

	public BoardResponseDto getBoard(Long boardId) {
		return boardJpaRepository.getBoardDto(boardId);
	}

	public List<BoardResponseDto> findAllBoards() {
		return boardJpaRepository.findAllBoardsDto();
	}
}
