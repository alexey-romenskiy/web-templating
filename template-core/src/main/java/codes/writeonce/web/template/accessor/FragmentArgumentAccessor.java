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
import codes.writeonce.web.template.FragmentOutput;

public class FragmentArgumentAccessor implements ArgumentAccessor {

    private final FragmentOutput fragmentOutput;
    private final int toIndex;

    public FragmentArgumentAccessor(FragmentOutput fragmentOutput, int toIndex) {
        this.fragmentOutput = fragmentOutput;
        this.toIndex = toIndex;
    }

    @Override
    public void init(ExecutionScope fromScope, ExecutionScope toScope) {
        fragmentOutput.setScope(fromScope);
        toScope.objects[toIndex] = fragmentOutput;
    }

    @Override
    public void reset(ExecutionScope fromScope, ExecutionScope toScope) {
        fragmentOutput.setScope(null);
        toScope.objects[toIndex] = null;
    }
}
