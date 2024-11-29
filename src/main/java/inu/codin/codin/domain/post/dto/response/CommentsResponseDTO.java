package inu.codin.codin.domain.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CommentsResponseDTO {
    @Schema(description = "댓글 또는 대댓글 ID", example = "111111")
    @NotBlank
    private String commentId;

    @Schema(description = "유저 ID", example = "user123")
    @NotBlank
    private String userId;

    @Schema(description = "댓글 또는 대댓글 내용", example = "This is a comment.")
    @NotBlank
    private String content;

    @Schema(description = "대댓글 리스트", example = "[...]")
    private List<CommentsResponseDTO> replies;

    @Schema(description = "좋아요 수", example = "5")
    private int likeCount;

    public CommentsResponseDTO(String commentId, String userId, String content, List<CommentsResponseDTO> replies, int likeCount) {
        this.commentId = commentId;
        this.userId = userId;
        this.content = content;
        this.replies = replies;
        this.likeCount = likeCount;
    }
}