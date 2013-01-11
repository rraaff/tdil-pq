<script>
$(document).ready(
	function(){
		$( "#openLangSwitcher" ).click(function() {
			var top = ($(window).height() / 2) - ($( "#switchLangLayer" ).height() / 2);
			var left = ($(window).width() / 2) - ($( "#switchLangLayer" ).width() / 2);
			$( "#switchLangLayer" ).css({
				position: 'absolute',
				top: top + 'px',
				left: left + 'px'
			}).fadeIn(500);
		});
		
		$( "#closeswitchLangLayer" ).click(function() {
			$( "#switchLangLayer" ).fadeOut();
		});
	}
);
</script>
<?php 
$basePath = ereg_replace('.*/[a-z|A-Z][a-z|A-Z]/(.*)', '\1', $_SERVER['SCRIPT_NAME']);
$barCount = substr_count($basePath, '/');
$pathToRoot = '';
for ($i = 0; $i <= $barCount; $i++) {
	$pathToRoot = $pathToRoot . '../';
}
?>