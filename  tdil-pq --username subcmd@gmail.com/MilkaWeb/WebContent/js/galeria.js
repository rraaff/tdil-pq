
	// CONFIG
	_config_fadeTime = 500;  // tiempo que tarde en desaparecer y aparecer cada escena del header
	_config_playTime = 6000; // Cada cuanto tiempo cambian las escenas del header. Incluyen el tiempo de fade
	//_config_totalParts = 5;  // Cantidad total de escenas del header

	


	// HEADER

	function change_to_part(number) {
		if(_parte_actual == number) return;
		_parte_nueva = number;
		jQuery('#galeria_parte_'+_parte_actual).fadeOut(_config_fadeTime,function() {
			jQuery('.control').removeClass('activo');
			jQuery('.control_'+_parte_nueva).addClass('activo');
			jQuery('#galeria_parte_'+_parte_nueva).fadeIn(_config_fadeTime,function() {});
		});
		
		_parte_actual = number;
	}

	_parte_actual = 1;
	_parte_nueva = 0;

	_timer = 0;
	function next_part() {
		if(_parte_actual == _config_totalParts) change_to_part(1);
		else change_to_part(_parte_actual + 1);
	}

	function prev_part() {
		if(_parte_actual == 1) change_to_part(_config_totalParts);
		else change_to_part(_parte_actual - 1);
	}
	
	function click_on_control(part_number) {
		clearInterval(_timer);
		change_to_part(part_number);
		_timer = window.setInterval(next_part, _config_playTime);
	}

	function prepareHeader() {
		for(i=2;i<=_config_totalParts;i++)
			jQuery('#galeria_parte_'+i).hide();
	
		for(i=1;i<=_config_totalParts;i++) {
			jQuery('.control_'+i).click(function() {
				nro = 0;
				for(j=1;j<=_config_totalParts;j++) {
					if(jQuery(this).hasClass('control_'+j)) {
						nro = j;
						break;
					}
				}
				if(nro > 0)	click_on_control(nro);
			});
		
		}
	
		jQuery('.panel_galeria_next').click(function(e) {
			e.preventDefault();
			if(_parte_actual == _config_totalParts) click_on_control(1);
			else click_on_control(_parte_actual + 1);
		});
		
		jQuery('.panel_galeria_prev').click(function(e) {
			e.preventDefault();
			if(_parte_actual == 1) click_on_control(_config_totalParts);
			else click_on_control(_parte_actual - 1);			
		});
				
		_timer = window.setInterval(next_part, _config_playTime);
	}
	
	jQuery(document).ready(function() {
	   _config_totalParts = jQuery('.panel_galeria').length;
		prepareHeader();
	  
	});
	