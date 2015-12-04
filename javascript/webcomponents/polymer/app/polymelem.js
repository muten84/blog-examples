define([], function(){

  var activateElement =  function($el){
    var methods = {
      start: function(){
        $el.interval = setInterval(this.update.bind($el), 1000);
      },
      stop: function(){
        $el.interval = clearInterval($el.interval);
      },
      update: function(){
        $el.textContent = new Date().toLocaleTimeString();
      }
    }
    return methods;
  };

  MyElement = Polymer({

    is: 'poly-clock',

    factoryImpl: function(foo, bar) {
      this.foo = foo;
      this.configureWithBar(bar);
    },

    created: function() {
      console.log(this.localName + '#' + this.id + ' was created');
      this.textContent = new Date().toLocaleTimeString();
    },

    ready: function() {
      // access a local DOM element by ID using this.$
      console.log(this.localName + '#' + this.id + ' is ready');
      activateElement(this).start();
    },

    attached: function() {
      console.log(this.localName + '#' + this.id + ' was attached');

    },

    detached: function() {
      console.log(this.localName + '#' + this.id + ' was detached');
    },

    attributeChanged: function(name, type) {
      console.log(this.localName + '#' + this.id + ' attribute ' + name +
      ' was changed to ' + this.getAttribute(name));
    },

    hostAttributes: {
      'string-attribute': 'Value',
      'boolean-attribute': true,
      tabindex: 0
    }
  });
});
