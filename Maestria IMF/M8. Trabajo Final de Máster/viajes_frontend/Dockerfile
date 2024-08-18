# Usa una imagen base de Nginx para servir contenido estático
FROM nginx:alpine

# Copia los archivos HTML, CSS y JS al directorio predeterminado de Nginx
COPY ./ /usr/share/nginx/html

# Expone el puerto 80 para el servidor web
EXPOSE 80

# Comando para iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]
