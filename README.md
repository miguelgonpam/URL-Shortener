# URL-Shortener

## Descripción y Alcance
URL-SHORTENER es un proyecto de Backend implementado con Java Spring Boot cuyo propósito es ganar habilidad y experiencia trabajando con estas herramientas, para poder llegar a ser Desarrolladores de Backend. 
Los programadores de este proyecto son:
-Miguel González Pampliega
-Diego Arbeloa Pérez
-Guillermo Arce Gómez


## Preparación
Se debe instalar apache2 e iniciarlo
```
sudo apt install apache2
systemctl restart apache2
```
A continuación se deben incluir los ficheros del Front-End en el directorio `/var/www/html`, principalmente `index.html` ya que es el fichero que se va a buscar por defecto, y en nuestro caso, también `styles.css`.


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
