package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.entity.Member;
import dev.community.repository.BoardRepository;
import dev.community.entity.Board;
import dev.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;

	public Board createBoard(String memberEmail, Board board) {
		Member memberEntity = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));

		Board newBoard = Board.builder()
			.title(board.getTitle())
			.content(board.getContent())
			.member(memberEntity)
			.build();

		return boardRepository.save(newBoard);
	}

	public List<Board> getBoards() {
		return boardRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Page<BoardResponseDto> getBoards(int page, int size, String sortBy, boolean asc) {
		Sort.Direction direction = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
		return boardRepository.findAll(pageable).map(BoardResponseDto::new);
	}

	@Transactional(readOnly = true)
	public BoardResponseDto getBoardInfo(Long boardId) {
		Board board = validateBoardId(boardId);
		return new BoardResponseDto(board);
	}

	@Transactional(readOnly = true)
	public BoardResponseDto getSingleBoard(Long boardId) {
		Board board = validateBoardId(boardId);

		BoardService proxy = context.getBean(BoardService.class);
		proxy.increaseViewCount(board);

		return new BoardResponseDto(board);

	}

	public List<Board> getBoardsByMember(Long memberId) {
		Member memberEntity = memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));

		return boardRepository.findAllByMember(memberEntity);

	}

	public Board updateBoard(String memberEmail, Long boardId, String newTitle, String newContent) {
		Member memberEntity = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));

		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));

		if (!board.getMember().getId().equals(memberEntity.getId())) {
			throw new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage());
		}

		board.setTitle(newTitle);
		board.setContent(newContent);

		return boardRepository.save(board);
	}

	public void deleteBoard(String memberEmail, Long boardId) {
		Member memberEntity = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_TOKEN.getMessage()));

		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));

		if (!board.getMember().getId().equals(memberEntity.getId())) {
			throw new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage());
		}

		boardRepository.delete(board);
	}

	@Transactional
	protected void increaseViewCount(Board board) {

		Board newboard = Board.builder()
			.id(board.getId())
			.title(board.getTitle())
			.content(board.getContent())
			.member(board.getMember())
			.viewCount(board.getViewCount() + 1)
			.version(board.getVersion())
			.build();

		boardRepository.saveAndFlush(newboard);
	}

	private Member validateMemberEmail(String memberEmail) {
		return memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));
	}

	private Board validateBoardId(Long boardId) {
		return boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));
	}
}
