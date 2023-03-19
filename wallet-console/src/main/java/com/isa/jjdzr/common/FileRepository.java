package com.isa.jjdzr.common;

import com.google.gson.*;
import lombok.SneakyThrows;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Arrays.stream;

public class FileRepository<T extends BaseEntity> {

    private static final String BASE_FILE_PATH = "data";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final String entiryDirectory;
    private final Gson gson;
    private Long nextId;

    public FileRepository() {
        String entityName = getParameterClass().getSimpleName();
        entiryDirectory = prepareDirectory(entityName);
        nextId = calculateNextId(entiryDirectory);
        gson = prepareGson();
    }

    public T save(T entity) {
        updateIdIfNewEntity(entity);
        String json = gson.toJson(entity);
        writeToFile(json, entity.getId());
        return entity;
    }

    public T find(Long id) {
        FileReader reader = prepareReader(id);
        return gson.fromJson(reader, getParameterClass());
    }

    public List<T> getAll() {
        Class<T> parameterClass = getParameterClass();
        return stream(new File(entiryDirectory).listFiles())
                .map(this::prepareReader)
                .map(reader -> gson.fromJson(reader, parameterClass))
                .toList();
    }

    public void delete(Long id) {
        String filePath = prepareEntityFilePath(id);
        File file = new File(filePath);
        file.delete();
    }


    private Class<T> getParameterClass() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> paramClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        return paramClass;
    }

    private String prepareDirectory(String entityName) {
        String filePath = new StringBuilder()
                .append(BASE_FILE_PATH)
                .append(File.separator)
                .append(entityName)
                .toString();
        File file = new File(filePath);
        file.mkdirs();
        return file.getAbsolutePath();
    }

    private void updateIdIfNewEntity(T entity) {
        if(entity.getId() == null) {
            entity.setId(nextId++);
        }
    }

    @SneakyThrows
    private void writeToFile(String json, Long entityId) {
        FileWriter writer = prepareWriter(entityId);
        writer.append(json);
        writer.close();
    }

    private Gson prepareGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, srcType, context) ->  new JsonPrimitive(formatter.format(localDate)))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->  LocalDate.parse(json.getAsString(), formatter))
                .create();
    }

    private FileWriter prepareWriter(Long entityId) throws IOException {
        String filePath = prepareEntityFilePath(entityId);
        return new FileWriter(filePath, false);
    }

    @SneakyThrows
    private FileReader prepareReader(Long id) {
        String filePath = prepareEntityFilePath(id);
        return new FileReader(new File(filePath));
    }

    @SneakyThrows
    private FileReader prepareReader(File file) {
        return new FileReader(file);
    }

    private String prepareEntityFilePath(Long entityId) {
        return new StringBuilder()
                .append(entiryDirectory)
                .append(File.separator)
                .append(entityId)
                .append(".json")
                .toString();
    }

    private Long calculateNextId(String entityDirectory) {
        File file = new File(entityDirectory);
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

