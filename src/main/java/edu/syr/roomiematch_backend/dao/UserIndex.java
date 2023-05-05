package edu.syr.roomiematch_backend.dao;

import edu.syr.roomiematch_backend.model.PreferredAttributes;
import edu.syr.roomiematch_backend.model.UserAttributes;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "users")
@Data
public class UserIndex {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String userId;

    @Field(type = FieldType.Text)
    private String email;

    @Field(type = FieldType.Keyword)
    private String groupId;

    @Field(type = FieldType.Object)
    private UserAttributes userAttributes;

    @Field(type = FieldType.Object)
    private PreferredAttributes preferredAttributes;


}


