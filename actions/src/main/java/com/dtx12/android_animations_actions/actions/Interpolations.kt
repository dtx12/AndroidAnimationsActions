package com.dtx12.android_animations_actions.actions

import android.view.animation.Interpolator
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

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
sealed class Interpolations : Interpolator {

    object QuadraticEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = t * t
    }

    object QuadraticEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = t * (2.0f - t)
    }

    object QuadraticEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t < 0.5) {
            2.0f * t * t
        } else {
            val f = t - 1.0f
            1.0f - 2.0f * f * f
        }
    }

    object CubicEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = t * t * t
    }

    object CubicEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float {
            val f = t - 1.0f
            return 1.0f + f * f * f
        }
    }

    object CubicEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t < 0.5) {
            4.0f * t * t * t
        } else {
            val f = t - 1.0f
            1.0f + 4.0f * f * f * f
        }
    }

    object QuarticEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = t * t * t * t
    }

    object QuarticEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float {
            val f = t - 1.0f
            return 1.0f - f * f * f * f
        }
    }

    object QuarticEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t < 0.5) {
            8.0f * t * t * t * t
        } else {
            val f = t - 1.0f
            1.0f - 8.0f * f * f * f * f
        }
    }

    object FunctionQuinticEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = t * t * t * t * t
    }

    object QuinticEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float {
            val f = t - 1.0f
            return 1.0f + f * f * f * f * f
        }
    }

    object QuinticEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t < 0.5) {
            16.0f * t * t * t * t * t
        } else {
            val f = t - 1.0f
            1.0f + 16.0f * f * f * f * f * f
        }
    }

    object SineEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = (sin((t - 1.0f) * Math.PI / 2) + 1.0f).toFloat()
    }

    object SineEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = sin(t * Math.PI / 2).toFloat()
    }

    object SineEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = (0.5f * (1.0f - cos(t * Math.PI))).toFloat()
    }

    object CircularEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = (1.0f - sqrt(1.0 - t * t)).toFloat()
    }

    object CircularEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = sqrt((2.0f - t) * t.toDouble()).toFloat()
    }

    object CircularEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t < 0.5) {
            (0.5 * (1.0 - sqrt(1.0 - 4.0 * t * t))).toFloat()
        } else {
            (0.5 * sqrt(-4.0 * t * t + 8.0 * t - 3.0) + 0.5).toFloat()
        }
    }

    object ExponentialEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t.toDouble() == 0.0) t else 2.0.pow(10.0 * (t - 1.0)).toFloat()
    }

    object ExponentialEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t.toDouble() == 1.0) t else (1.0 - 2.0.pow(-10.0 * t)).toFloat()
    }

    object ExponentialEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t.toDouble() == 0.0 || t.toDouble() == 1.0) {
            t
        } else if (t < 0.5) {
            (0.5 * 2.0.pow(20.0 * t - 10.0)).toFloat()
        } else {
            (1.0 - 0.5 * 2.0.pow(-20.0 * t + 10.0)).toFloat()
        }
    }

    object ElasticEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = (sin(13.0 * Math.PI / 2 * t) * 2.0.pow(10.0 * (t - 1.0))).toFloat()
    }

    object ElasticEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = (sin(-13.0 * Math.PI / 2 * (t + 1.0)) * 2.0.pow(-10.0 * t) + 1.0).toFloat()
    }

    object ElasticEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t < 0.5) {
            (0.5 * sin(13.0 * Math.PI * t) * 2.0.pow(20.0 * t - 10.0)).toFloat()
        } else {
            (0.5 * sin(-13.0 * Math.PI * t) * 2.0.pow(-20.0 * t + 10.0) + 1.0).toFloat()
        }
    }

    object BackEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float {
            val s = 1.70158f
            return ((s + 1.0f) * t - s) * t * t
        }
    }

    object BackEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float {
            val s = 1.70158f
            val f = 1.0f - t
            return 1.0f - ((s + 1.0f) * f - s) * f * f
        }
    }

    object BackEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float {
            val s = 1.70158f
            return if (t < 0.5) {
                val f = 2.0f * t
                0.5f * ((s + 1.0f) * f - s) * f * f
            } else {
                val f = 2.0f * (1.0f - t)
                1.0f - 0.5f * ((s + 1.0f) * f - s) * f * f
            }
        }
    }

    object ExtremeBackEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = ((t * t - sin(t * Math.PI)) * t).toFloat()
    }

    object ExtremeBackEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float {
            val f = 1.0f - t
            return (1.0 - (f * f - sin(f * Math.PI)) * f).toFloat()
        }
    }

    object ExtremeBackEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t < 0.5) {
            val f = 2.0f * t
            (0.5 * (f * f - sin(f * Math.PI)) * f).toFloat()
        } else {
            val f = 2.0f * (1.0f - t)
            (1.0 - 0.5 * (f * f - sin(f * Math.PI)) * f).toFloat()
        }
    }

    object BounceEaseOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = when {
            t < 1.0 / 2.75 -> 7.5625f * t * t
            t < 2.0 / 2.75 -> {
                val f = t - 1.5f / 2.75f
                7.5625f * f * f + 0.75f
            }
            t < 2.5 / 2.75 -> {
                val f = t - 2.25f / 2.75f
                7.5625f * f * f + 0.9375f
            }
            else -> {
                val f = t - 2.625f / 2.75f
                7.5625f * f * f + 0.984375f
            }
        }
    }

    object BounceEaseIn : Interpolations() {
        override fun getInterpolation(t: Float): Float = 1.0f - getInterpolation(1.0f - t)
    }

    object BounceEaseInOut : Interpolations() {
        override fun getInterpolation(t: Float): Float = if (t < 0.5) {
            0.5f * getInterpolation(t * 2.0f)
        } else {
            0.5f * getInterpolation(t * 2.0f - 1.0f) + 0.5f
        }
    }

    object SmoothStep : Interpolations() {
        override fun getInterpolation(t: Float): Float = t * t * (3 - 2 * t)
    }
}