package com.example.librarycrud.service;

import com.example.librarycrud.model.dto.PublisherDTO;
import com.example.librarycrud.model.entity.Publisher;
import com.example.librarycrud.model.request.BookRequest;

import java.util.List;

public interface PublisherService {
    List<PublisherDTO> getAllPublisher();

    PublisherDTO creatPublisher(BookRequest bookRequest);

    Publisher updatePublisher(long id, Publisher publisher);

    void deletePublisher(long id);

    Publisher getPublisherById(long id);
}
