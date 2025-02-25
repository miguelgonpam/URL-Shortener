# URL-Shortener

## Descripción y Alcance | Description and Scope
**[ES]** URL-SHORTENER es un proyecto de Backend implementado con Java Spring Boot cuyo propósito es ganar habilidad y experiencia trabajando con estas herramientas, para poder llegar a ser Desarrolladores de Backend. 
Los programadores de este proyecto son:

**[EN]** URL-Shortener is a Backend project, implemented using Java Spring Boot. Our purpouse was to gain expertise and hability with this tools, so we can get to become Backend Developers.
This project authors are:

* Miguel González Pampliega
* Diego Arbeloa Pérez
* Guillermo Arce Gómez


## Preparación | SetUp
**[ES]** Se debe instalar apache2 e iniciarlo. \
**[EN]** We must install apache2 and start it up.

```
sudo apt install apache2
systemctl restart apache2
```
**[ES]** A continuación se deben incluir los ficheros del Front-End en el directorio `/var/www/html`, principalmente `index.html` ya que es el fichero que se va a buscar por defecto, y en nuestro caso, también `styles.css`.\
**[EN]** Later on, we must include our Front-End files in `/var/www/html` directory. `index.html` is the most important one because it is the default file apache is going to look for. In our case, we included `styles.css` too.


**[ES]** Se debe modificar la configuración del servidor Apache para añadir los módulos `proxy` y `proxy_http`.
Para ello se debe hacer: 

**[EN]** We also must change our apache server configuration so we can add `proxy` and `proxy_http` modules.
We must execute: 
```
sudo a2enmod proxy
sudo a2enmod proxy_http
systemctl restart apache2
```

**[ES]** También se debe añadir las siguientes líneas a la configuración `/etc/apache2/sites-available/000-default.conf`, dentro del bloque `<VirtualHost *:80>` \
**[EN]** We also have to add these lines to conf file `/etc/apache2/sites-available/000-default.conf`, inside `<VirtualHost *:80>` block. 

```
  <Location "/shorten">
    ProxyPass "http://127.0.0.1:8080/shorten"
    ProxyPassReverse "http://127.0.0.1:8080/shorten"
  </Location>
```

**[ES]** También podemos redirigir los errores 404 a la página principal añadiendo lo siguiente al mismo archivo en el mismo lugar. \
**[EN]** If we want to redirect all 404 errors to the main page, we can also add this line to the same file. 

```
  ErrorDocument 404 /index.html
```
