package dev.community.repository;

import dev.community.entity.Board;
import dev.community.entity.Comment;
import dev.community.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findAllByMember(Member member);
	List<Comment> findAllByBoard(Board board);
	List<Comment> findAllByMemberAndBoard(Member member, Board board);


}
