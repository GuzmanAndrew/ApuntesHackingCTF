1. Se realizo un escaneo para saber la IP de la maquina victima

```
arp-scan -I eth0 --localnet
``` 

![[Pasted image 20240802194528.png]]

2. Si no se sabe cual IP es la de la victima podemos buscar la dirección MAC de donde se este corriendo la maquina que en este caso es Virtualbox

```
macchanger -l | grep -i "Oracle"
``` 

![[Pasted image 20240802194611.png]]

- Si nos damos cuenta la dirección IP 192.168.1.10 tiene en su mac **08:00** ya con eso sabemos que esa IP es nuestra maquina victima.

1. Luego se realiza un escaneo para saber que puertos estan abiertos

```
nmap -p- --open -sS --min-rate 5000 -vvv -n -Pn 192.168.1.10 -oG allPorts
``` 

![[Pasted image 20240802194928.png]]

4. Ahora se ejecuta este otro comando para poder obtener los puertos y que queden copiados en la clicboard

```
extractPorts allPorts
``` 

5. Ya teniendo esos puertos ahora se puede hacer un analisis mas detallado de ellos ejecutando con **-sC** diferentes script que tiene internamente nmap

```
nmap -sCV -p 21,22,25,80,110,4555 192.168.1.10 -oN targeted
```

 6. Para ver el archivo **targeted** podemos hacerlo asi

```
batcat -l python targeted
``` 

* Este es el resultado que nos arrojo el escaneo con la propiedad -sCV

![[Pasted image 20240802204228.png]]
![[Pasted image 20240802204306.png]]
