package dev.community.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository repository;

	public Member createMember(Member member) {
		Member newMember = Member.builder().name(member.getName()).email(member.getEmail())
				.password(member.getPassword()).build();

		return repository.save(newMember);
	}

	public List<Member> getMembers() {
		return repository.findAll();
	}

	public Member getSingleMember(Long memberId) {
		return repository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다"));
	}

	public Member updateMember(Long memberId, Member newMember) {
		Member oldMember = repository.findById(memberId)
				.orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다"));

		Member member = Member.builder().name(newMember.getName()).email(oldMember.getEmail())
				.password(oldMember.getPassword()).build();

		return repository.save(member);
	}

	public void deleteMember(Long memberId) {
		Member member = repository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다"));
		repository.delete(member);
	}

}
