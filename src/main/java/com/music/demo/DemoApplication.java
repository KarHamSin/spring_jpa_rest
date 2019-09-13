package com.music.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.demo.domain.Artist;
import com.music.demo.domain.Artists;
import com.music.demo.repository.ArtistRepository;
import com.music.demo.repository.SongRepository;
import com.music.demo.service.InitialLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EntityScan("com.music.demo.domain")
@EnableJpaRepositories("com.music.demo.repository")
public class DemoApplication  implements CommandLineRunner {

	@Autowired
	private List<InitialLoadService> initialLoadServices;


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String...args) throws IOException {
		for (InitialLoadService initialLoadService : initialLoadServices) {
			initialLoadService.loadData();
		}
	}

}
