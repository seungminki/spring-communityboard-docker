package dev.community.board.mapper;

import dev.community.board.entity.Board;
import dev.community.board.service.dto.BoardServiceResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BoardMapper {
	BoardServiceResponse toDto(Optional<Board> board);

	List<BoardServiceResponse> toDtoList(List<Board> boards);
}
