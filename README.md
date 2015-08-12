# Android Animations Actions
Actions for android animations. Inspired by libgdx scene2d actions.

The main goal of this project is making creating of complex animation easier.
You may create animations like in demo with just a couple lines of code.

#Actions
<b>sequence</b> - chains actions to sequence <br/>
<b>delay</b> - inserts pause into sequence <br/>
<b>run</b> - will run target runnable, useful in sequences <br/>
<b>parallel</b> - will run actions in parallel <br/>
<b>color</b> - color animation for view <br/>
<b>scaleTo</b> - scales view to defined values <br/>
<b>scaleBy</b> - scales view by defined values <br/>
<b>alpha</b> - alpha animation <br/>
<b>fadeIn</b> - fade in animation <br/>
<b>fadeOut</b> - fade out animation <br/>
<b>rotateTo</b> - rotates view to defined degree <br/>
<b>rotateBy</b> - rotates view by defined degree <br/>
<b>repeat</b> - repeating of selected action <br/>
<b>forever</b> - infinite repeating of selected action <br/>

# Demo
For better quality please check package demo in project.
![](http://i.imgur.com/EueRBrp.gif)

## Gradle
```java
repositories {
    jcenter()
}

dependencies {
    compile 'com.dtx12:actions:0.1.2'
}
```
