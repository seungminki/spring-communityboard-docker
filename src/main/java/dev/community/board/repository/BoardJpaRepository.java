package dev.community.board.repository;

import dev.community.board.entity.Board;
import dev.community.board.entity.BoardResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, Long> {

	BoardResponseDto getBoardDto(Long boardId);
	List<BoardResponseDto> findAllBoardsDto();

}
