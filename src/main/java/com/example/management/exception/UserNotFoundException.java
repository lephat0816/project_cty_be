package com.example.management.exception;

public class UserNotFoundException extends RuntimeException{
    // コンストラクタ：ユーザーが見つからない場合にIDを受け取ってエラーメッセージを作成
    public UserNotFoundException(Long id) {
        super("Cound not found user with" + id);
    }
}
