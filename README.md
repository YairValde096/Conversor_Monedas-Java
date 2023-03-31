# Programa de conversión de divisas

Este es un programa de consola que permite convertir entre diferentes divisas de moneda utilizando los valores actuales de la API de apilayer. La información de las divisas se almacena en una base de datos MySQL local para reducir la cantidad de solicitudes a la API.

# Requerimientos
Para ejecutar este programa, necesitarás:

*Java SE 1.8 o superior
*Una conexión a internet para actualizar los valores de la API de apilayer
*Una conexión a una base de datos MySQL para almacenar los valores de las divisas
# Instalación
Descarga el archivo .jar del programa en tu ordenador.
Crea una base de datos MySQL en tu servidor local(La configuración de la base de datos es la local por defecto).
Ejecuta el archivo .jar en tu terminal o consola.
# Uso
Al ejecutar el programa, se podrá interactuar con los ComboBox para elegir el pais, Los JSpinner servirán para interactuar con las cantidades.
El programa mostrará la cantidad de dinero equivalente en la moneda de destino.
También se mostrará la bandera del país correspondiente a la moneda de origen y la bandera del país correspondiente a la moneda de destino, obtenidas de la API bandera.xyz.
Notas adicionales
Este programa consume la API de apilayer para obtener los valores de las divisas, por lo que es necesario tener una conexión a internet para actualizar los valores de la API.
Si es posible, el sistema se mantendrá actualizado segun la fecha del sistema.
