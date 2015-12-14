define(['q'], function(Q){
  var example = {}
  var get = function (url) {
      var request = new XMLHttpRequest();
      var deferred = Q.defer();

      request.open("GET", url, true);
      request.onload = onload;
      request.onerror = onerror;
      request.onprogress = onprogress;
      request.send();

      function onload() {
          if (request.status === 200) {
              deferred.resolve(request.responseText);
          } else {
              deferred.reject(new Error("Status code was " + request.status));
          }
      }

      function onerror() {
          deferred.reject(new Error("Can't XHR " + JSON.stringify(url)));
      }

      function onprogress(event) {
          deferred.notify(event.loaded / event.total);
      }

      return deferred.promise;
  }
  example.get = get;
  return example;
})
