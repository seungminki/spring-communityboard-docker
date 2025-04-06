package dev.community.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;
	private int reviewCount;
	private int likeCount;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@CreatedDate
	private LocalDateTime createdAt;

	@Builder
	public Board(String title, String content, Member member) {
		this.title = title;
		this.content = content;
		this.reviewCount = 0;
		this.likeCount = 0;
		this.member = member;
		this.createdAt = LocalDateTime.now();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public Member getMember() {
		return member;
	}

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

}
