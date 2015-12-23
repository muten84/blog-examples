if (typeof define !== 'function') {
   // Assume we're in the Node.js environment
   // In this case, load the define function via amdefine
   console.log("define is not a function require with amdefine....");
   var define = require('amdefine')(module);
}

define(["express"], function (express) {
  // var listen = function (){
  //   console.log("listen called...");
  // }
  // var app = {};
  // app.listen = listen;
  // return app;
  console.log('Express from define: ' +express);
  var app = express();
  console.log('App from define: ' +app);

  // var express = require("express");
  // console.log('Express from node: '+express);
  // var app = express();
  // console.log('App from node: ' +app);

  app.use('/', express.static('./'));

  var port = process.env.PORT || 5000;
  app.listen(port, function() {
    console.log("Listening on " + port);
  });

  return app;
});
