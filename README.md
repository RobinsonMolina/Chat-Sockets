# Chat Multicliente en Java

Este proyecto es una aplicación de chat en Java que permite a múltiples clientes conectarse a un servidor y comunicarse en tiempo real. La aplicación utiliza sockets y hilos para gestionar conexiones simultáneas de los clientes.

## Características del Proyecto
- **Servidor Multicliente**: El servidor es capaz de gestionar conexiones concurrentes con múltiples clientes, creando un hilo separado para cada cliente.
- **Comunicación en Tiempo Real**:
  - Los mensajes enviados por cualquier cliente son retransmitidos a todos los demás clientes conectados.

### `ChatServer`
Clase que gestiona las conexiones y la comunicación entre el servidor y los clientes.
- **Método `main()`**: Inicia el servidor y espera nuevas conexiones de clientes. Cada vez que un cliente se conecta, crea un nuevo hilo `ClientHandler` para manejar la comunicación con ese cliente.
- **Atributo `clientWriters`**: Un conjunto estático que almacena los `PrintWriter` de todos los clientes conectados, permitiendo enviar mensajes a todos ellos.

### `ClientHandler`
Clase que representa un hilo dedicado a cada cliente conectado.
- **Método `run()`**: Escucha continuamente los mensajes enviados por el cliente y los reenvía a todos los clientes conectados. Los mensajes se reciben a través del `BufferedReader` y se reenvían a todos los clientes utilizando el `PrintWriter` almacenado en `ChatServer.clientWriters`.
- **Manejo de errores**: Si hay un error de conexión con un cliente, el hilo correspondiente se cierra y elimina de la lista de clientes.

### `ChatClient`
Clase que permite a un usuario conectarse al servidor de chat y comunicarse.
- **Método `main()`**: Conecta al cliente con el servidor, solicita un nombre al usuario y permite la interacción de chat. Inicia un hilo para recibir mensajes continuamente del servidor. El cliente utiliza un `PrintWriter` para enviar mensajes y un `BufferedReader` para recibirlos.
  - Los mensajes se envían utilizando `out.println()` (un `PrintWriter`) al servidor, que luego los retransmite a todos los clientes conectados.
  - Los mensajes recibidos del servidor son leídos dentro de un hilo separado que escucha continuamente usando `in.readLine()` (un `BufferedReader`).

## Flujo de Comunicación
1. **Cliente a Servidor**: Los clientes envían mensajes al servidor mediante un `PrintWriter` conectado al `Socket` de su cliente.
2. **Servidor a Clientes**: El servidor recibe el mensaje de un cliente y lo retransmite a todos los demás clientes mediante los `PrintWriter` almacenados en `ChatServer.clientWriters`.


## Requisitos

- Java JDK 8 o superior

## Configuración y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/RobinsonMolina/Chat-Sockets.git
```

### 2. Ejecutar el servidor

```bash
java ChatServer.java
```

### 3. Ejecutar los clientes

```bash
java ChatClient.java
```