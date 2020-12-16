package com.example.demo.pojo;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Human {
    @NotNull
    private int pk;
    @Length(max = 30,min = 0)
    private String name;

}
