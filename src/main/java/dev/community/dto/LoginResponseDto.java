package dev.community.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDto (
	String email
){ }
