/* Modal Dialog to Delete Account*/
$(function() {
	$('#deletedialoginvoker').click(function(ev){
        ev.preventDefault();
		$("#deletedialog").dialog({
			bgiframe: true,
			resizable: false,
			height:140,
			modal: true,
			overlay: {
				backgroundColor: '#000',
				opacity: 0.5
			},
			buttons: {
				'Delete Account': function() {
					$(this).dialog('destroy');
					window.location=$('#deletedialoginvoker').attr('href');
				},
				Cancel: function() {
					$(this).dialog('destroy');
				}
			}

		});
	});
});

/* Modal Dialog to Disable Account*/
$(function() {
	$('#disabledialoginvoker').click(function(ev){
        ev.preventDefault();
		$("#disabledialog").dialog({
			bgiframe: true,
			resizable: false,
			height:140,
			modal: true,
			overlay: {
				backgroundColor: '#000',
				opacity: 0.5
			},
			buttons: {
				'Disable Account': function() {
					$(this).dialog('destroy');
					window.location=$('#disabledialoginvoker').attr('href');
				},
				Cancel: function() {
					$(this).dialog('destroy');
				}
			}

		});
	});
});

/* Modal Dialog to Choose Category and Keyword when Submitting */
$(function() {
	$('.voteupdialoginvoker').click(function(ev){
        var UUID = $('input',this).val();
        ev.preventDefault();
		$("#voteupdialog").dialog({
			bgiframe: true,
			height: 200,
			modal: true,
			buttons: {
				Vote: function() {
					$(this).dialog('destroy');                    
                    $('#upbeanUUID').val(UUID);                    
                    $('#voteupform').submit();
				},
				Cancel: function() {
					$(this).dialog('destroy');
				}            
			}
		});        
	});
});

/* Modal Dialog to Choose Category and Keyword when Submitting */
$(function() {
	$('.votedowndialoginvoker').click(function(ev){
        var UUID = $('input',this).val();
        ev.preventDefault();
		$("#votedowndialog").dialog({
			bgiframe: true,
			height: 200,
			modal: true,
			buttons: {
				Vote: function() {
					$(this).dialog('destroy');
                    $('#downbeanUUID').val(UUID);
                    $('#votedownform').submit();
				},
				Cancel: function() {
					$(this).dialog('destroy');
				}
			}
		});
	});
});


/* Modal Dialog to Choose Keyword when Voting in Classic Dexter */
$(function() {
	$('.dextervoteupdialoginvoker').click(function(ev){
        var UUID = $('input',this).val();
        ev.preventDefault();
		$("#dextervoteupdialog").dialog({
			bgiframe: true,
			height: 200,
			modal: true,
			buttons: {
				Vote: function() {
					$(this).dialog('destroy');
                    $('#dexterupbeanUUID').val(UUID);
                    $('#dextervoteupform').submit();
				},
				Cancel: function() {
					$(this).dialog('destroy');
				}
			}
		});
	});
});

/* Modal Dialog to Choose Keyword when Voting in Classic Dexter */
$(function() {
	$('.dextervotedowndialoginvoker').click(function(ev){
        var UUID = $('input',this).val();
        ev.preventDefault();
		$("#dextervotedowndialog").dialog({
			bgiframe: true,
			height: 200,
			modal: true,
			buttons: {
				Vote: function() {
					$(this).dialog('destroy');
                    $('#dexterdownbeanUUID').val(UUID);
                    $('#dextervotedownform').submit();
				},
				Cancel: function() {
					$(this).dialog('destroy');
				}
			}
		});
	});
});

/* Modal Dialog to Choose Label when Adding Favorite */
$(function() {
	$('.favoritedialoginvoker').click(function(ev){
        var UUID = $('input',this).val();
        ev.preventDefault();
		$("#favoritedialog").dialog({
			bgiframe: true,
			height: 170,
			modal: true,
			buttons: {
				Add: function() {
					$(this).dialog('destroy');
                    $('#favoritebeanID').val(UUID);
                    $('#favoritedialogform').submit();
				},
				Cancel: function() {
					$(this).dialog('destroy');
				}
			}
		});
	});
});