package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.PostService;
import hr.fer.zemris.opp.giger.web.rest.dto.NewCommentDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PostDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);
	private PostService postService;

	@PostMapping("/create")
    private void postPost(@Valid @RequestBody NewCommentDto commentDto, BindingResult bindingResult) {
        LOGGER.info("PostPost: " + commentDto);
        handleValidation(bindingResult);
		postService.postPost(commentDto);
	}

	@PostMapping("/create/{bandId}")
    private void postPostAsBand(@Valid @RequestBody NewCommentDto commentDto, @PathVariable @Min(1) Long bandId, BindingResult bindingResult) {
        LOGGER.info("PostPostAsBand: " + commentDto + " bandId: " + bandId);
        handleValidation(bindingResult);
		postService.postPost(commentDto, bandId);
	}

	@PostMapping("/{postId}")
    private void postComment(@Valid @RequestBody NewCommentDto commentPreviewDto, @PathVariable @Min(1) Long postId, BindingResult bindingResult) {
        LOGGER.info("PostComment: " + commentPreviewDto + " bandId: " + postId);
        handleValidation(bindingResult);
		postService.postComment(commentPreviewDto, postId);
	}

	@GetMapping("/{postId}")
    public PostDto viewPost(@PathVariable @Min(1) Long postId, BindingResult bindingResult) {
        LOGGER.info("ViewPost: " + postId);
        handleValidation(bindingResult);
		return postService.viewPost(postId);
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
