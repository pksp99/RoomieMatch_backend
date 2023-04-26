package edu.syr.roomiematch_backend.dao;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "likedgroups")
@Data
public class LikedGroup {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String groupIdMakingLikeAction;

    @Field(type = FieldType.Keyword)
    private String groupIdLiked;

}
