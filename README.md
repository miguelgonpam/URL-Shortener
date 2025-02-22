# URL-Shortener

Se debe modificar la configuración del servidor Apache para añadir los módulos `proxy` y `proxy_http`
Para ello se debe hacer
```
sudo a2enmod proxy
sudo a2enmod proxy_http
systemctl restart apache2
```
También se debe añadir las siguientes líneas a la configuración `000-default.conf`, dentro del bloque `<VirtualHost *:80>` 
```
  <Location "/shorten">
    ProxyPass "http://127.0.0.1:8080/shorten"
    ProxyPassReverse "http://127.0.0.1:8080/shorten"
  </Location>
```

También podemos redirigir los errores 404 a la página principal añadiendo lo siguiente al mismo archivo en el mismo lugar.
```
  ErrorDocument 404 /index.html
```
