package dev.community.repository;

import dev.community.entity.Board;
import dev.community.entity.Member;
import dev.community.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

	Optional<Like> find(Member member, Board board);
	List<Like> findAllByMember(Member member);
	int countByBoard(Board board);


}
