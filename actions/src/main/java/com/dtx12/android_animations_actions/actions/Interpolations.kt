package com.dtx12.android_animations_actions.actions

import android.view.animation.Interpolator

/*
 * Timing functions. Based on https://github.com/raywenderlich/SKTUtils/blob/master/SKTUtils/SKTTimingFunctions.swift
 *
 * Copyright (c) 2013-2014 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
object Interpolations {
    val QuadraticEaseIn = Interpolator { t -> t * t }
    val QuadraticEaseOut = Interpolator { t -> t * (2.0f - t) }
    val QuadraticEaseInOut = Interpolator { t ->
        if (t < 0.5) {
            2.0f * t * t
        } else {
            val f = t - 1.0f
            1.0f - 2.0f * f * f
        }
    }
    val CubicEaseIn = Interpolator { t -> t * t * t }
    val CubicEaseOut = Interpolator { t ->
        val f = t - 1.0f
        1.0f + f * f * f
    }
    val CubicEaseInOut = Interpolator { t ->
        if (t < 0.5) {
            4.0f * t * t * t
        } else {
            val f = t - 1.0f
            1.0f + 4.0f * f * f * f
        }
    }
    val QuarticEaseIn = Interpolator { t -> t * t * t * t }
    val QuarticEaseOut = Interpolator { t ->
        val f = t - 1.0f
        1.0f - f * f * f * f
    }
    val QuarticEaseInOut = Interpolator { t ->
        if (t < 0.5) {
            8.0f * t * t * t * t
        } else {
            val f = t - 1.0f
            1.0f - 8.0f * f * f * f * f
        }
    }
    val FunctionQuinticEaseIn = Interpolator { t -> t * t * t * t * t }
    val QuinticEaseOut = Interpolator { t ->
        val f = t - 1.0f
        1.0f + f * f * f * f * f
    }
    val QuinticEaseInOut = Interpolator { t ->
        if (t < 0.5) {
            16.0f * t * t * t * t * t
        } else {
            val f = t - 1.0f
            1.0f + 16.0f * f * f * f * f * f
        }
    }
    val SineEaseIn = Interpolator { t -> (Math.sin((t - 1.0f) * Math.PI / 2) + 1.0f).toFloat() }
    val SineEaseOut = Interpolator { t -> Math.sin(t * Math.PI / 2).toFloat() }
    val SineEaseInOut = Interpolator { t -> (0.5f * (1.0f - Math.cos(t * Math.PI))).toFloat() }
    val CircularEaseIn = Interpolator { t -> (1.0f - Math.sqrt(1.0 - t * t)).toFloat() }
    val CircularEaseOut = Interpolator { t -> Math.sqrt((2.0f - t) * t.toDouble()).toFloat() }
    val CircularEaseInOut = Interpolator { t ->
        if (t < 0.5) {
            (0.5 * (1.0 - Math.sqrt(1.0 - 4.0 * t * t))).toFloat()
        } else {
            (0.5 * Math.sqrt(-4.0 * t * t + 8.0 * t - 3.0) + 0.5).toFloat()
        }
    }
    val ExponentialEaseIn = Interpolator { t -> if (t.toDouble() == 0.0) t else Math.pow(2.0, 10.0 * (t - 1.0)).toFloat() }
    val ExponentialEaseOut = Interpolator { t -> if (t.toDouble() == 1.0) t else (1.0 - Math.pow(2.0, -10.0 * t)).toFloat() }
    val ExponentialEaseInOut = Interpolator { t ->
        if (t.toDouble() == 0.0 || t.toDouble() == 1.0) {
            t
        } else if (t < 0.5) {
            (0.5 * Math.pow(2.0, 20.0 * t - 10.0)).toFloat()
        } else {
            (1.0 - 0.5 * Math.pow(2.0, -20.0 * t + 10.0)).toFloat()
        }
    }
    val ElasticEaseIn = Interpolator { t -> (Math.sin(13.0 * Math.PI / 2 * t) * Math.pow(2.0, 10.0 * (t - 1.0))).toFloat() }
    val ElasticEaseOut = Interpolator { t -> (Math.sin(-13.0 * Math.PI / 2 * (t + 1.0)) * Math.pow(2.0, -10.0 * t) + 1.0).toFloat() }
    val ElasticEaseInOut = Interpolator { t ->
        if (t < 0.5) {
            (0.5 * Math.sin(13.0 * Math.PI * t) * Math.pow(2.0, 20.0 * t - 10.0)).toFloat()
        } else {
            (0.5 * Math.sin(-13.0 * Math.PI * t) * Math.pow(2.0, -20.0 * t + 10.0) + 1.0).toFloat()
        }
    }
    val BackEaseIn = Interpolator { t ->
        val s = 1.70158f
        ((s + 1.0f) * t - s) * t * t
    }
    val BackEaseOut = Interpolator { t ->
        val s = 1.70158f
        val f = 1.0f - t
        1.0f - ((s + 1.0f) * f - s) * f * f
    }
    val BackEaseInOut = Interpolator { t ->
        val s = 1.70158f
        if (t < 0.5) {
            val f = 2.0f * t
            0.5f * ((s + 1.0f) * f - s) * f * f
        } else {
            val f = 2.0f * (1.0f - t)
            1.0f - 0.5f * ((s + 1.0f) * f - s) * f * f
        }
    }
    val ExtremeBackEaseIn = Interpolator { t -> ((t * t - Math.sin(t * Math.PI)) * t).toFloat() }
    val ExtremeBackEaseOut = Interpolator { t ->
        val f = 1.0f - t
        (1.0 - (f * f - Math.sin(f * Math.PI)) * f).toFloat()
    }
    val ExtremeBackEaseInOut = Interpolator { t ->
        if (t < 0.5) {
            val f = 2.0f * t
            (0.5 * (f * f - Math.sin(f * Math.PI)) * f).toFloat()
        } else {
            val f = 2.0f * (1.0f - t)
            (1.0 - 0.5 * (f * f - Math.sin(f * Math.PI)) * f).toFloat()
        }
    }
    val BounceEaseOut = Interpolator { t ->
        if (t < 1.0 / 2.75) {
            7.5625f * t * t
        } else if (t < 2.0 / 2.75) {
            val f = t - 1.5f / 2.75f
            7.5625f * f * f + 0.75f
        } else if (t < 2.5 / 2.75) {
            val f = t - 2.25f / 2.75f
            7.5625f * f * f + 0.9375f
        } else {
            val f = t - 2.625f / 2.75f
            7.5625f * f * f + 0.984375f
        }
    }
    val BounceEaseIn: Interpolator = object : Interpolator {
        override fun getInterpolation(t: Float): Float {
            return 1.0f - getInterpolation(1.0f - t)
        }
    }
    val BounceEaseInOut: Interpolator = object : Interpolator {
        override fun getInterpolation(t: Float): Float {
            return if (t < 0.5) {
                0.5f * getInterpolation(t * 2.0f)
            } else {
                0.5f * getInterpolation(t * 2.0f - 1.0f) + 0.5f
            }
        }
    }
    val SmoothStep = Interpolator { t -> t * t * (3 - 2 * t) }
}