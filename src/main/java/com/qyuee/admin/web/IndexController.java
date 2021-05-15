package com.qyuee.admin.web;

import com.qyuee.admin.service.posts.PostsService;
import com.qyuee.admin.web.Dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        // 서버 템플릿엔진에서 사용 할 수 있는 객체를 지정 할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());

        // mustache stater에 의해서 template의 경로 및 확장자는 자동으로 지원이 된다.
        return "index"; // -> View Resolver가 처리한다.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
