package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.dto.CreateBoardRequestDto;
import dev.community.dto.BoardResponseDto;
import dev.community.entity.Member;
import dev.community.repository.BoardRepository;
import dev.community.entity.Board;
import dev.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

	private final ApplicationContext context;

	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;

	public BoardResponseDto createBoard(String memberEmail, CreateBoardRequestDto createBoardRequestDto) {
		Member member = validateMemberEmail(memberEmail);

		Board newBoard = Board.builder()
			.title(createBoardRequestDto.title())
			.content(createBoardRequestDto.content())
			.member(member)
			.build();

		Board savedBoard = boardRepository.save(newBoard);

		return new BoardResponseDto(savedBoard);
	}

	@Transactional(readOnly = true)
	public List<BoardResponseDto> getBoards() {
		return boardRepository.findAll().stream()
			.map(BoardResponseDto::new)
			.collect(Collectors.toList());
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

	@Transactional(readOnly = true)
	public List<BoardResponseDto> getBoardsByMember(String memberEmail) {
		Member member = validateMemberEmail(memberEmail);
		return boardRepository.findAllByMember(member).stream()
			.map(BoardResponseDto::new)
			.collect(Collectors.toList());

	}

	public BoardResponseDto updateBoard(String memberEmail, Long boardId, CreateBoardRequestDto createBoardRequestDto) {
		Member member = validateMemberEmail(memberEmail);
		Board board = validateBoardId(boardId);

		if (!board.getMember().getId().equals(member.getId())) {
			throw new IllegalArgumentException("");
		}

		Board newBoard = Board.builder()
			.title(createBoardRequestDto.title())
			.content(createBoardRequestDto.content())
			.member(member)
			.build();

		Board savedBoard = boardRepository.save(newBoard);

		return new BoardResponseDto(savedBoard);
	}

	public void deleteBoard(String memberEmail, Long boardId) {
		Member member = validateMemberEmail(memberEmail);
		Board board = validateBoardId(boardId);

		if (!board.getMember().getId().equals(member.getId())) {
			throw new IllegalArgumentException("");
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
