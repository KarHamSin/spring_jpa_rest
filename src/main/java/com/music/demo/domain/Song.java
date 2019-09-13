package com.music.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {

    @GeneratedValue
    @Id
    private Long id;

    @Column(unique = true)
    private String name;
    private Integer year;
    private String artist;
    private String shortName;
    private Integer bpm;
    private Integer duration;
    private String genre;
    private String spotifyId;
    private String album;
}
