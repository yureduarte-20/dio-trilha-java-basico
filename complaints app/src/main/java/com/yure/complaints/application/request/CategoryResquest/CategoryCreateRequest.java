package com.yure.complaints.application.request.CategoryResquest;


import com.yure.complaints.domain.models.Category;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CategoryCreateRequest(
        @NotBlank
        @Length(min = 3)
        String name,

        String description
) {
        public static Category toCategory(CategoryCreateRequest categoryCreateRequest){
                var c = new Category();
                c.setDescription(categoryCreateRequest.description());
                c.setName(categoryCreateRequest.name());
                return  c;
        }
}
