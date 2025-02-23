package com.urlshortener.urlshortener.url;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer>{
    void deleteById(@NonNull Integer id);

    //Optional<Url> findById(@NonNull Integer id);

    Optional<Url> findByShorturl(@NonNull String shorturl);


}