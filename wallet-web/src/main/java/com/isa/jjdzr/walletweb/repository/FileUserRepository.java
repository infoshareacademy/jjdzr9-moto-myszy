package com.isa.jjdzr.walletweb.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.jjdzr.walletweb.Constants;
import com.isa.jjdzr.walletweb.dto.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.stream;
@Component
class FileUserRepository implements UserRepository{
    private final String userDirectory;
    private final Gson gson;
    private Long nextId;

    public FileUserRepository() {
        String name = "User";
        userDirectory = prepareDirectory(name);
        nextId = calculateNextId(userDirectory);
        gson = prepareGson();
    }
    @Override
    public User save(User user) {
        updateIdIfNew(user);
        String json = gson.toJson(user);
        writeToFile(json, user.getId());
        return user;
    }

    @Override
    public List<User> getAll() {
        return stream(new File(userDirectory).listFiles())
                .map(this::prepareReader)
                .map(reader -> gson.fromJson(reader, User.class))
                .toList();
    }

    private String prepareDirectory(String userName) {
        String filePath = new StringBuilder()
                .append(Constants.BASE_FILE_PATH)
                .append(File.separator)
                .append(userName)
                .toString();
        File file = new File(filePath);
        file.mkdirs();
        return file.getAbsolutePath();
    }

    private void updateIdIfNew(User user) {
        if(user.getId() == null) {
            user.setId(nextId++);
        }
    }
    @SneakyThrows
    private void writeToFile(String json, Long userId) {
        FileWriter writer = prepareWriter(userId);
        writer.append(json);
        writer.close();
    }

    private Gson prepareGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    private FileWriter prepareWriter(Long userId) throws IOException {
        String filePath = prepareEntityFilePath(userId);
        return new FileWriter(filePath, false);
    }

    @SneakyThrows
    private FileReader prepareReader(Long userId) {
        String filePath = prepareEntityFilePath(userId);
        return new FileReader(new File(filePath));
    }

    @SneakyThrows
    private FileReader prepareReader(File file) {
        return new FileReader(file);
    }

    private String prepareEntityFilePath(Long userID) {
        return new StringBuilder()
                .append(userDirectory)
                .append(File.separator)
                .append(userID)
                .append(".json")
                .toString();
    }

    private Long calculateNextId(String userDirectory) {
        File file = new File(userDirectory);
        return 1 + stream(file.listFiles())
                .map(this::getIdFromFileName)
                .max(Long::compareTo)
                .orElse(-1L);
    }

    private Long getIdFromFileName(File file) {
        String fileName = file.getName().replace(".json", "");
        return Long.parseLong(fileName);
    }
}
