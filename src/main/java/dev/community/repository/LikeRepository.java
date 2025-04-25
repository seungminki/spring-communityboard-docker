package dev.community.repository;

import dev.community.entity.Board;
import dev.community.entity.Member;
import dev.community.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

	Long countByBoard(Board board);
	Optional<Like> findByMemberAndBoard(Member member, Board board);


}
