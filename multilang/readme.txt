Soporte de cambio de lenguage

Setup
1) Agregar el directorio includes completo
2) Agregar en el root el archivo langSwitcher.php
3) En cada directorio de idioma agregar el archivo langSwitcherBody.php, arreglandolo visualmente, este
	archivo contiene el layer de cambio
4) Agregar el archivo js/jquery-1.7.min.js

Uso
1) Incluir en el head:
<script type='text/javascript' src='../js/jquery-1.7.min.js'></script>
<?php include '../includes/langSwitcherHead.php';?>
Debe llevar los suficientes ../ para llegar al directorio superior al idioma

2) Incluir en el body:
<?php include './langSwitcherBody.php';?>
Este include esta en cada directorio de idioma, si el archivo a traducir no esta en el mismo directorio,
agregar los correspondientes ../

3) Agregar un elemento con id openLangSwitcher que sera el que abrira el layer de cambio
Se puede utilizar un include para que todos los archivos dentro de un idioma tengan el mismo disparador

4) Arreglar visualmente el layer de cambio langSwitcherBody.php dentro de cada directorio de idioma

5) Agregar las equivalencias de paginas en includes/equivalencias.csv
Las paginas deben tener el path relativo a su idioma (ver e ejemplo)

Restricciones
El directorio del lenguage debe tener dos letras, mayusculas o minusculas

Prueba
Entrar al index.php del root, y luego en cada archivo que se entra se tiene la opcion de verlo en otro 
idioma. 

Horas 5
