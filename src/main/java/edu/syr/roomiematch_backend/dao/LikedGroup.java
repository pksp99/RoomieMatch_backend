package edu.syr.roomiematch_backend.dao;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("likedgroups")
@Data
public class LikedGroup {

    @Id
    private String id;

    private String groupIdMakingLikeAction;

    private String groupIdLiked;

}
