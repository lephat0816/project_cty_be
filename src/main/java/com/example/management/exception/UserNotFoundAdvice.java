package com.example.management.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

@ControllerAdvice // このクラスが例外を処理するためのアドバイスクラスであることを示す
public class UserNotFoundAdvice {
    // UserNotFoundExceptionが発生した場合の例外処理メソッド
    @ResponseBody  // レスポンスボディとして返す
    @ExceptionHandler(UserNotFoundException.class)  // 特定の例外をキャッチする
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404エラーを返す
    public Map<String, String> exceptionHandler(UserNotFoundException ex) {
        // エラーメッセージを格納するマップを作成
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage()); // 例外メッセージをエラーマップに追加
        return errorMap;  // エラーメッセージを含むマップを返す
    }
}
