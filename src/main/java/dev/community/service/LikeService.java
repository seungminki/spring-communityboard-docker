package dev.community.service;

import dev.community.ErrorMessage;
import dev.community.entity.Like;
import dev.community.entity.Member;
import dev.community.repository.BoardRepository;
import dev.community.entity.Board;
import dev.community.repository.LikeRepository;
import dev.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

	private final LikeRepository likeRepository;
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;


	public Like createLike(String memberName, Long boardId) {
		Member member = memberRepository.findByName(memberName)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));

		Like like = Like.builder()
			.member(member)
			.board(board)
			.build();

		return likeRepository.save(like);
	}



	public void deleteLike(String memberName, Long boardId) {
		Member member = memberRepository.findByName(memberName)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MEMBER_ID.getMessage()));
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));

		Like like = likeRepository.findByMemberAndBoard(member, board)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));
		// TODO: 좋아요를 누른 적이 없습니다

		likeRepository.delete(like);
	}

	public long countLikes(Long boardId) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BOARD_ID.getMessage()));

		return likeRepository.countByBoard(board);
	}
}

