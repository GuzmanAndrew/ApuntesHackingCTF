### Atajos Bspwn
Ubicación archivo configuración atajos **cat .config/sxhkd/sxhkdrc**
1. Cambio pestañas
```
windows + 1,2,3,4,5...
``` 
2. Abrir terminal
```
windows + enter
``` 
3. Cerrar ventanas
```
windows + w
``` 
4. Reiniciar el entorno
```
windows + alt + r
``` 
5. Abrir chrome
```
windows + shift + f
``` 
6. Abrir burpsuite
```
windows + shift + b
``` 
7. Mover aplicacion a una pestaña
```
windows + shift + 1,2,3,4,5...
``` 
8. Abrir rofi
```
windows + d
``` 
9. Asignar IP máquina victima
```
target IP
``` 
10. Borrar IP máquina victima
```
target reset
``` 
11. Auto colocar palabra sudo
```
hacer clic dos veces a la tecla escape
``` 
### Atajos Tmux
Ubicación archivo de configuración **cat .tmux.conf.local**
El comando principal es **ctrl + a** y lo llamaremos en este caso **principal** 
1. Crear nueva ventana
```
principal + c
``` 
2. Renombrar ventana
```
principal + ,
``` 
3. Moverse entre las ventanas
```
principal + 1,2,3,4...
``` 
4. Dividir ventana en vertical
```
principal + -
``` 
5. Dividir ventana en horizontal
```
principal + _
``` 
6. Cerra ventana
```
principal + x o exit
``` 
### Modificación TTY

1. Primer paso
```
script /dev/null -c bash
``` 
2. Despues escribimos esta combinación de teclas
```
ctrl + z
``` 
3. Luego escribimos esto
```
stty raw -echo; fg
``` 
4. Siguiente paso escribimos el reset
```
reset xterm
``` 
5. Luego seteamos la variable TERM
```
export TERM=xterm
``` 
6. Despues en una terminal de nuestro Kali Linux revisamos las filas y columnas que tenemos
```
stty size
``` 
7. Con el resultado anterior seteamos las filas y columnas de la stty de la maquina victima
```
stty rows 43 cols 167
``` 
