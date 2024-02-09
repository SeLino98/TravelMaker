package com.ssafy.gumibom.global.config;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FFmpegConfig {

    @Value("${ffmpeg.main.location}")
    public String ffmpegLocation;

    @Value("${ffmpeg.probe.location}")
    public String ffprobeLocation;

    @Bean
    public FFmpeg ffMpeg() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(ffmpegLocation);
        return new FFmpeg(classPathResource.getURL().getPath());
    }

    @Bean
    public FFprobe ffProbe() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(ffprobeLocation);
        return new FFprobe(classPathResource.getURL().getPath());
    }
}
