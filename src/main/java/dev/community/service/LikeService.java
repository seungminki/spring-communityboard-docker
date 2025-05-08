package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.dto.like.LikeResponseDto;
import dev.community.entity.Like;
import dev.community.entity.Member;
import dev.community.repository.BoardRepository;
import dev.community.entity.Board;
import dev.community.repository.LikeRepository;
import dev.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class LikeService {

	private final LikeRepository likeRepository;
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;


	public LikeResponseDto createLike(String memberEmail, Long boardId) {
		Member member = validateMemberEmail(memberEmail);
		Board board = validateBoardId(boardId);

		Like newLike = Like.builder()
			.member(member)
			.board(board)
			.build();

		Like savedLike = likeRepository.save(newLike);

		return new LikeResponseDto(savedLike);
	}

	public void deleteLike(String memberEmail, Long boardId) {
		Member member = validateMemberEmail(memberEmail);
		Board board = validateBoardId(boardId);

		Like like = likeRepository.find(member, board)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_LIKED_ID.getMessage()));

		likeRepository.delete(like);
	}

	public long countLikesByBoard(Long boardId) {
		Board board = validateBoardId(boardId);

		return likeRepository.countByBoard(board);
	}

	public List<LikeResponseDto> getMemberLikes(String memberEmail) {
		Member member = validateMemberEmail(memberEmail);

		return likeRepository.findAllByMember(member).stream()
			.map(LikeResponseDto::new)
			.collect(Collectors.toList());
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

