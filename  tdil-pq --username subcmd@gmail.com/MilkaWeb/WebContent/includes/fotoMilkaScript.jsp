function postUploadFotoMilka(data) {
	$( "#dialog-form" ).dialog("close" );
	if (data.result == 'OK') {
		$("input[name='authorBean.name']").attr('value', '');
		$("input[name='authorBean.email']").attr('value', '');
		$("input[name='authorBean.acceptPolitics']").attr('checked', false);
		$( "#dialog-modal" ).dialog({
			height: 140,
			modal: true
		});
	} else {
		$( "#dialog-modal-err" ).dialog({
				height: 140,
				modal: true
			});
	}
}