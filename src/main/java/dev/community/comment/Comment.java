package dev.community.comment;

import dev.community.board.Board;
import dev.community.member.Member;
import dev.community.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "comments")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

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
