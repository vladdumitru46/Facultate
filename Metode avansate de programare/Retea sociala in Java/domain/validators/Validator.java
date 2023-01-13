package com.example.reteasocialagui.example.domain.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}