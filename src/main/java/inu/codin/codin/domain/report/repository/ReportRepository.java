package inu.codin.codin.domain.report.repository;

import inu.codin.codin.domain.report.entity.ReportEntity;
import inu.codin.codin.domain.report.entity.ReportTargetType;
import inu.codin.codin.domain.report.entity.ReportType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends MongoRepository<ReportEntity, ObjectId> {

    boolean existsByReportingUserIdAndReportTargetIdAndReportTargetType(ObjectId reportingUserId, ObjectId reportTargetId, ReportTargetType reportTargetType);

    List<ReportEntity> findByReportTargetType(ReportTargetType reportTargetType);

    @Aggregation(pipeline = {
            "{ $group: { _id: '$reportTargetId', count: { $sum: 1 } } }", // reportTargetId별로 그룹화하고 개수 세기
            "{ $match: { count: { $gte: ?0 } } }" // minReportCount 이상인 count만 필터링
    })
    List<ReportEntity> findReportsByMinReportCount(Integer minReportCount);

    List<ReportEntity> findByReportingUserId(ObjectId userId);

    // 현재 정지 상태이며, 정지 종료일이 아직 남아있는 유저 조회
    //정지 종료일(suspensionEndDate)이 현재 날짜보다 이전($lt)
    @Query("{'reportStatus': 'SUSPENDED', 'action.suspensionEndDate': { $lt: ?0 }}")
    List<ReportEntity> findSuspendedUsers(LocalDateTime now);

    // 특정 게시물의 전체 신고 개수
    int countByReportTargetId(ObjectId reportTargetId);

    // 특정 게시물의 특정 신고 유형 개수
    int countByReportTargetIdAndReportType(ObjectId reportTargetId, ReportType reportType);
}
