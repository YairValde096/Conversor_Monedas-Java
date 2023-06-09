# Programa de conversión de divisas

Este es un programa de consola que permite convertir entre diferentes divisas de moneda utilizando los valores actuales de la API de apilayer. La información de las divisas se almacena en una base de datos MySQL local para reducir la cantidad de solicitudes a la API.

# Requerimientos
Para ejecutar este programa, necesitarás:

*Java SE 1.8 o superior.

*Una conexión a internet para actualizar los valores de la API de apilayer.

*Una conexión a una base de datos MySQL para almacenar los valores de las divisas. es posible que efectues la instalación del driver de JDBC el utilizado fue mysql-connector-j-8.0.32.

*Es necesario que cuentes con una clave de la API "Exchange Rates Data API". Puedes obtenerla desde el siguiente link:
https://apilayer.com/marketplace/exchangerates_data-api . la clave y contraseña deberásn estar almaenados en la carpeta resources, con el nombre pass.txt (o modificar el archivo getPass.java).

*Es necesario crear una base de datos MySQL(La configuración de la base de datos es la local por defecto, esto puede ser modificado en MySQLconector.java) la base de datos deberá ser llamada "tabla_conversion". Importar las tablas.

# Instalación
*Copia el proyecto.

*Haz la instalación del conector a la base de datos (Si es que seguiste los requerimientos).


# Uso
Al ejecutar el programa, se podrá interactuar con los ComboBox para elegir el pais, Los JSpinner servirán para interactuar con las cantidades.
El programa mostrará la cantidad de dinero equivalente en la moneda de destino.
También se mostrará la bandera del país correspondiente a la moneda de origen y la bandera del país correspondiente a la moneda de destino, obtenidas de la API bandera.xyz.
![Gifs.gif](https://github.com/YairValde096/Conversor_Monedas-Java/blob/main/resources/images/Gifs.gif)
Notas adicionales
Este programa consume la API de apilayer para obtener los valores de las divisas, por lo que es necesario tener una conexión a internet para actualizar los valores de la API.
Si es posible, el sistema se mantendrá actualizado segun la fecha del sistema.

