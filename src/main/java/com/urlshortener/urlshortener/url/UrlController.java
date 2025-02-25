package com.urlshortener.urlshortener.url;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class UrlController{
    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService){
        this.urlService=urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<Url> addUrl(@RequestBody RequestedUrl url){
        Url uurl = new Url();
        String fullUrl = url.getUrl();
        if (!fullUrl.startsWith("http://") && !fullUrl.startsWith("https://")) {
            fullUrl = "http://" + fullUrl; 
        }
        uurl.setUrl(fullUrl);
        uurl.setAccess_count(0);
        uurl.setCreated_at(new Timestamp(System.currentTimeMillis()));
        uurl.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        Url createdUrl = urlService.addUrl(uurl);
        return new ResponseEntity<>(createdUrl, HttpStatus.CREATED);
    }

    @GetMapping("/shorten/{surl}")
    public ResponseEntity<String> getUrl(@PathVariable String surl){
        Url url = urlService.getUrlByShorturl(surl); //devuelve null
        //System.out.println(surl);
        if(url != null){
            urlService.updateUrl(url);
            //System.out.println(url.toString());
            String html = "<html>"
            + "<body>"
            + "<script>window.location.href = \""+url.getUrl()+"\"</script>"
            + "</body>"
            + "</html>";
            return ResponseEntity.ok().contentType(MediaType.valueOf("text/html")).body(html);
        }else{
            String html = "<html>"
            +"<body>"
            +"<script>window.location.href = \"http://192.168.1.80/\";setTimeout(function() {alert(\"URL no encontrada\");, 1000);\"</script>"
            +"</body>"
            +"</html>";

            return ResponseEntity.ok().contentType(MediaType.valueOf("text/html")).body(html);
        }
    }
}
