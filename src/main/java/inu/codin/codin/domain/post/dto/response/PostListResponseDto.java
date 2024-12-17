package inu.codin.codin.domain.post.dto.response;

import inu.codin.codin.domain.post.entity.PostCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostListResponseDto extends PostDetailResponseDTO{

    @Schema(description = "댓글 및 대댓글 count", example = "0")
    private final int commentCount;

    public PostListResponseDto(String userId, String postId, String content, String title, String nickname, PostCategory postCategory, List<String> postImageUrls , boolean isAnonymous, int commentCount, int likeCount, int scrapCount, LocalDateTime createdAt) {
        super(userId, postId, content, title,nickname,  postCategory, postImageUrls, isAnonymous, likeCount, scrapCount, createdAt);
        this.commentCount = commentCount;
    }
}