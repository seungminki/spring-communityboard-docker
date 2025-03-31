package dev.community.board;

import dev.community.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository repository;

	public Board createBoard(Board board) {
		Board newBoard = Board.builder().
			title(board.getTitle())
			.content(board.getContent())
			.member(board.getMember())
			.build();

		return repository.save(newBoard);
	}

	public List<Board> getBoards() {
		return repository.findAll();
	}

	public Board getSingleBoard(Long boardId) {
		return repository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));
	}

	public Board updateBoard(Long boardId, Board newBoard) {
		Board oldBoard = repository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));

		Board board = Board.builder().title(newBoard.getTitle()).content(newBoard.getContent())
				.member(oldBoard.getMember()).build();

		return repository.save(board);
	}

	public void deleteBoard(Long boardId) {
		Board board = repository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));
		repository.delete(board);
	}

}
