<script>

function showVideo1(strVideo) {
	centerLayer($(window), $( "#video1Layer" ));
	centerLayer($(window), $( "#centradorModalesVideo1Layer" ));
}

</script>

<div id="video1Layer" class="layerOnTop70" style="display: none; z-index: 1500;">
	<div id="centradorModalesVideo1Layer" class="defaultLayerStyles">
		<div class="videoLayer">
			<div class="closeLayerVideoLink"><button title="Cerrar video" class="btn btn-link customLink" id="closevideo1Layer" cl="video1Layer">< volver</button></div>
			<div id="videoWrapper">
				<!--  NECESITO UN IF ELSE LOCO QUE ESCRIBA EL IFRAME CORRESPONDIENTE. EN LA FUNCIÓN DE JAVASCRIPT ESTOY MANDANDO UN PARÁMETRO DENTRO DE LA LLAMADA Y LO ATAJO CON strVideo -->
				<iframe id="videos" src="http://www.youtube.com/embed/5Xe5pODPq1I" frameborder="0" allowscale="false" allowfullscreen></iframe>
				<!-- iframe id="videos" src="http://www.youtube.com/embed/Iz_VvsFwXQI" frameborder="0" allowscale="false" allowfullscreen></iframe>
				<iframe id="videos" src="http://www.youtube.com/embed/M8VhrMM0j-Q" frameborder="0" allowscale="false" allowfullscreen></iframe>
				<iframe id="videos" src="http://www.youtube.com/embed/G18ElDg9s-o" frameborder="0" allowscale="false" allowfullscreen></iframe-->
			</div>
			<div id="footerizer">
				<div class="col1_300 marginRight_60">
					<h3>Bienvenido a Lo-Life</h3> 	<!-- titulo también dinámico -->
					<p>texto.</p>					<!-- texto dinámico -->
					<!--  button class="btn btn-mini btn-primary" type="button">Mas info >></button-->
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 
car: http://www.youtube.com/embed/5Xe5pODPq1I
Home: http://www.youtube.com/embed/Iz_VvsFwXQI
Pets: http://www.youtube.com/embed/M8VhrMM0j-Q
LOAPP: http://www.youtube.com/embed/G18ElDg9s-o
????
-->