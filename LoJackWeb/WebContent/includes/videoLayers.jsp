<script>

function showVideo1(strVideo) {
	if (strVideo == 'car') {
		$('#videoIframe').prop('src', '<%=com.tdil.lojack.utils.LoJackConfig.getVideocar()%>');
		$('#videoTitle').prop('innerHTML', 'Lo Jack Car');
		$('#videoDescription').prop('innerHTML', 'Control� tu auto o tu flota. Podr�s saber d�nde est�n, las velocidades m�ximas.');
	}
	if (strVideo == 'home') {
		$('#videoIframe').prop('src', '<%=com.tdil.lojack.utils.LoJackConfig.getVideohome()%>');
		$('#videoTitle').prop('innerHTML', 'Lo Jack Home');
		$('#videoDescription').prop('innerHTML', 'Control� tus alarmas, luces y c�maras. Manten� el control de tu casa.');
	}
	if (strVideo == 'pets') {
		$('#videoIframe').prop('src', '<%=com.tdil.lojack.utils.LoJackConfig.getVideopets()%>');
		$('#videoTitle').prop('innerHTML', 'Lo Jack Pets');
		$('#videoDescription').prop('innerHTML', 'Ten� a tus mascotas monitoreadas.');
	}
	if (strVideo == 'loapp') {
		$('#videoIframe').prop('src', '<%=com.tdil.lojack.utils.LoJackConfig.getVideoloapp()%>');
		$('#videoTitle').prop('innerHTML', 'Lo App');
		$('#videoDescription').prop('innerHTML', '');
	}
	centerLayer($(window), $( "#video1Layer" ));
	centerLayer($(window), $( "#centradorModalesVideo1Layer" ));
}

</script>

<div id="video1Layer" class="layerOnTop" style="display: none; z-index: 2500;">
	<div id="centradorModalesVideo1Layer" class="defaultLayerStyles">
		<div class="videoLayer">
			<div class="closeLayerVideoLink"><button title="Cerrar video" class="btn btn-link customLink" id="closevideo1Layer">< volver</button></div>
			<div id="videoWrapper">
				<iframe id="videoIframe" src="<%=com.tdil.lojack.utils.LoJackConfig.getVideocar()%>" frameborder="0" allowscale="false" allowfullscreen></iframe>
			</div>
			<div id="footerizer">
				<div class="col1_300 marginRight_60">
					<h3 id="videoTitle">Bienvenido a Lo-Life</h3>
					<p id="videoDescription">texto.</p>
					<!--  button class="btn btn-mini btn-primary" type="button">Mas info >></button-->
				</div>
			</div>
		</div>
	</div>
</div>