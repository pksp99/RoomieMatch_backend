package edu.syr.roomiematch_backend.dao.nonIndexClasses;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
public class PreferredAttributes {

    @Field(type = FieldType.Keyword)
    private String gender;

    @Field(type = FieldType.Text)
    private List<String> hobbies;

    @Field(type = FieldType.Keyword)
    private String food_preference;

    @Field(type = FieldType.Integer)
    private int cleanliness;

    @Field(type = FieldType.Keyword)
    private String sleep_schedule;

    @Field(type = FieldType.Boolean)
    private boolean smoking;

    @Field(type = FieldType.Boolean)
    private boolean partying;

    @Field(type = FieldType.Boolean)
    private boolean pet_friendly;

}
