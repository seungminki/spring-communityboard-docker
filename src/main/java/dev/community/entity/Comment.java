package dev.community.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String content;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;

	@CreatedDate
	private LocalDateTime createdAt;

	@Builder
	public Comment(String content, Member member, Board board) {
		this.content = content;
		this.member = member;
		this.board = board;
		this.createdAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return this.content;
	}

	public Board getBoardId() {
		return this.board;
	}

	public Member getMemberId() {
		return this.member;
	}

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

}
