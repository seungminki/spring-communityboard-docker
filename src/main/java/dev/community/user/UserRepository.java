package dev.community.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT user FROM User user WHERE user.name =:name")
	@Transactional(readOnly = true)
	User findByName(@Param("name") String name);

	@Query("SELECT user FROM User user WHERE user.email =:email")
	@Transactional(readOnly = true)
	User findByEmail(@Param("email") String email);

}
