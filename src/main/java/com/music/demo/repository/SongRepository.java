package com.music.demo.repository;

import com.music.demo.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "song", path = "song")
public interface SongRepository extends JpaRepository<Song, Long> {
    Song save(Song song);
    List<Song> getByGenre(String genre);
    void deleteById(Long id);

}
