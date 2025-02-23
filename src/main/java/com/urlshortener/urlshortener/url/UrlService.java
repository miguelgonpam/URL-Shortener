package com.urlshortener.urlshortener.url;

import java.sql.Timestamp;
//import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class UrlService{
    
    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository){
        this.urlRepository=urlRepository;
    }

    public Url getUrlById(Integer id){
        Optional<Url> url = urlRepository.findById(id);
        
        return url.isPresent()?url.get():null;
    }

    /**
     * Busca en el repositorio un registro donde coincida el campo shorturl con el valor pasado como parámetro.
     * 
     * @param shorturl a buscar coincidencias en el repositorio.
     * @return url coincidente o null si no encuentra ninguno.
     */
    public Url getUrlByShorturl(String shorturl){
        //List<Url> lis = urlRepository.findAll().stream().collect(Collectors.toList()); //.filter(url -> shorturl.equals(url.getShorturl()))
        //System.out.println(urlRepository.findAll().toArray().toString());
        //System.out.println(lis.size());
        /*
         * if(lis.size() > 0)
            return lis.getFirst();
        else
            return null;
         */
        Optional<Url> url = urlRepository.findByShorturl(shorturl);
        if(url.isPresent())
            return url.get();
        else
            return null;
        
    }


    public Url addUrl(Url url){
        //hacer robusto
        Url u = urlRepository.save(url);
        return u;
    }

    public Url updateUrl(Url url){
        Optional<Url> old_url = urlRepository.findById(url.getId());

        if (old_url.isPresent()){

            Url upt = old_url.get();
            Integer act = upt.getAccess_count();
            act++;
            upt.setAccess_count(act);
            Timestamp t = new Timestamp(System.currentTimeMillis());
            upt.setUpdated_at(t);
            urlRepository.save(upt);
            return upt;
        }
        return null;
    }

    @Transactional
    public void deleteUrl(Integer id){
        urlRepository.deleteById(id);
    }

}