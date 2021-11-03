package com.api.bookstore.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(String errorTitle, Map<String, String> errors) { }
