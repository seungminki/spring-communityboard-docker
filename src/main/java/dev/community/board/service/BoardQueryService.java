package dev.community.board.service;

import dev.community.board.mapper.BoardMapper;
import dev.community.board.repository.BoardJpaRepository;
import dev.community.board.service.dto.BoardServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardQueryService {

	private final BoardJpaRepository boardJpaRepository;
	private final BoardMapper boardMapper;

	public BoardServiceResponse getBoard(Long boardId) {
		return boardMapper.toDto(boardJpaRepository.findById(boardId));
	}

	public List<BoardServiceResponse> findAllBoards() {
		return boardMapper.toDtoList(boardJpaRepository.findAll());
	}
}
