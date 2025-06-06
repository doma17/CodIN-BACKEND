package inu.codin.codin.domain.chat.chatroom.repository;

import inu.codin.codin.domain.chat.chatroom.entity.ChatRoom;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    @Query("{ '_id': ?0, 'deletedAt': null }")
    Optional<ChatRoom> findById(ObjectId id);

    @Query("{ 'participants.info.?0.userId': ?0, 'participants.info.?0.isLeaved': false, 'deletedAt': null }")
    List<ChatRoom> findByParticipantIsNotLeavedAndDeletedIsNull(ObjectId userId);

    @Query("{ 'referenceId': ?0, 'participants.info.?1.userId': ?1, 'participants.info.?2.userId': ?2, 'deletedAt': null }")
    Optional<ChatRoom> findByReferenceIdAndParticipantsContaining(ObjectId referenceId, ObjectId userId, ObjectId receiverId);
}
