package com.example.javaspringbootb17.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateReviewRequest {
    String content;
    Integer rating;
}
