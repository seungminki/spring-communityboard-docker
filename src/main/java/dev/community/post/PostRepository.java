package dev.community.post;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

	@Transactional(readOnly = true)
	@Cacheable("posts")
	List<Post> findAll() throws DataAccessException;

	@Transactional(readOnly = true)
	@Cacheable("posts")
	Page<Post> findAll(Pageable pageable) throws DataAccessException;

}
