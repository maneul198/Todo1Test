# Test

## Stack principal
- **Java 15**
- **mvn**

## Instalación
1. Clonar este repositorio en la ubicacion preferida
    ```console
   $ git clone https://github.com/maneul198/
    ```

### **Ejecucion**
Segun su eleccion puede importar ambos proyectos al IDE de su preferencia
o bien ejecutarlos directamente sobre un shell, para ello ejecute sobre los directorios ./soapservice y ./restservice
```console
   mvn spring-boot:run
```
### **Nota:**
Para una correcta ejecucion debe correr tambien previamente sobre ambos directorios
```console
   mvn compile
```
a fin de obtener las clases SOAP autogeneradas (JAXB)
Ahora puede probar el servicio usando 
```console
curl 'localhost:8080/employee?name=manuel&surname=apellido&documentType=1&documentNumber=1234567&birthday=2000-03-27&hireday=2021-01-04&role=admin&salary=1000' -v
```
Si lo desea tambien puede probar el servicio SOAP de forma individual usando
```console
curl --header "content-type: text/xml" -d @request.xml http://localhost:8081/ws -v
```
sobre el directorio ./soapservice
