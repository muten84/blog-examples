require.config({
	paths: {
		promise: 'app/promise',
		promiseHXR: 'app/promiseXHR',
		q: 'public/components/q/q'
	},
	shim: {

	},
	packages: [

	]
});

//require our angularApp for bootstrapping it when DOM ready function was called!
require(
	['app/promise'],	function() {
		'use strict';
		console.log("Application loaded.... ");
	});

	require(['app/promiseXHR'],	function(example) {
		'use strict';
		example.get("http://localhost:1337/camera/camera_control.cgi?loginuse=&loginpas=")
		.then(function (responseText) {
			// If the HTTP response returns 200 OK, log the response text.
			console.log(responseText);
		}, function (error) {
			// If there's an error or a non-200 status code, log the error.
			console.error(error);
		}, function (progress) {
			// Log the progress as it comes in.
			console.log("Request progress: " + Math.round(progress * 100) + "%");
		});
	});
