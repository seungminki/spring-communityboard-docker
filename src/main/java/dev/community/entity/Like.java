package dev.community.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
@Getter
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;

	@CreatedDate
	private LocalDateTime likedAt;

	@Builder
	public Like(Member member, Board board) {
		this.member = member;
		this.board = board;
		this.likedAt = LocalDateTime.now();
	}

}
