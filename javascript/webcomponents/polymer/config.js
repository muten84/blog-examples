require.config({
	paths: {
		'polymelem': 'app/polymelem'
	},
	shim: {

	},
	packages: [

	]
});

//require our angularApp for bootstrapping it when DOM ready function was called!
require(['polymelem'],function() {
		'use strict';
		console.log("Application loaded.... ");
	});
