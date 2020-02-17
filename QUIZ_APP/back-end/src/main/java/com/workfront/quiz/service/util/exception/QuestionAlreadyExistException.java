package com.workfront.quiz.service.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class QuestionAlreadyExistException extends RuntimeException {

    public QuestionAlreadyExistException(String questionName) {
        super("Question" + questionName + " already exists.");
    }
}
