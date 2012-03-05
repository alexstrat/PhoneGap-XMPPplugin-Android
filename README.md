## What is __that__ ?

_That_ is an attempt to write an XMPP plugin for PhoneGap : it borrows the simplicity 
of [strophe.js](strophe.im) API and the native XMPP implementation of [ASMACK](http://code.google.com/p/asmack).

Since the API is a shim of strophe.js one, any Strophe application or plugin should work with zero modification.

Beyond this, _that_ was a student project for the MobServ course at [EURECOM](www.eurecom.fr). Thus _that_ is more a proof of concept than a complete and finished implementation. We won't continue the development but *we encourage motivated people to fork !*

## Does it work ?

Yes, in some way ! But since this was initially developed as proof of concept, the implementation lacks some features (proper handling of connection/disconnection e.g.).

If you want to see it in action you can jump to the `hangman` branch which is an example of use to implement an P2P hangman game.

*Note*: the project's initial motivation was to bypass the XHR long-polling usage (named BOSH) made in strophe.js which is networkly inefficient and battery consuming. We thought that it will be more efficient to use a raw TCP stream (particularly in mobile environment). (We have now doubt about this.) Unfortunately we didn't have mean to test the efficiency of each solution..

## How does it work ?

On the Android side, the connection with the server is handled by ASMACK. Stanzas are build on the javascript side thanks to Strophe API, then raw stanzas are passed on the native side and directly forward to the server. The reverse path follows the same schema : once received from the server, stanzas are passed raw to the javascript side where the Strophe machinery handles it as usual.

![schema](https://raw.github.com/alexstrat/PhoneGap-XMPPplugin-Android/master/doc/schema.png)

## Installation

In your PhoneGap Android project :

- put `XMPPPhoneGapPlugin.js` and `strophe.shim.js` in your `assets/www` folder.
- put `asmack-jse.jar` in `lib` or `libs` folder and build path if needed
- put `XMPPPhoneGapPlugin.java` and `Stanza.java` in a `src/eurecom/phonegap` folder – or whereever you want if you modify accordingly `package` attribute in the code.
- modify your `res/xml/plugins.xml` to add the plugin by adding the following line :

```xml
<plugin name="XMPPPhoneGapPlugin" value="eurecom.phonegap.XMPPPhoneGapPlugin"/>
```

## API

[Strophe doc](http://strophe.benn.org/strophejs-1.0.1/doc/files/core-js.html)

## Authors

- Alexandre Lachèze ([alexstrat])
- Pierre Guilleminot ([jinroh])


[jinroh]:https//github.com/jinroh
[alexstrat]:https://github.com/alexstrat