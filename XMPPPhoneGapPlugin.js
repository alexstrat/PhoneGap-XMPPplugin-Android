var xmpp = function() {
};

xmpp.prototype = {
  connect : function(host, port, service, successCallback, failureCallback ) {
    return PhoneGap.exec(
      successCallback,    //Success callback from the plugin
      failureCallback,     //Error callback from the plugin
      'XMPPPhoneGapPlugin',  //Tell PhoneGap to run "DirectoryListingPlugin" Plugin
      'connect',              //Tell plugin, which action we want to perform
      [host, port, service]);        //Passing list of args to the plugin
      },
      
  login : function(login, password, successCallback, failureCallback) {
    return PhoneGap.exec(
      successCallback,
      failureCallback,
      'XMPPPhoneGapPlugin',
      'login',
      [login, password]);
    },
    
    setAvailable : function(successCallback, failureCallback) {
      return PhoneGap.exec(
        successCallback,
        failureCallback,
        'XMPPPhoneGapPlugin',
        'setAvailable',
        []);
    },
    
    sendMessage : function(dest, content, successCallback, failureCallback) {
      return PhoneGap.exec(
        successCallback,
        failureCallback,
        'XMPPPhoneGapPlugin',
        'sendMessage',
        [dest, content]);
    },
    
    onMessage : function(successCallback, failureCallback) {
      return PhoneGap.exec(
        successCallback,
        failureCallback,
        'XMPPPhoneGapPlugin',
        'onMessage',
        []);
    }
  };
 
PhoneGap.addConstructor(function() {
    PhoneGap.addPlugin("xmpp", new xmpp());
});