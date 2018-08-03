package com.manning.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository
        extends JpaRepository<Reader, String>{    /* 通过JPA持久化 Reader */
}
