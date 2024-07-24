1. Primero realizo un escaneo de puerto asi:

```
$ nmap -Pn -sV 10.10.206.202
``` 
2. El resultado es el siguiente:

```
PORT   STATE SERVICE VERSION
22/tcp  closed ssh
80/tcp  open   http     Apache httpd
443/tcp open   ssl/http Apache httpd
```

