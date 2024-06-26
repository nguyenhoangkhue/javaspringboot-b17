package com.example.javaspringbootb17.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEpisodeRequest {
    @NotEmpty(message = "Tên tập phim không được để trống")
    String name;
    @NotNull(message = "Số thứ tự tập phim không được để trống")
    Integer displayOrder;
    @NotNull(message = "Trạng thái tập phim phim không được để trống")
    Boolean status;
}
