# Android Animations Actions
Actions for android animations. Inspired by libgdx scene2d actions.

The main goal of this project is making creating of complex animation easier.
You may create animations like below with just a couple lines of code.

#Actions
sequence - chains actions to sequence <br/>
delay - inserts pause into sequence <br/>
run - will run target runnable, useful in sequences <br/>
parallel - will run actions in parallel <br/>
color - color animation for view <br/>
scaleTo - scales view to defined values <br/>
scaleBy - scales view by defined values <br/>
alpha - alpha animation <br/>
fadeIn - fade in animation <br/>
fadeOut - fade out animation <br/>
rotateTo - rotates view to defined degree <br/>
rotateBy - rotates view by defined degree <br/>
repeat - repeating of selected action <br/>
forever - infinite repeating of selected action <br/>

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
