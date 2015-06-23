/*
 * Copyright 2015 Jiesoft Consulting.
 * All Rights Reserved.
 *
 * All information contained herein is, and remains the property
 * of Jiesoft Consulting. The intellectual and technical concepts 
 * contained herein are proprietary to Jiesoft and are protected by 
 * trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless 
 * prior written permission is obtained from Jiesoft Consulting.
 *
 *      http://www.jiesoft.com
 */
package com.jiesoft.mitrac.common;


/**
 * A generic tuple type.
 * @param <L> The left parameter.
 * @param <R> The right parameter.
 * 
 * @author Ray Shi
 */
public class Pair<L, R> {

    /**
     * The left parameter value.
     */
    private L left;

    /**
     * The right parameter value.
     */
    private R right;

    /**
     * Constructor. Accepts two objects and constructs a pair. Neither can be null.
     *
     * @param leftParm
     *            The left object
     * @param rightParm
     *            The right object
     */
    public Pair(final L leftParm, final R rightParm) {
        left = leftParm;
        right = rightParm;
    }

    /**
     * Returns the left item of the pair.
     *
     * @return the left item
     */
    public final L getLeft() {
        return left;
    }

    /**
     * Sets the left item of the pair.
     *
     * @param leftParm The left parameter.
     */
    public final void setLeft(final L leftParm) {
        left = leftParm;
    }

    /**
     * Returns the right item of the pair.
     *
     * @return the left item
     */
    public final R getRight() {
        return right;
    }

    /**
     * Sets the right item of the pair.
     *
     * @param rightParm The right parameter value.
     */
    public final void setRight(final R rightParm) {
        right = rightParm;
    }
    
}
