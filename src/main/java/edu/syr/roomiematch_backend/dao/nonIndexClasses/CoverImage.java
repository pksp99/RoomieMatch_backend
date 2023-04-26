package edu.syr.roomiematch_backend.dao.nonIndexClasses;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
public class CoverImage {
    @Field(type = FieldType.Keyword)
    private String image_url;
}
