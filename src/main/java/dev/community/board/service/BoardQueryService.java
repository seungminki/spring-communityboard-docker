package dev.community.board.service;

import dev.community.board.controller.BoardMapper;
import dev.community.board.entity.BoardResponseDto;
import dev.community.board.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardQueryService {

	private final BoardJpaRepository boardJpaRepository;
	private final BoardMapper boardMapper;

	public BoardResponseDto getBoard(Long boardId) {
		return boardMapper.toDto(boardJpaRepository.findById(boardId));
	}

	public List<BoardResponseDto> findAllBoards() {
		return boardMapper.toDtoList(boardJpaRepository.findAll());
	}
}
