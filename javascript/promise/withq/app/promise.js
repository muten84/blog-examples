define(['q'], function(Q){
  var example = {};
  var fcall = Q.fcall(function () {
    console.log("invoked")
    return [{ foo: "bar" }, { foo: "baz" }];
  })
  .then(function (value) {
    console.log("processing values: "+value)
    return value[0].foo;
  });
  example.fcall = fcall;
  return example;
  //.all();
})
