package com.directa24.main.challenge.dto;

import java.util.List;

public record DirectorsResponse(
        int threshold,
        List<String> directors
) {
}
