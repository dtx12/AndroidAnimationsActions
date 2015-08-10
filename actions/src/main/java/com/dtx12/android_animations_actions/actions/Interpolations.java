package com.dtx12.android_animations_actions.actions;

import android.view.animation.Interpolator;

import static java.lang.Math.*;

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
public final class Interpolations {

    public static final Interpolator QuadraticEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return t * t;
        }
    };

    public static final Interpolator QuadraticEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return t * (2.0f - t);
        }
    };

    public static final Interpolator QuadraticEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t < 0.5) {
                return 2.0f * t * t;
            } else {
                float f = t - 1.0f;
                return 1.0f - 2.0f * f * f;
            }
        }
    };

    public static final Interpolator CubicEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return t * t * t;
        }
    };

    public static final Interpolator CubicEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            float f = t - 1.0f;
            return 1.0f + f * f * f;
        }
    };

    public static final Interpolator CubicEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t < 0.5) {
                return 4.0f * t * t * t;
            } else {
                float f = t - 1.0f;
                return 1.0f + 4.0f * f * f * f;
            }
        }
    };

    public static final Interpolator QuarticEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return t * t * t * t;
        }
    };

    public static final Interpolator QuarticEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            float f = t - 1.0f;
            return 1.0f - f * f * f * f;
        }
    };

    public static final Interpolator QuarticEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t < 0.5) {
                return 8.0f * t * t * t * t;
            } else {
                float f = t - 1.0f;
                return 1.0f - 8.0f * f * f * f * f;
            }
        }
    };

    public static final Interpolator FunctionQuinticEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return t * t * t * t * t;
        }
    };

    public static final Interpolator QuinticEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            float f = t - 1.0f;
            return 1.0f + f * f * f * f * f;
        }
    };

    public static final Interpolator QuinticEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t < 0.5) {
                return 16.0f * t * t * t * t * t;
            } else {
                float f = t - 1.0f;
                return 1.0f + 16.0f * f * f * f * f * f;
            }
        }
    };

    public static final Interpolator SineEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (float) (sin((t - 1.0f) * PI / 2) + 1.0f);
        }
    };

    public static final Interpolator SineEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (float) sin(t * PI / 2);
        }
    };

    public static final Interpolator SineEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (float) (0.5f * (1.0f - cos(t * PI)));
        }
    };

    public static final Interpolator CircularEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (float) (1.0f - sqrt(1.0 - t * t));
        }
    };

    public static final Interpolator CircularEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (float) sqrt((2.0f - t) * t);
        }
    };

    public static final Interpolator CircularEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t < 0.5) {
                return (float) (0.5 * (1.0 - sqrt(1.0 - 4.0 * t * t)));
            } else {
                return (float) (0.5 * sqrt(-4.0 * t * t + 8.0 * t - 3.0) + 0.5);
            }
        }
    };

    public static final Interpolator ExponentialEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (t == 0.0) ? t : (float) pow(2.0, 10.0 * (t - 1.0));
        }
    };

    public static final Interpolator ExponentialEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (t == 1.0) ? t : (float) (1.0 - pow(2.0, -10.0 * t));
        }
    };

    public static final Interpolator ExponentialEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t == 0.0 || t == 1.0) {
                return t;
            } else if (t < 0.5) {
                return (float) (0.5 * pow(2.0, 20.0 * t - 10.0));
            } else {
                return (float) (1.0 - 0.5 * pow(2.0, -20.0 * t + 10.0));
            }
        }
    };

    public static final Interpolator ElasticEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (float) (sin(13.0 * PI / 2 * t) * pow(2.0, 10.0 * (t - 1.0)));
        }
    };

    public static final Interpolator ElasticEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (float) (sin(-13.0 * PI / 2 * (t + 1.0)) * pow(2.0, -10.0 * t) + 1.0);
        }
    };

    public static final Interpolator ElasticEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t < 0.5) {
                return (float) (0.5 * sin(13.0 * PI * t) * pow(2.0, 20.0 * t - 10.0));
            } else {
                return (float) (0.5 * sin(-13.0 * PI * t) * pow(2.0, -20.0 * t + 10.0) + 1.0);
            }
        }
    };

    public static final Interpolator BackEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            float s = 1.70158f;
            return ((s + 1.0f) * t - s) * t * t;
        }
    };

    public static final Interpolator BackEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            float s = 1.70158f;
            float f = 1.0f - t;
            return 1.0f - ((s + 1.0f) * f - s) * f * f;
        }
    };

    public static final Interpolator BackEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            float s = 1.70158f;
            if (t < 0.5) {
                float f = 2.0f * t;
                return 0.5f * ((s + 1.0f) * f - s) * f * f;
            } else {
                float f = 2.0f * (1.0f - t);
                return 1.0f - 0.5f * ((s + 1.0f) * f - s) * f * f;
            }
        }
    };

    public static final Interpolator ExtremeBackEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return (float) ((t * t - sin(t * PI)) * t);
        }
    };

    public static final Interpolator ExtremeBackEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            float f = 1.0f - t;
            return (float) (1.0 - (f * f - sin(f * PI)) * f);
        }
    };

    public static final Interpolator ExtremeBackEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t < 0.5) {
                float f = 2.0f * t;
                return (float) (0.5 * (f * f - sin(f * PI)) * f);
            } else {
                float f = 2.0f * (1.0f - t);
                return (float) (1.0 - 0.5 * (f * f - sin(f * PI)) * f);
            }
        }
    };
    public static final Interpolator BounceEaseOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t < 1.0 / 2.75) {
                return 7.5625f * t * t;
            } else if (t < 2.0 / 2.75) {
                float f = t - 1.5f / 2.75f;
                return 7.5625f * f * f + 0.75f;
            } else if (t < 2.5 / 2.75) {
                float f = t - 2.25f / 2.75f;
                return 7.5625f * f * f + 0.9375f;
            } else {
                float f = t - 2.625f / 2.75f;
                return 7.5625f * f * f + 0.984375f;
            }
        }
    };
    public static final Interpolator BounceEaseIn = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return 1.0f - BounceEaseOut.getInterpolation(1.0f - t);
        }
    };
    public static final Interpolator BounceEaseInOut = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            if (t < 0.5) {
                return 0.5f * BounceEaseIn.getInterpolation(t * 2.0f);
            } else {
                return 0.5f * BounceEaseOut.getInterpolation(t * 2.0f - 1.0f) + 0.5f;
            }
        }
    };

    public static final Interpolator SmoothStep = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            return t * t * (3 - 2 * t);
        }
    };
}
