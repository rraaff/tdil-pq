<script>

function showVideo1() {
	centerLayer($(window), $( "#video1Layer" ));
}

</script>

<div id="video1Layer" class="layerOnTop70" style="display: none; z-index: 1500;">
	<div class="defaultLayerStyles">
		<div class="videoLayer">
			<div class="closeLayerVideoLink"><button title="Cerrar video" class="btn btn-link customLink" id="closevideo1Layer" cl="video1Layer">< volver</button></div>
			<div id="videoWrapper">
				<iframe id="videos" src="http://www.youtube.com/embed/WT4DhyArmdg" frameborder="0" allowscale="false" allowfullscreen></iframe>
				<!-- video id="videos">
					<source src="http://www.youtube.com/embed/WT4DhyArmdg" type="video/mp4; codecs='avc1,mp4a'" />
					<source src="http://www.youtube.com/embed/WT4DhyArmdg" type="video/webm; codecs='vp8,vorbis'" />
				</video-->
			</div>
			<div id="footerizer">
				<div class="col1_300 marginRight_60">
					<h3>Video Title</h3>
					<p>Con LoJack for Laptopts sabés que si te roban la computadora, te la encontramos.</p>
					<button class="btn btn-mini btn-primary" type="button">Mas info >></button>
				</div>
			</div>
		</div>
	</div>
</div>