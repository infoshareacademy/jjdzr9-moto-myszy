package com.isa.jjdzr.walletweb.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilterInputDtoTest {
    @Test
    void testSetters() {
        // GIVEN
        FilterInputDto dto = new FilterInputDto();
        String filterInput = "example";

        // WHEN
        dto.setFilterInput(filterInput);

        // THEN
        Assertions.assertEquals(filterInput, dto.getFilterInput());
    }

}
