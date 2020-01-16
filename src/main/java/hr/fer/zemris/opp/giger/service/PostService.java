package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Comment;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Post;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.repository.BandRepository;
import hr.fer.zemris.opp.giger.repository.CommentRepository;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import hr.fer.zemris.opp.giger.repository.PostRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.NewCommentDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PostDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.NOT_A_MEMBER_OF_BAND;
import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.NO_SUCH_POST;

@AllArgsConstructor
@Service
public class PostService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private UserDetailsServiceImpl userDetailsService;
	private MusicianRepository musicianRepository;
	private BandRepository bandRepository;
	private MusicianService musicianService;

	public void postPost(NewCommentDto commentDto) {
		Musician musician = userDetailsService.getLoggedMusician();

		Post post = Post.createPost(commentDto.getContent());
		musician.addPost(post);

		postRepository.save(post);
		musicianRepository.save(musician);
	}

	public void postPost(NewCommentDto commentDto, Long bandId) {
		Musician musician = userDetailsService.getLoggedMusician();
		Band band = bandRepository.findAllByMembersContaining(musician).stream().filter(e -> e.getId().equals(bandId)).findFirst().orElseThrow(() -> new GigerException(NOT_A_MEMBER_OF_BAND));

		Post post = Post.createPost(commentDto.getContent());
		band.addPost(post);

		postRepository.save(post);
		bandRepository.save(band);
	}

	public void postComment(NewCommentDto commentPreviewDto, Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new GigerException(NO_SUCH_POST));

		post.addComment(commentRepository.save(new Comment(null, commentPreviewDto.getContent(), LocalDateTime.now(), userDetailsService.getLoggedPerson())));
		postRepository.save(post);
	}

	public PostDto viewPost(Long postId) {
		var post = postRepository.findById(postId).orElseThrow(() -> new GigerException(NO_SUCH_POST));
		Optional<Musician> author = musicianRepository.findByPostsContaining(post);
		var band = bandRepository.findByPostsContaining(post);

		if (author.isPresent())
			return post.toDto(musicianService.showProfile(author.get().getId()).toMusicianProfileDto(), null);
		if (band.isPresent()) return post.toDto(null, band.get().toDto());

		throw new GigerException(NO_SUCH_POST);
	}
}
