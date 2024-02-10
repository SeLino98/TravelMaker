package com.ssafy.gumibom.global.config;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FFmpegConfig {

    @Value("${ffmpeg.main.location}")
    public String ffmpegLocation;

    @Value("${ffmpeg.probe.location}")
    public String ffprobeLocation;

    @Bean
    public FFmpeg ffMpeg() throws IOException {
//        ClassPathResource classPathResource = new ClassPathResource(ffmpegLocation);
//        return new FFmpeg(classPathResource.getURL().getPath());

        InputStream inputStream = new ClassPathResource(ffmpegLocation).getInputStream();
        File file = File.createTempFile("ffmpeg", ".exe");

        try {
            FileUtils.copyInputStreamToFile(inputStream, file);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        return new FFmpeg(file.getPath());
    }

    @Bean
    public FFprobe ffProbe() throws IOException {
//        ClassPathResource classPathResource = new ClassPathResource(ffprobeLocation);
//        return new FFprobe(classPathResource.getURL().getPath());

        InputStream inputStream = new ClassPathResource(ffprobeLocation).getInputStream();
        File file = File.createTempFile("ffprobe", ".exe");

        try {
            FileUtils.copyInputStreamToFile(inputStream, file);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        return new FFprobe(file.getPath());
    }
}
