 var requirejs = require('requirejs');
//
requirejs.config({
    paths: {
         server: 'server'
     },
    //Pass the top-level main.js/index.js require
    //function to requirejs so that node modules
    //are loaded relative to the top-level JS file.
    nodeRequire: require,
    //Use node's special variable __dirname to
    //get the directory containing this file.
    //Useful if building a library that will
    //be used in node but does not require the
    //use of node outside
    //baseUrl: __dirname,
});

requirejs(["server/simpleserver"],function(app) {
  console.log("application loaded...");
   app.listen(8000);
 });
