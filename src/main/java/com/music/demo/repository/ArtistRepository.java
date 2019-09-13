package com.music.demo.repository;

import com.music.demo.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "artist", path = "artist")
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist save(Artist artist);
    List<Artist> getByName(String name);
    void deleteById(Long id);
}
