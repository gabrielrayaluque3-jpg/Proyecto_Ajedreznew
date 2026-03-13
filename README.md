# ♟️ Proyecto Motor de Ajedrez en Java

## 📄 Descripción

Este documento describe el proyecto a realizar durante la **segunda evaluación del módulo de Programación de 1º CFGS DAM**.

Se trata de un proyecto realizado por **equipos colaborativos (4–5 personas)** utilizando:

- **Git** como sistema de control de versiones
- **GitHub Issues** para gestión de tareas
- **Trello** para organización del trabajo
- **Metodología ágil (Scrum)**

Los **sprints se realizarán semanalmente**.

La **entrega del proyecto** la realizará el **Scrum Master**, siguiendo estrictamente las instrucciones del apartado **Entrega**.

> ⚠️ **IMPORTANTE**
>
> Cualquier entrega que **no respete las fechas o el formato** será **descalificada**, lo que supondrá el **suspenso automático de todos los miembros del equipo**.  
> **No se aceptarán entregas fuera de plazo bajo ningún concepto.**

---

# 🧠 Objetivo del Proyecto

Desarrollar un **motor simplificado de ajedrez en Java**, aplicando los siguientes conceptos de programación:

- Herencia
- Clases abstractas
- Interfaces
- Enum
- Serialización
- Colecciones
- Persistencia

---

# 🗂️ Estructura General del Proyecto

El proyecto debe organizarse en los siguientes paquetes:

```
src/
 ├── modelo
 ├── controladores
 ├── vista
 └── utils
```

---

# 📦 Paquete `modelo`

## 🎨 Colores

Se permiten dos colores:

- **Blancas**
- **Negras**

---

## ♟️ Piezas

De todas las piezas se debe almacenar:

- La **fila que ocupan** (0–7)
- La **columna que ocupan** (0–7)
- El **color al que pertenecen**

Para crear cualquier pieza se deben indicar:

- fila
- columna
- color

Se deberá validar que la posición esté entre **0 y 7**.


## Funcionalidad de las piezas

Dada una pieza, se debe permitir:

- Ser **serializable**
- Comprobar si puede **mover a una casilla**
- Comprobar si puede **atacar a otra pieza**
- **Mover a otra posición**
- Obtener **los puntos de la pieza**
- Obtener **una copia de la pieza**
- Representarse como **cadena UTF-8**

### Validaciones del movimiento

- Movimiento correcto según el tipo de pieza
- Destino dentro del tablero
- No capturar pieza del mismo color
- No haber piezas intermedias (excepto piezas saltadoras)


## Representación de piezas (UTF-8)

```
♔ ♕ ♖ ♗ ♘ ♙
♚ ♛ ♜ ♝ ♞ ♟
```

---

## Métodos obligatorios

- `equals()`
- `hashCode()`
- getters necesarios

---

# ♜ Piezas Concretas

Se deben implementar las siguientes piezas:

| Pieza | Puntos |
|------|------|
| Rey | 100 |
| Reina | 9 |
| Torre | 5 |
| Alfil | 3 |
| Caballo | 3 |
| Peón | 1 |

Cada pieza debe controlar:

- Si puede **mover**
- Si puede **atacar**
- **Puntos que vale**
- **Representación como cadena**

### Particularidades del Peón

- Puede avanzar **dos casillas en su primer movimiento**
- Es la **única pieza cuyo ataque es distinto de su movimiento**

---

# 🐎 Piezas Saltadoras

Las piezas saltadoras **no necesitan comprobar piezas intermedias**.

Actualmente:

- **Caballo**

El diseño debe permitir **añadir nuevas piezas saltadoras en el futuro**.

---

# ♟️ Tablero

El tablero debe ser **serializable**.

Debe almacenar:

- Lista de **piezas blancas activas**
- Lista de **piezas negras activas**
- Lista de **piezas eliminadas**

Al crearse, el tablero debe aparecer con **las piezas en su posición inicial**.

---

## Funcionalidad del tablero

El tablero debe permitir:

- Vaciar las piezas
- Crear **copias del tablero**
- Reiniciar el tablero
- Añadir una pieza
- Obtener la pieza de una posición
- Comprobar si hay piezas entre dos casillas
- Mover una pieza
- Comprobar **jaque**
- Obtener puntuación por color

---

## Representación del tablero

El tablero será de **8 × 8**.

Casillas vacías:

```
░  casilla blanca
▓  casilla negra
```

Casillas ocupadas:

```
UTF-8 de la pieza correspondiente
```

---

# 🛠️ Paquete `utils`

Se deberá crear:

### Clase `Utils`

Para métodos auxiliares.



# 🎮 Paquete `controladores`

Se creará un **controlador del tablero** que gestionará:

- Tablero de la partida
- Color del turno actual
- Pieza seleccionada

---

## Funcionalidad del controlador

- Iniciar aplicación
- Gestionar turnos
- Mostrar estado de la partida
- Seleccionar pieza
- Deseleccionar pieza
- Mover pieza
- Gestionar menús

---

## Información mostrada al usuario

- Tablero
- Si hay **jaque**
- Lista de piezas muertas
- Puntuación total de blancas
- Puntuación total de negras

---

# 🖥️ Paquete `vista`

Se creará una clase encargada de:

- Mostrar **menús**
- Mostrar **mensajes**
- Solicitar **datos al usuario**

La interacción será **por consola**.

---

# 🔄 Flujo de la Aplicación

## Menú principal

Al iniciar se mostrará:

- Estado de la partida
- Turno actual

Opciones:

- Seleccionar pieza
- Reiniciar tablero
- Cargar tablero
- Guardar tablero
- Salir

---

## Menú de pieza seleccionada

Opciones:

- Mover
- Cancelar

Comportamiento:

- Si se cancela → vuelve al menú principal
- Si el movimiento es incorrecto → vuelve al menú de pieza
- Si el movimiento es correcto → se actualiza el tablero y cambia el turno

---

# ⚙️ Metodología de Trabajo

Se utilizará **Scrum**.

Herramientas:

- Trello → gestión de tareas
- Git → control de versiones
- GitHub → repositorio

El **jefe de equipo** actuará también como **Scrum Master**.

Responsabilidades:

- Supervisar Pull Requests
- Gestionar daily meetings
- Organizar sprints

---

# 🗄️ Repositorio

Ramas principales:

- `main` → versión estable
- `develop` → versión en desarrollo

Configuración:

- Rama **main protegida**
- **Pull Request obligatorio**
- **Prohibido hacer push directo a main**

---

# 🌿 Ramas de Trabajo

Cada tarea debe tener su propia rama.

Ejemplos:

```
feature/movimiento-peon
feature/jaque
feature/enroque
feature/serializacion
feature/vista-menu
```

Convención de nombres:

```
feature/<nombre>   → nueva funcionalidad
bugfix/<nombre>    → corrección de errores
refactor/<nombre>  → mejora de código
```

---

# 🔁 Flujo de Trabajo por Tarea

## 1️⃣ Crear Issue en GitHub

Ejemplo:

```
#12 Implementar detección de jaque
```

Descripción clara y asignada a un miembro.

---

## 2️⃣ Crear rama desde `develop`

Las ramas **siempre** se crean desde `develop`.

Nunca desde `main`.

---

## 3️⃣ Commits pequeños y descriptivos

Formato:

```
feat: añadir método estaEnJaque en Tablero
fix: corregir validación diagonal del alfil
refactor: extraer lógica de validación
```

Prefijos:

- `feat`
- `fix`
- `refactor`

---

## 4️⃣ Pull Request hacia `develop`

El PR debe incluir:

- Descripción
- Issue relacionado
- Qué se ha hecho
- Qué falta

Ejemplo:

```
Closes #12
```

Cuando el **Scrum Master apruebe el PR → merge a develop**.

---

# 🚀 Fin del Sprint

Al finalizar un sprint:

```
develop → merge → main
```

---

# 📅 Temporalización

**Inicio del proyecto**

📅 27 de febrero de 2026

**Fecha límite de entrega**

📅 19 de marzo de 2026

**Inicio de exposiciones**

📅 20 de marzo de 2026

---

## 🏃 Sprints

| Sprint | Fechas |
|------|------|
| Sprint 1 | 27/02 - 04/03 |
| Sprint 2 | 05/03 - 09/03 |
| Sprint 3 | 10/03 - 12/03 |
| Sprint 4 | 13/03 - 18/03 |

---

# 👨‍💻 Proyecto académico

**CFGS DAM – Programación**  
Proyecto de desarrollo colaborativo con **Java + Git + Scrum**.