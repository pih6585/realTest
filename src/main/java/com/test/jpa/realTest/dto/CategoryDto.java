package com.test.jpa.realTest.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.test.jpa.realTest.entity.Category;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {


    private Long id;

    private String name;

    private String lv;

    private Long parent_id;

    private String parent_name;

    private List<CategoryDto> child = new ArrayList<>();

    public CategoryDto(String name, Long parent_id){
        this.name = name;
        this.parent_id = parent_id;
    }

    @QueryProjection
    public CategoryDto(Long id, String name){
        this.id = id;
        this.name = name;
    }

    @QueryProjection
    public CategoryDto(Long id, String name,Long parent_id){
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
    }


    @QueryProjection
    public CategoryDto(Long id, String name,Long parent_id, String parent_name){
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
        this.parent_name = parent_name;
    }

    @QueryProjection
    public CategoryDto(Long id, String name, String lv, Long parent_id, String parent_name){
        this.id = id;
        this.name = name;
        this.lv = lv;
        this.parent_id = parent_id;
        this.parent_name = parent_name;
    }

}
