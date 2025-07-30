package com.erickmendes.workshop_mongodb.config;

import com.erickmendes.workshop_mongodb.domain.Post;
import com.erickmendes.workshop_mongodb.domain.User;
import com.erickmendes.workshop_mongodb.dto.AuthorDTO;
import com.erickmendes.workshop_mongodb.dto.CommentDTO;
import com.erickmendes.workshop_mongodb.repository.PostRepository;
import com.erickmendes.workshop_mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post p1 = new Post(null, Instant.parse("2025-03-21T13:08:00Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post p2 = new Post(null, Instant.parse("2025-03-22T08:30:00Z"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
        postRepository.saveAll(Arrays.asList(p1, p2));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", Instant.parse("2025-07-21T10:30:00Z"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", Instant.parse("2025-07-21T14:40:00Z"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", Instant.parse("2025-07-22T08:50:00Z"), new AuthorDTO(alex));

        p1.getComments().add(c1);
        p1.getComments().add(c2);
        p2.getComments().add(c3);
        postRepository.saveAll(Arrays.asList(p1, p2));

        maria.getPosts().add(p1);
        maria.getPosts().add(p2);
        userRepository.save(maria);
    }
}
