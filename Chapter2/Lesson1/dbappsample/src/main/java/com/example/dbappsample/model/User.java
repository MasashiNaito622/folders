package com.example.dbappsample.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id//主キーであることを指定
    //IDの生成戦略を指定(データベース依存の自動インクリメント)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true)//ユニーク制約を追加
    private String username;

    @Column(nullable=false)//データベースのカラム設定　ここではNull不可を指定
    private String name;

    //コンストラクタ(デフォルト)
    public User(){

    }
    // コンストラクタ（全パラメータ）
    public User(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    // ゲッターとセッター
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
