Paso 1: Descompilar el APK y revision del codigo fuente

- Use la herramienta JADX para descompilar y ver el codigo fuente, este es el repositorio de la herramienta https://github.com/skylot/jadx y con este comando ejecuto la herramienta: jadx-gui /home/aram1r3z/Documents/AndroidApplication/AndroidApplication.apk
- listar jdk: sudo update-alternatives --config java
- Esa herramienta me genero estas carpetas:

	![[Pasted image 20240616202339.png]]

- Hay se puede obvervar el Archivo AndroidManifest.xml y la carpeta android.insecureapp que es la que  contiene el codigo o las clases principales de la aplicaciòn
- Una vez instalado el MobSF para correrlo se usa este comando ./run.sh

### Vulnerabilidades en el archivo `AndroidManifest.xml`

Analizando el archivo `AndroidManifest.xml` que has proporcionado, podemos identificar varias vulnerabilidades potenciales:

#### 1. Permisos Excesivos

La aplicación solicita varios permisos que pueden no ser necesarios para su funcionamiento básico. Cada permiso adicional aumenta la superficie de ataque.

- **Permisos sensibles solicitados**:
    - `android.permission.WRITE_EXTERNAL_STORAGE`: Permite escribir en el almacenamiento externo, lo que puede llevar a la exposición de datos sensibles si no se maneja adecuadamente.
    - `android.permission.SEND_SMS`: Permite enviar mensajes SMS, lo cual puede generar costos o problemas de privacidad si es abusado.
    - `android.permission.GET_ACCOUNTS` y `android.permission.USE_CREDENTIALS`: Permiten acceder a las cuentas del dispositivo, lo cual puede exponer información sensible del usuario.
    - `android.permission.READ_CONTACTS` y `android.permission.READ_PROFILE`: Permiten leer los contactos y el perfil del usuario, exponiendo datos personales.
    - `android.permission.READ_CALL_LOG`: Permite leer el registro de llamadas del usuario.
    - `android.permission.READ_PHONE_STATE`: Permite leer el estado del teléfono, lo cual puede incluir el número de teléfono y el estado de la red.
    - `android.permission.ACCESS_COARSE_LOCATION`: Permite acceder a la ubicación aproximada del usuario, lo cual puede usarse para rastrear al usuario.

**Recomendación**: Revisa y elimina cualquier permiso que no sea absolutamente necesario para el funcionamiento de la aplicación.

#### 2. Componentes Exportados

**Actividades Exportadas**:

- `com.android.insecureapp.PostLogin`
- `com.android.insecureapp.DoTransfer`
- `com.android.insecureapp.ViewStatement`
- `com.android.insecureapp.ChangePassword`

**Proveedores de Contenido Exportados**:

- `com.android.insecureapp.TrackUserContentProvider`

**Receptores Exportados**:

- `com.android.insecureapp.MyBroadCastReceiver`

**Riesgo**:

- Las actividades exportadas pueden ser iniciadas por otras aplicaciones, lo que puede llevar a abusos si estas actividades manejan datos sensibles o realizan acciones críticas.
- El proveedor de contenido exportado puede ser accedido por otras aplicaciones, lo que puede exponer datos sensibles.
- El receptor exportado puede recibir broadcasts de otras aplicaciones, lo que puede desencadenar acciones no deseadas.

**Recomendación**:

- Si no es necesario que estas actividades, proveedores de contenido o receptores sean accesibles desde otras aplicaciones, elimina `android:exported="true"`.
- Si es necesario exportarlos, asegúrate de implementar controles de acceso adecuados para proteger los datos y las acciones críticas.

#### 3. Depuración Habilitada

**Riesgo**:

- `android:debuggable="true"` permite que la aplicación sea depurada. Esto puede ser explotado por atacantes para acceder a información interna de la aplicación y realizar análisis más profundos de las vulnerabilidades.
- `android:allowBackup="true"` permite que los datos de la aplicación sean respaldados y restaurados. Esto puede exponer datos sensibles si no se maneja adecuadamente.

**Recomendación**:

- Elimina `android:debuggable="true"` en versiones de producción.
- Evalúa la necesidad de `android:allowBackup="true"` y asegúrate de proteger adecuadamente los datos sensibles.

### Paso a paso en Kali Linux configuracion emulador android

1. **Instalar el SDK de Android:** Descarga el SDK desde la página oficial de Android y descomprímelo:
    ![[Pasted image 20240617020657.png]]
    
2. **Actualizar el PATH:** 

	Abre tu archivo de perfil (por ejemplo, `.bashrc`):
    ![[Pasted image 20240617020742.png]]
    
    Agrega las siguientes líneas al final del archivo:
	![[Pasted image 20240617020820.png]]
    
    Guarda y cierra el archivo. Luego, recarga el archivo de perfil:
	![[Pasted image 20240617020857.png]]
    
3. **Instalar paquetes necesarios:** Usa `sdkmanager` para instalar los paquetes necesarios:

	`sdkmanager "platform-tools" "platforms;android-30" "system-images;android-30;default;x86_64" "emulator"`
    
4. **Crear y ejecutar un AVD:** 

	Usa `avdmanager` para crear un AVD:
    
    `avdmanager create avd -n my_avd -k "system-images;android-30;default;x86_64"`

	Listar maquinas:

	`emulator -list-avds`

    Luego, inicia el emulador:
    
    `emulator -avd my_avd`

### Comandos Sqlite DB apk

qlite3 mydb.db
SQLite version 3.46.0 2024-05-23 13:25:27
Enter ".help" for usage hints.
sqlite> .tables
accounts  users   
sqlite> .schema users
CREATE TABLE users (
	id INTEGER NOT NULL, 
	username VARCHAR(50), 
	password VARCHAR(50), 
	first_name VARCHAR(50), 
	last_name VARCHAR(50), 
	PRIMARY KEY (id), 
	UNIQUE (username)
);
sqlite> .schema accounts
CREATE TABLE accounts (
	id INTEGER NOT NULL, 
	account_number INTEGER, 
	type VARCHAR(50), 
	balance INTEGER, 
	user_id INTEGER, 
	user VARCHAR(50), 
	PRIMARY KEY (id), 
	UNIQUE (account_number)
);
sqlite> SELECT * FROM users;

### Vulnerabilidades Codigo:

1. Archivo CryptoClass:

	La clase `CryptoClass` presenta la vulnerabilidad de Hardcoded Secrets y esa vulnerabilidad se presenta en la clave de cifrado que está codificada directamente en el código fuente, lo que compromete la seguridad.

	![[Pasted image 20240619055042.png]]

2. Archivo DoLogin:

	Al revisar este codigo se puede observar que apunta a dos tipos de path de un endpoint que son:

	- /login
	- /devlogin

	Debajo de eso vemos una condicion y lo que hace es validar si el nombre de usuario es devadmin se usa el path de /devlogin si no entonces se usa el path /login, si observamos en el login hay un nombre de usuario hardcoded, eso me da un indicio que no se esta validando la contraseña, solo el nombre de usuario, entonces se hizo esta peticion en burpsuite

![[Pasted image 20240619153722.png]]

Hay en esa imagen se puede observar que estamos haciendo la peticion hacia el path de /devlogin y en el request del body se envia el nombre de usuario sin contraseña y como respuesta nos dice credenciales correctas, pero al hacer otra prueba con otro usuario que este registrado en la base de datos y con cualquier contraseña nos retorna el mismo mensaje de exito, esto vendria siendo una vulnerabilidad Developer Backdoor.

![[Screenshot from 2024-06-19 15-16-14.png]]

### Configuraciòn Conexiòn BurpSuite y Genymotion

1. En BurpSuite primero que todo se crea una nuevo Proxy listeners que es la IP de kali linux y el puerto 8080, a su vez descargo el certificado dando click en el boton Import/export CA certificate y le doy un un nombre y es burp.crt

![[Pasted image 20240619161622.png]]

![[Screenshot from 2024-06-19 16-10-48.png]]

2. Ahora en el emulador de Genymotion importo el certificado con este comando

![[Pasted image 20240619162326.png]]

3. En el emulador instalamos el certificado que esta en download

![[Pasted image 20240619182854.png]]

4. Por ultimo se configura el wifi del emulador para hacer peticiones a burpsuite 

	![[Pasted image 20240619183824.png]]

- Al lanzar una peticion desde la app del emulador ya burpsuite captura la peticion

![[Pasted image 20240619185027.png]]

### Análisis de aplicación móvil en dinámico

1. Vulnerabilidades de depuración de la aplicación: esta vulnerabilidad puede proporcionar acceso a una variedad de información sensible y funcionalidades internas de la aplicación y para saber si existe esa vulnerabilidad, en el archivo de AndroidManifest.xml debe estar habilitada la opcion de debug que es la siguiente

	![[Pasted image 20240619225241.png]]

- como el debug esta habilitado podemos acceder al contenido interno de la aplicación, con estas intrucciones de consola

	- Acceder a los archivos internos de la aplicación:

	![[Pasted image 20240619225651.png]]

	- Leer archivos sensibles:
	![[Pasted image 20240619225805.png]]

	- Datos almacenados en `SharedPreferences`
	![[Screenshot from 2024-06-19 22-42-23.png]]

2. Almacenamiento inseguro de datos
- He identificado que la aplicación almacena datos sensibles en `SharedPreferences` sin cifrado adecuado. Esto se puede explotar mediante acceso físico al dispositivo o mediante técnicas de extracción de datos y eso es porque en el código del `CryptoClass` esta la Clave de Cifrado Estática hardcodeada y hay un Vector de Inicialización (IV) Estático

3. Autenticación debil: Al ingresar al archivo res/values/strings.xml existe esto

	![[Pasted image 20240620002106.png]]

- Lo logico seria cambiar el valor a si y volver a genera el apk a ver si cambia el comportamiento. 

	1. Lo primero es descomprimir el APK con apktool asi
	![[Pasted image 20240620003646.png]]

	2. Modificamos el strings.xml por un yes

	![[Screenshot from 2024-06-20 00-26-00.png]]

	3. Volvemos a crear el APK con apktool usando el .jar asi

	![[Pasted image 20240620003932.png]]

	4. Se debe de crear la firma y lo hice asi
	![[Pasted image 20240620004347.png]]

	5. Una vez creada podemos firmar el APK asi:
	![[Pasted image 20240620004602.png]]

	6. Desistalamos la aplicacion y volvemos a instalar la app modificada
	![[Pasted image 20240620004915.png]]

	7. Ahora en la app hay un nuevo boton de crear usuario que esta en construccion

	![[Pasted image 20240620005136.png]]

### Bypass de Detección de Root

- En el archivo PostLogin esta es porcion de codigo
	![[Pasted image 20240620010252.png]]

#### Funcion showRootStatus:

- **Propósito**: Esta función verifica si el dispositivo está rooteado y actualiza el texto en un elemento UI (`root_status`) para indicar si el dispositivo está rooteado o no.
- **Proceso**:
    1. **Verificación de Superuser.apk**: Llama a la función `doesSuperuserApkExist` con la ruta `"/system/app/Superuser.apk"`.
    2. **Verificación de `su` binario**: Llama a la función `doesSUexist`.
    3. Si cualquiera de estas verificaciones devuelve `true`, se establece el texto `Rooted Device!!` en `root_status`. De lo contrario, se establece `Device not Rooted!!`.

#### Función doesSUexist:

- **Propósito**: Esta función verifica si el comando `su` (que se utiliza para obtener privilegios de superusuario) está presente en el sistema.
- **Proceso**:
    1. **Ejecución del comando**: Utiliza `Runtime.getRuntime().exec` para ejecutar el comando `which su`, que busca la ubicación del binario `su`.
    2. **Lectura de la salida**: Lee la salida del comando usando un `BufferedReader`. Si `which` encuentra el binario `su`, devuelve su ruta; de lo contrario, no devuelve nada.
    3. **Verificación de la salida**: Si `in.readLine()` devuelve una línea no nula, significa que el binario `su` está presente, por lo que devuelve `true`.
    4. **Manejo de excepciones**: Si ocurre una excepción durante el proceso, la función devuelve `false`.
    5. **Destrucción del proceso**: Asegura que el proceso se destruya después de completar la verificación.

- Para eludir esta detección, puedes modificar el APK de la aplicación para que estas verificaciones siempre devuelvan `false`, incluso si el dispositivo está rooteado.

- El objetivo de este Bypass es el siguiente:

	- **Razón para Bypass**: Eludir la detección de root permite al atacante acceder a funcionalidades restringidas y evadir medidas de seguridad adicionales implementadas por aplicaciones en dispositivos rooteados.
	- **Beneficio para el Atacante**: Obtener acceso completo a aplicaciones y funcionalidades que, de otra manera, estarían restringidas en dispositivos rooteados.

- Vamos abrir el archivo PostLogin.smali y buscamos el binario su
	![[Pasted image 20240620024735.png]]
- Lo vamos por remplazar por cualquier cosa para que no haga la validacion

![[Pasted image 20240620030853.png]]
- Ahora creamos el apk, lo desistalamos del emulador y lo volvemos a instalar, ya con esto se altera el comportamiento de detección de root

![[Pasted image 20240620030428.png]]

- En el dispositivo no existe el archivo Superuser.apk y es por eso que no se realiza la validacion 

![[Pasted image 20240620032238.png]]

### Bypass de Detección de Emulador

- El codigo que tiene la validacion del emulador esta en PostLogin y es este:
  ![[Pasted image 20240620173910.png]]

- Para hacer el bypass en el if returnamos un false y ya con eso cuando se inicia sesion aparecera el toast del else

![[Screenshot from 2024-06-20 16-20-22.png]]