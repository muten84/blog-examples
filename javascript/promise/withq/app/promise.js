define(['q'], function(Q){
  console.log("Q loaded: "+Q);
  Q.fcall(function () {
    console.log("invoked")
    return [{ foo: "bar" }, { foo: "baz" }];
  })
  .then(function (value) {
    console.log("processing values: "+value)
    return value[0].foo;
  })
  .all();

  
  //Q.all(promise);
})
