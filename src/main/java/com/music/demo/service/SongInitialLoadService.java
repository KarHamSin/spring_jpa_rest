package com.music.demo.service;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.demo.domain.Artists;
import com.music.demo.domain.Song;
import com.music.demo.domain.Songs;
import com.music.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SongInitialLoadService implements InitialLoadService {

    @Autowired
    private SongRepository songRepository;

    public void loadData() throws IOException {
        File file = ResourceUtils.getFile("classpath:initial/songs.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        Songs songs = mapper.readValue(file, Songs.class);
        var distictSongs = songs.getSongs().stream().filter(distinctByKey(Song::getName)).collect(Collectors.toList());
        songRepository.saveAll(distictSongs);
        System.out.println("songs saved");
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
