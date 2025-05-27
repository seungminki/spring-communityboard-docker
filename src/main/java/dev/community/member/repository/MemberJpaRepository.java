package dev.community.member.repository;

import dev.community.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

	boolean existsByEmail(String email);

	boolean existsByName(String name);

	Member findByEmail(String email);
}

