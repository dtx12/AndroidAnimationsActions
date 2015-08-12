# Android Animations Actions
Actions for android animations. Inspired by libgdx scene2d actions.

The main goal of this project is making creating of complex animations easier.
You may create animations like in demo with just a couple lines of code.

##Actions
<b>sequence</b> - executes actions sequentially <br/>
<b>delay</b> - inserts pause into sequence <br/>
<b>run</b> - will run target runnable, useful in sequences <br/>
<b>parallel</b> - executes actions at the same time <br/>
<b>color</b> - color animation for view <br/>
<b>scaleTo</b> - scales view to defined values <br/>
<b>scaleBy</b> - scales view by defined values <br/>
<b>alpha</b> - alpha animation <br/>
<b>fadeIn</b> - fades alpha to 1 <br/>
<b>fadeOut</b> - fades alpha to 0<br/>
<b>rotateTo</b> - rotates view to defined degree <br/>
<b>rotateBy</b> - rotates view by defined degree <br/>
<b>moveTo</b> - moves view to defined x, y<br/>
<b>moveBy</b> - moves view by defined x, y<br/>
<b>repeat</b> - repeats action using defined number of times <br/>
<b>forever</b> - repeats action infinite <br/>

##Interpolations
Library contains class `Interpolations` with a different predefined interpolations which may be useful for animations.

## Demo
For better quality please check package demo in project.
![](http://i.imgur.com/EueRBrp.gif)

## Gradle
```java
repositories {
    jcenter()
}

dependencies {
    compile 'com.dtx12:actions:0.1.3'
}
```
