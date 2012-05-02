
	// CONFIG
	_header_config_header_fadeTime = 500;  // tiempo que tarde en desaparecer y aparecer cada escena del header
	_header_config_header_playTime = 6000; // Cada cuanto tiempo cambian las escenas del header. Incluyen el tiempo de fade
	_header_config_header_totalParts = 5;  // Cantidad total de escenas del header



	// HEADER

	function change_to_part(number) {
		if(_parte_actual == number) return;
		_parte_nueva = number;
		$('#parte_'+_parte_actual).fadeOut(_header_config_header_fadeTime,function() {
			$('.control').removeClass('activo');
			$('.control_'+_parte_nueva).addClass('activo');
			$('#parte_'+_parte_nueva).fadeIn(_header_config_header_fadeTime,function() {});
		});
		
		_parte_actual = number;
	}

	_parte_actual = 1;
	_parte_nueva = 0;

	_timer = 0;
	function next_part() {
		if(_parte_actual == _header_config_header_totalParts) change_to_part(1);
		else change_to_part(_parte_actual + 1);
	}

	function click_on_control(part_number) {
		clearInterval(_timer);
		change_to_part(part_number);
		_timer = window.setInterval(next_part, _header_config_header_playTime);
	}

	function prepareHeader() {
		for(i=2;i<=_header_config_header_totalParts;i++)
			$('#parte_'+i).hide();
	
		for(i=1;i<=_header_config_header_totalParts;i++) {
			$('.control_'+i).click(function() {
				nro = 0;
				for(j=1;j<=_header_config_header_totalParts;j++) {
					if($(this).hasClass('control_'+j)) {
						nro = j;
						break;
					}
				}
				if(nro > 0)	click_on_control(nro);
			});
		
		}
		
		_timer = window.setInterval(next_part, _header_config_header_playTime);
	}
	
	$(document).ready(function() {
	   prepareHeader();
		
	});