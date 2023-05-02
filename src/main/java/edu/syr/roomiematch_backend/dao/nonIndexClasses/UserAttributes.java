package edu.syr.roomiematch_backend.dao.nonIndexClasses;

import edu.syr.roomiematch_backend.dao.nonIndexClasses.CoverImage;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


@Data
public class UserAttributes {

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String intro;

    @Field(type = FieldType.Keyword)
    private String profile_image;

    @Field(type = FieldType.Keyword)
    private String gender;

    @Field(type = FieldType.Integer)
    private int age;

    @Field(type = FieldType.Integer)
    private int monthly_budget;

    @Field(type = FieldType.Text)
    private String major;

    @Field(type = FieldType.Text)
    private String date_available;

    @Field(type = FieldType.Keyword)
    private String food_preference;

    @Field(type = FieldType.Nested)
    private List<CoverImage> cover_images;

    @Field(type = FieldType.Text)
    private String bio;

    @Field(type = FieldType.Text)
    private List<String> hobbies;

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

