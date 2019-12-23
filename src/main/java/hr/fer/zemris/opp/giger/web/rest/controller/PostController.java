package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.PostService;
import hr.fer.zemris.opp.giger.web.rest.dto.NewCommentDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PostDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PostPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @PostMapping("/create")
    private void postPost(@RequestBody NewCommentDto commentDto) {
        postService.postPost(commentDto);
    }

    @PostMapping("/create/{bandId}")
    private void postPostAsBand(@RequestBody NewCommentDto commentDto, Long bandId) {
        postService.postPost(commentDto,bandId);
    }

    @PostMapping("/{postId}")
    private void postComment(@RequestBody NewCommentDto commentPreviewDto, @PathVariable Long postId) {
        postService.postComment(commentPreviewDto, postId);
    }

    @GetMapping("/{postId}")
    public PostDto viewPost(@PathVariable Long postId) {
        return postService.viewPost(postId);
    }
}
