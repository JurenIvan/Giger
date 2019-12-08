package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode;
import hr.fer.zemris.opp.giger.config.errorHandling.GigerException;
import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Comment;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Post;
import hr.fer.zemris.opp.giger.repository.CommentRepository;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import hr.fer.zemris.opp.giger.repository.PostRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.NewCommentDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PostPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class PostService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserDetailsServiceImpl userDetailsService;
    private MusicianRepository musicianRepository;

    public void postPost(NewCommentDto commentDto) {
        Musician musician = userDetailsService.getLoggedMusician();
        musician.addPost(Post.createPost(commentDto.getContent()));

        musicianRepository.save(musician);
    }

    public void postComment(NewCommentDto commentPreviewDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_POST));

        post.addComment(commentRepository.save(new Comment(null, commentPreviewDto.getContent(), LocalDateTime.now(), userDetailsService.getLoggedPerson())));
        postRepository.save(post);
    }

    public PostPreviewDto viewPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_POST)).toDto();
    }
}
