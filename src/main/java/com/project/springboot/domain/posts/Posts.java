package com.project.springboot.domain.posts;

import com.project.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor          //기본 생성자 추가
@Entity                     //테이블과 링크될 클래스
public class Posts extends BaseTimeEntity {

    @Id                                                     //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //pk생성규칙 (옵션 auto_increment)
    private Long id;

    @Column(length = 500, nullable = false)                 //컬럼 재정의
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder                                                //빌더 패턴 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
