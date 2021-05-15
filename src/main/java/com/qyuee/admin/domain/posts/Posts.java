package com.qyuee.admin.domain.posts;

import com.qyuee.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 롬복의 어노테이션
@Getter
@NoArgsConstructor // 기본생성자 자동추가

// JPA의 어노테이션
// 테이블과 링크될 클래스임을 나타낸다.
// 기본적으로 카멜케이스 이름을 테이블 이름을 매칭한다. ex) Posts -> posts, MemberListTable -> member_list_table
@Entity
public class Posts extends BaseTimeEntity {
    @Id // PK를 의미
    // PK의 생성 규칙, GenerationType.IDENTITY으로해야 auto_increment가 된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    // 빌더 패턴: setter가 없는 객체를 생성하여 변경불가능하게 한다.
    // 변수 공유 이슈 대비
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 비즈니스
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
