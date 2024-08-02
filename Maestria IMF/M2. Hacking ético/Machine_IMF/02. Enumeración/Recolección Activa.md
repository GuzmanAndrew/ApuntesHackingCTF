1. Se realizo un escaneo para saber la IP de la maquina victima
```
arp-scan -I eth0 --localnet
``` 
2. Si no se sabe cual IP es la de la victima podemos buscar la dirección MAC de donde se este corriendo la maquina que en este caso es Virtualbox
```
macchanger -l | grep -i "Oracle"
``` 
3. Luego se realiza un escaneo para saber que puertos estan abiertos
```
nmap -p- --open -sS --min-rate 5000 -vvv -n -Pn 192.168.1.6 -oG allPorts
``` 
4. Ahora se ejecuta este otro comando para poder obtener los puertos y que queden copiados en la clicboard
```
extractPorts allPorts
``` 
5. Ya teniendo esos puertos ahora se puede hacer un analisis mas detallado de ellos
```
nmap -sCV -p 21,22,25,80,110,119,4555 192.168.1.11 -oN targeted
``` 
