package com.cocoon.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO {
    private String description;
    private int companyId;
    private boolean enabled;
}
