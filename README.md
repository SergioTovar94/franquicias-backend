# Franquicias API

API REST reactiva para la gestión de franquicias, sucursales y productos, construida con Spring Boot WebFlux y Clean Architecture.

## Tecnologías

- **Java 21**
- **Spring Boot 3.5.14** (WebFlux, Data R2DBC)
- **PostgreSQL 16** (Neon Serverless)
- **Docker**
- **Terraform**
- **JUnit 5, Mockito, StepVerifier**

## Arquitectura

Se implementó **Clean Architecture** con enfoque de **Puertos y Adaptadores (Hexagonal)**.

- **`domain/`**: Entidades, puertos (interfaces) y servicios de dominio y excepciones de dominio. **No depende de frameworks externos.**
- **`application/`**: Casos de uso, DTOs y mappers de aplicación. Orquesta el flujo entre el dominio y la infraestructura.
- **`infrastructure/`**: Contiene los adaptadores de persistencia (puertos de salida) con Spring Data R2DBC (entidades y repositorios reactivos), los controladores REST (puertos de entrada) y el manejo global de errores.

Las dependencias apuntan hacia el dominio: `infrastructure → application → domain`.

## Requisitos Previos

- **JDK 21** (o superior)
- **Maven 3.9+** (incluye wrapper `./mvnw`)
- **Docker** (opcional, para despliegue con contenedor)
- **Cuenta gratuita en Neon** (o acceso a una BD PostgreSQL)
- **Terraform CLI** (opcional, solo para IaC).

## Configuración

La aplicación se configura mediante las siguientes variables de entorno. En `application.yml` ya están definidos placeholders con valores por defecto seguros.

| Variable                | Descripción                        |
| :---------------------- | :--------------------------------- |
| `SPRING_R2DBC_URL`      | URL de conexión R2DBC a PostgreSQL |
| `SPRING_R2DBC_USERNAME` | Usuario de la base de datos        |
| `SPRING_R2DBC_PASSWORD` | Contraseña de la base de datos     |

**Opcional:** Para desarrollo local se puede editar directamente `src/main/resources/application.yml` y colocar credenciales (el archivo contiene placeholders).

## Ejecución local (sin Docker)

1.  **Clona el repositorio**

    ```bash
    git clone https://github.com/SergioTovar94/franquicias-backend.git
    cd franquicias-backend
    ```

2.  **Configura las variables de entorno**

    ```bash
    export SPRING_R2DBC_URL=r2dbc:postgresql://[TU_HOST_NEON]:5432/neondb?sslmode=require
    export SPRING_R2DBC_USERNAME=neondb_owner
    export SPRING_R2DBC_PASSWORD=[TU_PASSWORD]
    ```

3.  Ejecuta la aplicación
    ```bash
    ./mvnw spring-boot:run
    ```

La API estará en http://localhost:8080.

### Ejecución en Docker

1.  Construir la imagen

    ```bash
    docker build -t franquicias-api .
    ```

2.  Ejecutar el contenedor

    ```bash
    docker run -p 8080:8080 \
    -e SPRING_R2DBC_URL=r2dbc:postgresql://[TU_HOST_NEON]:5432/neondb?sslmode=require \
    -e SPRING_R2DBC_USERNAME=neondb_owner \
    -e SPRING_R2DBC_PASSWORD=[TU_PASSWORD] \
    franquicias-api
    ```

    O con Docker Compose:

    ```bash
    docker-compose up
    ```

## Endpoints de la API

### Franquicias

| Método | URL                                  | Descripción                            |
| ------ | ------------------------------------ | -------------------------------------- |
| POST   | /franquicias                         | Crear una nueva franquicia             |
| PATCH  | /franquicias/{id}/nombre             | Actualizar nombre de franquicia (Plus) |
| GET    | /franquicias/{id}/producto-max-stock | Producto con más stock por sucursal    |

### Sucursales

| Método | URL                          | Descripción                          |
| ------ | ---------------------------- | ------------------------------------ |
| POST   | /franquicias/{id}/sucursales | Agregar sucursal a una franquicia    |
| PATCH  | /sucursales/{id}/nombre      | Actualizar nombre de sucursal (Plus) |

### Productos

| Método | URL                                             | Descripción                          |
| ------ | ----------------------------------------------- | ------------------------------------ |
| POST   | /sucursales/{id}/productos                      | Agregar producto a una sucursal      |
| DELETE | /sucursales/{sucursalId}/productos/{productoId} | Eliminar un producto                 |
| PATCH  | /productos/{id}/stock                           | Modificar stock de un producto       |
| PATCH  | /productos/{id}/nombre                          | Actualizar nombre de producto (Plus) |

## Pruebas unitarias

Ejecuta las pruebas con Maven:

```bash
./mvnw test
```

Se incluyen tests unitarios reactivos para los servicios de dominio y casos de uso. Se utiliza Mockito para simular los puertos y StepVerifier para verificar el comportamiento de Mono y Flux.

## Colección de Postman

En la raíz del proyecto se encuentra el archivo franquicias-api.postman_collection.json.

Importa la colección en Postman.

La variable {{base_url}} está preconfigurada para http://localhost:8080. Pero puedes cambiarla por la url desplegada en render https://franquicias-backend.onrender.com/

## Despliegue en la Nube (Render)

El backend está desplegado en render: https://franquicias-backend.onrender.com/

### Nota sobre el primer acceso

El plan gratuito de Render duerme el servicio tras 15 min de inactividad.
La primera petición puede tardar entre 30 y 90 segundos mientras el contenedor se reactiva.

**Recomendación para probar la API desplegada:**

1. Realiza una petición simple, por ejemplo: franquicias/2/producto-max-stock, y espera la respuesta.

2. Una vez que la API esté activa, todas las peticiones posteriores responderán con normalidad.

3. Usa la colección de Postman con la variable base_url apuntando a la URL de Render.

Render está conectado a la rama main del repositorio

## Infraestructura como Código (IaC) – Terraform

Se incluye un script de Terraform (terraform/main.tf) para aprovisionar la base de datos en Neon.

1. Exporta API Key de Neon

   ```bash
   export TF_VAR_neon_api_key="[TU_API_KEY]"
   ```

2. Inicializar y verificar

```
cd terraform
terraform init
terraform plan
```

El script importa la base de datos existente (no la modifica). La configuración de la base de datos queda documentada como código.

## Flujo de Trabajo con Git

- main – Rama de producción. Contiene el entregable final.
- develop – Rama principal de integración de funcionalidades.
- feature/\* – Ramas para cada bloque de tareas (ej. feature/endpoints-core, feature/unit-tests).

Los commits siguen el estándar Conventional Commits (feat:, test:, docs:, chore:, fix:).

```bash

src/main/java/com/sergio/franquicias/
├── FranquiciasApplication.java
├── application/
│   ├── dto/
│   │   ├── FranquiciaRequest.java
│   │   ├── FranquiciaResponse.java
│   │   ├── ProductoRequest.java
│   │   ├── ProductoResponse.java
│   │   ├── ProductoSucursalResponse.java
│   │   ├── SucursalRequest.java
│   │   ├── SucursalResponse.java
│   │   ├── UpdateNombreFranquiciaRequest.java
│   │   ├── UpdateNombreProductoRequest.java
│   │   ├── UpdateNombreSucursalRequest.java
│   │   └── UpdateStockRequest.java
│   ├── mapper/
│   │   ├── FranquiciaMapper.java
│   │   ├── FranquiciaRequestMapper.java
│   │   ├── FranquiciaResponseMapper.java
│   │   ├── ProductoMapper.java
│   │   ├── ProductoRequestMapper.java
│   │   ├── ProductoResponseMapper.java
│   │   ├── ProductoSucursalResponseMapper.java
│   │   ├── SucursalMapper.java
│   │   ├── SucursalRequestMapper.java
│   │   └── SucursalResponseMapper.java
│   └── usecase/
│       ├── ActualizarNombreFranquiciaUseCase.java
│       ├── ActualizarNombreProductoUseCase.java
│       ├── ActualizarNombreSucursalUseCase.java
│       ├── ActualizarStockUseCase.java
│       ├── AgregarProductoUseCase.java
│       ├── AgregarSucursalUseCase.java
│       ├── CrearFranquiciaUseCase.java
│       ├── EliminarProductoUseCase.java
│       └── ObtenerProductosMasStockUseCase.java
├── domain/
│   ├── exception/
│   │   ├── RecursoDuplicadoException.java
│   │   └── RecursoNoEncontradoException.java
│   ├── model/
│   │   ├── Franquicia.java
│   │   ├── Producto.java
│   │   ├── ProductoSucursal.java
│   │   └── Sucursal.java
│   ├── repository/
│   │   ├── FranquiciaRepositoryPort.java
│   │   ├── ProductoRepositoryPort.java
│   │   └── SucursalRepositoryPort.java
│   └── service/
│       ├── FranquiciaService.java
│       ├── ProductoService.java
│       └── SucursalService.java
└── infrastructure/
    ├── config/
    ├── persistence/
    │   ├── adapter/
    │   │   ├── FranquiciaRepositoryAdapter.java
    │   │   ├── ProductoRepositoryAdapter.java
    │   │   └── SucursalRepositoryAdapter.java
    │   ├── entity/
    │   │   ├── FranquiciaEntity.java
    │   │   ├── ProductoEntity.java
    │   │   └── SucursalEntity.java
    │   └── repository/
    │       ├── FranquiciaRepository.java
    │       ├── ProductoRepository.java
    │       └── SucursalRepository.java
    └── web/
        ├── controller/
        │   ├── FranquiciaController.java
        │   ├── ProductoController.java
        │   └── SucursalController.java
        └── handler/
            └── GlobalErrorHandler.java
```
