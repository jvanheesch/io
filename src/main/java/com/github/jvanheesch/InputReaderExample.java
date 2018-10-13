package com.github.jvanheesch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class InputReaderExample {
    public static final String RELATIVE_PATH_TO_FILE_ON_CLASSPATH = "com/github/jvanheesch/AsciiExample.txt";

    public static void main(String[] args) throws IOException, URISyntaxException {
        readWithIo();
        readWithNio();
    }

    private static void readWithIo() throws URISyntaxException, IOException {
        int numberOfBytesInFile = Math.toIntExact(getFile().length());
        try (InputStream inputStream = InputReaderExample.class.getClassLoader().getResourceAsStream(RELATIVE_PATH_TO_FILE_ON_CLASSPATH)) {
            byte[] byteArray = new byte[numberOfBytesInFile];
            int numberOfBytesRead = inputStream.read(byteArray);
            System.out.println(String.format("Read %d bytes with io: %s.", numberOfBytesRead, Arrays.toString(byteArray)));
        }
    }

    private static void readWithNio() throws IOException, URISyntaxException {
        byte[] result = Files.readAllBytes(getPath());
        System.out.println(String.format("Read %d bytes with nio: %s.", result.length, Arrays.toString(result)));
    }

    private static File getFile() throws URISyntaxException {
        return getPath().toFile();
    }

    private static Path getPath() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource(RELATIVE_PATH_TO_FILE_ON_CLASSPATH).toURI());
    }
}
