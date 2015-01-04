/*
 * template
 * Copyright (c) 2015, Alexey Romenskiy, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package codes.writeonce.web.template;

public class ExecutionScope {

    public final boolean[] booleans;
    public final byte[] bytes;
    public final short[] shorts;
    public final char[] chars;
    public final int[] ints;
    public final long[] longs;
    public final float[] floats;
    public final double[] doubles;
    public final Object[] objects;

    public ExecutionScope(int booleansCount, int bytesCount, int shortsCount, char charsCount, int intsCount,
            int longsCount, int floatsCount, int doublesCount, int objectsCount) {
        this.booleans = booleansCount == 0 ? null : new boolean[booleansCount];
        this.bytes = bytesCount == 0 ? null : new byte[bytesCount];
        this.shorts = shortsCount == 0 ? null : new short[shortsCount];
        this.chars = charsCount == 0 ? null : new char[charsCount];
        this.ints = intsCount == 0 ? null : new int[intsCount];
        this.longs = longsCount == 0 ? null : new long[longsCount];
        this.floats = floatsCount == 0 ? null : new float[floatsCount];
        this.doubles = doublesCount == 0 ? null : new double[doublesCount];
        this.objects = objectsCount == 0 ? null : new Object[objectsCount];
    }
}
