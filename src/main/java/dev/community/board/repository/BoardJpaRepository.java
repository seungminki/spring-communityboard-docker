package dev.community.board.repository;

import dev.community.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, Long> {

	boolean existsById(Long id);

	Board getMemberEmailById(Long id);

}
