package dev.community.board.controller;

import dev.community.board.entity.Board;
import dev.community.board.entity.BoardResponseDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BoardMapper {
	BoardResponseDto toDto(Optional<Board> board);

	List<BoardResponseDto> toDtoList(List<Board> boards);
}
