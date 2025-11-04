# 🚀 Conversor de Monedas

**Desafío de Programación:**  
El objetivo es desarrollar un **conversor de monedas** en Java que realice solicitudes a una **API de tasas de cambio**, manipule los datos en formato **JSON** y permita **filtrar y mostrar las monedas de interés** de manera dinámica.

---

## 🧩 Tecnologías utilizadas
- **Java 17+**
- **Gson** (para parsear JSON)
- **HTTP Client (nativo de Java 11+)**
- **[API de tipo ExchangeRate](https://www.exchangerate-api.com/) / Currency API**
- **Maven** (gestión de dependencias)

---

## 🎯 Etapas del desarrollo

1. **Configuración del ambiente Java**  
   Instalación del JDK y configuración del entorno de trabajo.

2. **Creación del proyecto**  
   Estructura inicial de carpetas y paquetes.

3. **Consumo de la API**  
   Envío de solicitudes HTTP a un servicio externo de conversión de divisas.

4. **Análisis de respuesta JSON**  
   Deserialización de los datos en objetos Java mediante una clase `record`.

5. **Filtrado de monedas**  
   Implementación de una lógica para seleccionar las monedas válidas definidas en un `Enum`.

6. **Visualización de resultados**  
   Presentación de las tasas de conversión y resultados finales por consola (o interfaz).

---

## ✅ Estructura del proyecto

```
src/
 ├── entities/
 │    └── Moneda.java        // Record utilizado para mapear la respuesta JSON
 │
 ├── enums/
 │    └── MonedasDisponibles.java  // Enum con las monedas válidas para conversión
 │
 ├── menu/
 │    ├── IMenu.java         // Interfaz base del menú
 │    └── MenuPrincipal.java // Implementación concreta del menú de usuario
 │
 ├── service/
 │    ├── IConversorDeMonedas.java // Interfaz con la lógica de conversión
 │    └── ConversorDeMonedas.java  // Implementación del servicio
 │
 └── ConversorApp.java               // Clase principal del programa
```

---

## 💡 Ejemplo de uso

Al ejecutar el programa, el usuario podrá:
1. Seleccionar una moneda base (por ejemplo, **USD**).  
2. Elegir una moneda de destino (por ejemplo, **ARS**).  
3. Ingresar el monto a convertir.  
4. Recibir el valor convertido utilizando la tasa actual obtenida desde la API.

---

## 📜 Licencia
Este proyecto es de uso educativo y libre para modificar o mejorar.
