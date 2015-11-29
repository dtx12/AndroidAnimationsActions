[![Android Gems](http://www.android-gems.com/badge/dtx12/AndroidAnimationsActions.svg?branch=master)](http://www.android-gems.com/lib/dtx12/AndroidAnimationsActions)

# Android Animations Actions
Actions for android animations. Inspired by libgdx scene2d actions.

The main goal of this project is making creating of complex animations easier.
You may create animations like in demo with just a couple lines of code.

##Actions
`sequence` - executes actions sequentially <br/>
`delay` - inserts pause into sequence <br/>
`run` - will run target runnable, useful in sequences <br/>
`parallel` - executes actions at the same time <br/>
`color` - color animation for view <br/>
`scaleTo` - scales view to defined values <br/>
`scaleBy` - scales view by defined values <br/>
`alpha` - alpha animation <br/>
`fadeIn` - fades alpha to 1 <br/>
`fadeOut` - fades alpha to 0<br/>
`rotateTo` - rotates view to defined degree <br/>
`rotateBy` - rotates view by defined degree <br/>
`moveTo` - moves view to defined x, y<br/>
`moveBy` - moves view by defined x, y<br/>
`repeat` - repeats action using defined number of times <br/>
`forever` - repeats action infinite <br/>

`play` - plays specified action on specified view<br/>

##Interpolations
Library contains class `Interpolations` with different predefined interpolations which may be useful for animations.

## Demo
For better quality please check package demo in the project.
![](http://i.imgur.com/EueRBrp.gif)

## Gradle
```java
repositories {
    jcenter()
}

dependencies {
    compile 'com.dtx12:actions:0.1.4'
}
```
