package com.example.librarycrud.service;

import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Service
public class MinioService {
    private final MinioClient minioClient;
    private static final Logger log = LoggerFactory.getLogger(MinioService.class);
    @Value("${minio.bucket.name}")
    private String bucketName;

    public void putObject(String filename, InputStream inputStream, long size) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(filename).stream(inputStream, size, -1)
                        .contentType("image/jpg")
                        .build());
    }

    public void removeFile(String nameObject) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName)
                .object(nameObject)
                .build());
    }

}
