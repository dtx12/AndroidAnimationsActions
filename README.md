# Android Animations Actions
Actions for android animations. Inspired by libgdx scene2d actions.

The main goal of this project is making creating of complex animation easier.
You may create animations like below with just a couple lines of code.

#Actions
sequence - chains actions to sequence
delay - inserts pause into sequence
run - will target runnable, useful in sequences
parallel - will run actions in parallel
color - color animation for view
scaleTo - scales view to defined values
scaleBy - scales view by defined values
alpha - alpha animation
fadeIn - fade in animation
fadeOut - fade out animation
rotateTo - rotates view to defined degree
rotateBy - rotates view by defined degree
repeat - repeating of selected action
forever - infinite repeating of selected action

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
