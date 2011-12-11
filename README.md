## Installation

In your PhoneGap Android project :

- put `XMPPPhoneGapPlugin.js` in your `assets/www` folder.
- put `asmack-jse.jar` in `lib` or `libs` folder and build path if needed
- put `XMPPPhoneGapPlugin.java` in `src/[path for namespace of your choice]` folder
- modify your `res/xml/plugins.xml` to add the plugin by adding the following line :

```xml
<plugin name="XMPPPhoneGapPlugin" value="[namespace you have chosen].XMPPPhoneGapPlugin"/>
```

## API

__TBD__