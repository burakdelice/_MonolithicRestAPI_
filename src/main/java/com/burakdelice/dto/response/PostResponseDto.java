package com.burakdelice.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {
    Long id;
    private String title;
    private String content;
    private LocalDate publishedAt;
    private Long userId;
    private Long categoryId;
    private String comment;
    private Integer likes;
}
