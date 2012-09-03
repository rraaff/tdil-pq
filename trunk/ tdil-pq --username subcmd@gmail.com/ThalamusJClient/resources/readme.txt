Esquema

WebApp -> Form -> ThalamusJClient -> (httpclient) -> Thalamus

Una vez hecho el login, se guarda en la session usuario y pasword.
Este se pasa en todos los request.

thalamus jclient va a tener un facade, con todos sus metodos.
va a haber una property de servername (de thalamus)
va a existir un archivo, o algo asi, de configuracion de los servicios, + el esquema de invocacion de los mismos (POST, GET)
La entrada a la fachada sera con JSONObjects y la salida tambien (o me conviene mapearlos a objetos de java?)

JUNIT De los servicios?