package ru.gr0550x;

import java.time.LocalDate;

public record Task(
        String title,
        LocalDate deadline,
        Priority priority
) {}
