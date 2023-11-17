package com.yure.complaints.application.request.category;

import com.yure.complaints.domain.models.Category;
import org.hibernate.validator.constraints.Length;

public record CategoryUpdateRequest (
    @Length(min = 3)
    String name,
    String description
){
    public static Category toCategory(CategoryCreateRequest categoryCreateRequest){
        var c = new Category();
        c.setDescription(categoryCreateRequest.description());
        c.setName(categoryCreateRequest.name());
        return  c;
    }
}
