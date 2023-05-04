package com.isa.jjdzr.walletweb.service.fileuploader;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
public class FileUploaderImpl implements FileUploader{

    private final MinioClient minioClient;

    public void uploadChart(String filepath, String filename) {
        try {
            boolean found = checkIfBucketExists();
            if (!found) {
                creteNewBucket();
            }
            uploadFile(filepath, filename);

        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    private void uploadFile(String filepath, String filename) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket("moto-myszy")
                        .object(filename)
                        .filename(filepath)
                        .build()
        );
    }

    private void creteNewBucket() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        minioClient.makeBucket(MakeBucketArgs
                .builder()
                .bucket("moto-myszy")
                .build());
    }

    private boolean checkIfBucketExists() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        return minioClient.bucketExists(BucketExistsArgs
                .builder()
                .bucket("moto-myszy")
                .build());
    }
}
