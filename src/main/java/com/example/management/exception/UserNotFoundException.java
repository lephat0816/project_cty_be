package com.example.management.exception;

/**
 * ユーザーが見つからなかった場合にスローされる例外クラス。
 * 
 * この例外は主にUserControllerやサービス層で、指定されたIDのユーザーが存在しない場合に使用。
 */
public class UserNotFoundException extends RuntimeException{
    // コンストラクタ：ユーザーが見つからない場合にIDを受け取ってエラーメッセージを作成
    public UserNotFoundException(Long id) {
        super("Cound not found user with" + id);
    }
}
