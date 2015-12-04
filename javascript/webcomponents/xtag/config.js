require.config({
		paths: {
				'x-tag-core': 'public/components/x-tag-core/dist/x-tag-core',
				'xclock': 'app/xclock'
		},
		shim: {

		},
		packages: [

		]
});

//require our angularApp for bootstrapping it when DOM ready function was called!
require(
		['x-tag-core', 'app/xclock'],		function() {
			'use strict';
			console.log("Application loaded.... " + app);
		});
