package dev.community.board;

import dev.community.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	public List<Board> getAllBoards() {
		return boardRepository.findAll();
	}

	public Board getBoardById(Long id) {
		return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));
	}

	public Board saveBoard(BoardCreateRequestDto board) {

		Board boardEntity = Board.builder()
			.title(board.title())
			.content(board.content())
			.build();

		return boardRepository.save(boardEntity);
	}

	public Board updateBoard(Board board) {
		return boardRepository.save(board);
	}

	public void deleteBoard(Long id) {
		boardRepository.deleteById(id);
	}


}
