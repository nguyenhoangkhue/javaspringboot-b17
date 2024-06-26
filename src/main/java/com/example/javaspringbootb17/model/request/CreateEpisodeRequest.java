package com.example.javaspringbootb17.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEpisodeRequest {
    @NotEmpty(message = "Tên tập phim không được để trống")
    String name;
    @NotEmpty(message = "Số thứ tự tập phim không được để trống")
    Integer displayOrder;
    @NotNull(message = "Trạng thái tập phim phim không được để trống")
    Boolean status;
}
