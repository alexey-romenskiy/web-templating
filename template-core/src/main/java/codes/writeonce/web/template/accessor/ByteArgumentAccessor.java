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

package codes.writeonce.web.template.accessor;

import codes.writeonce.web.template.ExecutionScope;

public class ByteArgumentAccessor implements ArgumentAccessor {

    private final int fromIndex;
    private final int toIndex;

    public ByteArgumentAccessor(int fromIndex, int toIndex) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    public void init(ExecutionScope fromScope, ExecutionScope toScope) {
        toScope.bytes[toIndex] = fromScope.bytes[fromIndex];
    }

    @Override
    public void reset(ExecutionScope fromScope, ExecutionScope toScope) {
        // empty
    }
}
