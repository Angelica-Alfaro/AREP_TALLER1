# Escuela Colombiana de Ingeniería Julio Garavito

# Spark Web

#### Autor🙎
> - María Angélica Alfaro Fandiño

### 📜 Arquitectura y Diseño detallado

#### Diagrama de Clases

<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <img src="https://github.com/Angelica-Alfaro/AREP_TALLER1/blob/main/src/main/resources/img/ClassDiagram.PNG" alt="MI"/>
    </body>
</html>

#### Diagrama de Despliegue

<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <img src="https://github.com/Angelica-Alfaro/AREP_TALLER1/blob/main/src/main/resources/img/DeploymentDiagram.PNG" alt="MI"/>
    </body>
</html>

#### 💾 Almacenamiento en caché 
Es un HashMapConcurrent que mantiene los datos guardados de las consultas que ya se han realizado para evitar así llamados al API externo para consultas frecuentes.

<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <img src="https://github.com/Angelica-Alfaro/AREP_TALLER1/blob/main/src/main/resources/img/Cach%C3%A9.PNG" alt="MI"/>
    </body>
</html>

#### 🔎 Descripción del diseño
La aplicación está construida con el patrón de diseño Factory Method lo que la hace extensible para implementar un proveedor de servicios diferente.

<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <img src="https://github.com/Angelica-Alfaro/AREP_TALLER1/blob/main/src/main/resources/img/Factory.PNG" alt="MI"/>
    </body>
</html>

Para ampliar esta aplicación, se podría crear una clase que extienda de StockHttpConnection, ir a la clase FactoryHttpConnection, proporcionarle la información necesaria para que está decida qué objeto debe crear e implementar los métodos correspondientes.

#### 🚀 Despliegue en Heroku (Cliente Java): [![Deploy](https://www.herokucdn.com/deploy/button.svg)](http://spark-web-backend.herokuapp.com/getStockSeries?stock=MSFT&time=WeeklyTimeSeries)

#### 🚀 Despliegue en Heroku (Cliente web JS): [![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://spark-web-arep.herokuapp.com/)
