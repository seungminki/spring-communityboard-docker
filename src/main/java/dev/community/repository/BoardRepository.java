package dev.community.repository;

import dev.community.entity.Board;
import dev.community.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findAllByMember(Member member);
	Page<Board> findAll(Pageable pageable);

}
