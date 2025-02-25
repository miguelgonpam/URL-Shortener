package com.urlshortener.urlshortener.url;

import java.sql.Timestamp;

import org.hibernate.annotations.Generated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="url")
public class Url{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", unique=true)
    private Integer id;

    @Generated
    @Column(name="shorturl", unique = true)
    private String shorturl;

    @Column(name="url")
    private String url;

    @Column(name="created_at")
    private Timestamp created_at;

    @Column(name="updated_at")
    private Timestamp updated_at;

    @Column(name="access_count")
    private Integer access_count;

    public String toString(){
        return "Url[ id="+id+", shorturl="+shorturl+", url="+url+", createdAt="+created_at+", updatedAt="+updated_at+", accessCount="+access_count+"]";
    }

}
