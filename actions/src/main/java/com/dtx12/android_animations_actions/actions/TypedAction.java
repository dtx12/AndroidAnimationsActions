/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 dtx12
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.dtx12.android_animations_actions.actions;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import com.dtx12.android_animations_actions.actions.listeners.*;

class TypedAction extends ValueAnimator {
    private ActionType type;
    private float[] floatTargets;
    private int[] intTargets;
    private View view;

    public void setFloatTargets(float... floatTargets) {
        this.floatTargets = floatTargets;
    }

    public void setIntTargets(int... intTargets) {
        this.intTargets = intTargets;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    @Override
    public void setTarget(Object target) {
        if (target instanceof View) {
            this.view = ((View) target);
        }
    }

    @Override
    public void start() {
        updateValues();
        super.start();
    }

    private void updateValues() {
        if (type == null) {
            throw new IllegalStateException("type is not specified!");
        }
        if (view == null) {
            throw new IllegalStateException("target is not specified!");
        }
        switch (type) {

            case ALPHA:
                prepareAlphaAnimation();
                break;
            case SCALE_TO:
                prepareScaleAnimation(false);
                break;
            case SCALE_BY:
                prepareScaleAnimation(true);
                break;
            case SIZE_TO:
                break;
            case SIZE_BY:
                break;
            case ROTATE_TO:
                prepareRotateAnimation(false);
                break;
            case ROTATE_BY:
                prepareRotateAnimation(true);
                break;
            case COLOR:
                prepareColorAnimation();
                break;
            case MOVE_TO:
                prepareMoveAnimation(false);
                break;
            case MOVE_BY:
                prepareMoveAnimation(true);
                break;
        }
    }

    private void prepareColorAnimation() {
        setIntValues(intTargets);
        setEvaluator(ArgbEvaluator.getInstance());
        addUpdateListener(new ColorUpdateListener(view));
    }

    private void prepareRotateAnimation(boolean rotateBy) {
        float rotationOffset = rotateBy ? view.getRotation() : 0;
        setFloatValues(view.getRotation(), rotationOffset + floatTargets[0]);
        addUpdateListener(new RotationUpdateListener(view));
    }

    private void prepareMoveAnimation(boolean moveBy) {
        float moveXOffset = moveBy ? view.getX() : 0;
        float moveYOffset = moveBy ? view.getY() : 0;
        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x", view.getX(), moveXOffset + floatTargets[0]);
        PropertyValuesHolder y = PropertyValuesHolder.ofFloat("y", view.getY(), moveYOffset + floatTargets[1]);
        setValues(x, y);
        addUpdateListener(new MoveUpdateListener(view));
    }

    private void prepareScaleAnimation(boolean scaleBy) {
        float scaleXOffset = scaleBy ? view.getScaleX() : 0;
        float scaleYOffset = scaleBy ? view.getScaleY() : 0;
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", view.getScaleX(), scaleXOffset + floatTargets[0]);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", view.getScaleY(), scaleYOffset + floatTargets[1]);
        setValues(scaleX, scaleY);
        addUpdateListener(new ScaleUpdateListener(view));
    }

    private void prepareAlphaAnimation() {
        setFloatValues(view.getAlpha(), floatTargets[0]);
        addUpdateListener(new AlphaUpdateListener(view));
    }
}
