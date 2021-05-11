package com.qyuee.admin.service.posts;

import com.qyuee.admin.domain.posts.Posts;
import com.qyuee.admin.domain.posts.PostsRepository;
import com.qyuee.admin.web.Dto.PostsResponseDto;
import com.qyuee.admin.web.Dto.PostsSaveRequestDto;
import com.qyuee.admin.web.Dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 서비스는 트랜잭션, 도메인 간 순서보장만 한다.
@Service
@RequiredArgsConstructor // final이 선언된 모든 필드를 인자로하는 생성자를 생성해줌
public class PostsService {
    // @Autowired 없이 빈 객체 주입
    // 생성자를 통해서 주입받는 것을 추천
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id+"+id));
        /* update를 하는 쿼리를 날리는 부분이 없다...?
         JPA의 영속성 컨텍스트 때문에 가능하다.
         ?? -> 엔티티를 영구저장하는 환경
         트랜잭션 안에서 데이터베이스에서 데이터를 가져오고 영속성이 유지된 상태로 데이터의 값을 변경하면
         트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
         즉, 이 update 트랜잭션이 끝나기 전에 데이터를 조회하면 update 이전의 상태가 보일 것 -> 아주 당연한 이야기긴 함
         이 개념을 더티 체킹이라고 함 (https://jojoldu.tistory.com/415)
         */
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
