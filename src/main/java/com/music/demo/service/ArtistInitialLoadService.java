package com.music.demo.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.demo.domain.Artist;
import com.music.demo.domain.Artists;
import com.music.demo.domain.Songs;
import com.music.demo.repository.ArtistRepository;
import com.music.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

@Service
public class ArtistInitialLoadService implements InitialLoadService {

    @Autowired
    private ArtistRepository artistRepository;

    public void loadData() throws IOException {
        File file = ResourceUtils.getFile("classpath:initial/artists.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        Artists artists = mapper.readValue(file, Artists.class);
        artistRepository.saveAll(artists.getArtists());
        System.out.println("artists saved");
    }
}
