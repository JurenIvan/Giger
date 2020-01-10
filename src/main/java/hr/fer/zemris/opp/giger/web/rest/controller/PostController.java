package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.PostService;
import hr.fer.zemris.opp.giger.web.rest.dto.NewCommentDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PostDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	@PostMapping("/create")
	private void postPost(@Valid @RequestBody NewCommentDto commentDto) {
		postService.postPost(commentDto);
	}

	@PostMapping("/create/{bandId}")
	private void postPostAsBand(@Valid @RequestBody NewCommentDto commentDto, @PathVariable @Min(1) Long bandId) {
		postService.postPost(commentDto, bandId);
	}

	@PostMapping("/{postId}")
	private void postComment(@Valid @RequestBody NewCommentDto commentPreviewDto, @PathVariable @Min(1) Long postId) {
		postService.postComment(commentPreviewDto, postId);
	}

	@GetMapping("/{postId}")
	public PostDto viewPost(@PathVariable @Min(1) Long postId) {
		return postService.viewPost(postId);
	}
}
