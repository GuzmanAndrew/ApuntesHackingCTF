- La maquina virtual nos dice la version del ubuntu

![[Pasted image 20240803193159.png]]

- En internet al buscar un exploit se encontro este de la platafoma **exploit-db.com** 

![[Pasted image 20240803193424.png]]

- Ese exploit esta en lenguaje de programacion C, por lo tanto se debe de compilar pero no se debe de compilar en kali ya que mi version del kernel es diferente y el exploit es para versiones del kernel menor a la 4.4.0-116. Si no se compila en un Linux con un kernel menor podria generar un error de incompatibilidad de versión de la biblioteca **glibc**

![[Pasted image 20240803193727.png]]

- Se instalo una maquina virtual con una version menor de Ubuntu de la maquina victima para poder hacer la compilacion

![[Pasted image 20240803200531.png]]

- Se descarga el archivo del exploit en esa maquina de ubuntu con **wget**

![[Pasted image 20240803201704.png]]

- Una vez descargado se procede a realiazar la compilacion de esta manera

![[Pasted image 20240803201739.png]]

- Cuando el archivo quede compilado se ejecuta un servidor con python en el puerto 8000 para poder descargar el archivo compilado en la maquina victima

![[Pasted image 20240803202144.png]]

- Con la herramienta **wget** descargamos el archivo compilado en la maquina victima

![[Pasted image 20240803202433.png]]

- Se revisa si ese archivo descargado si sea un ELB y para comprobar se hace con la herramienta **file**

![[Pasted image 20240803202643.png]]

- Se procede a dar permisos de ejecucion antes de ejecutarlo 

![[Pasted image 20240803202756.png]]

- Ya con los permisos de ejecucion se procede a ejecutarlo y se obtiene una shell de root

![[Pasted image 20240803202919.png]]

- Por ultimo se ingresa a la carpeta root y se observa un archivo txt con la ultima flag

![[Pasted image 20240803203048.png]]

```
FLAG{YEAH_SETUID_FILES_RuL3S}
``` 
### Segunda opción para el exploit

![[Pasted image 20240804024904.png]]
