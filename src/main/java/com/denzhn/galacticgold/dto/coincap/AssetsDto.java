package com.denzhn.galacticgold.dto.coincap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AssetsDto {
    private List<AssetsDataDto> data;
}
