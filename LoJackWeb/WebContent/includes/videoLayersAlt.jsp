<script>
function getYouTubeUrl(youtubeId) {
	var YouTubeURL = "http://www.youtube.com/embed/" + youtubeId + "?rel=0&showsearch=0&autohide=2";
	YouTubeURL += "&autoplay=1&controls=1&fs=1&loop=0";
	YouTubeURL += "&showinfo=0&color=red&theme=light";
	return YouTubeURL;
}

function getYouTubePlayer(URL, width, height) {
    var YouTubePlayer = '<iframe title="YouTube video player" style="margin:0; padding:0;" width="' + width + '" ';
    YouTubePlayer += 'height="' + height + '" src="' + URL + '" frameborder="0" allowfullscreen></iframe>';
    return YouTubePlayer;
}

function showVideo1(strVideo) {
	if (strVideo == 'car') {
		$('#videoIframe').prop('innerHTML', getYouTubePlayer(getYouTubeUrl('5Xe5pODPq1I'), 640, 480));
		$('#videoTitle').prop('innerHTML', 'Lo Jack Car');
		$('#videoDescription').prop('innerHTML', 'Controlá tu auto o tu flota. Podrás saber dónde están, las velocidades máximas.');
	}
	if (strVideo == 'home') {
		$('#videoIframe').prop('innerHTML', getYouTubePlayer(getYouTubeUrl('Iz_VvsFwXQI'), 640, 480));
		$('#videoTitle').prop('innerHTML', 'Lo Jack Home');
		$('#videoDescription').prop('innerHTML', 'Controlá tus alarmas, luces y cámaras. Mantené el control de tu casa.');
	}
	if (strVideo == 'pets') {
		$('#videoIframe').prop('innerHTML', getYouTubePlayer(getYouTubeUrl('M8VhrMM0j-Q'), 640, 480));
		$('#videoTitle').prop('innerHTML', 'Lo Jack Pets');
		$('#videoDescription').prop('innerHTML', 'Tené a tus mascotas monitoreadas.');
	}
	if (strVideo == 'loapp') {
		$('#videoIframe').prop('innerHTML', getYouTubePlayer(getYouTubeUrl('G18ElDg9s-o'), 640, 480));
		$('#videoTitle').prop('innerHTML', 'Lo App');
		$('#videoDescription').prop('innerHTML', '');
	}
	centerLayer($(window), $( "#video1Layer" ));
	centerLayer($(window), $( "#centradorModalesVideo1Layer" ));
}

</script>

<div id="video1Layer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesVideo1Layer" class="defaultLayerStyles">
		<div class="videoLayer">
			<div class="closeLayerVideoLink"><button title="Cerrar video" class="btn btn-link customLink" id="closevideo1Layer">< volver</button></div>
			<div id="videoWrapper">
				<div id="videoIframe"></div>
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