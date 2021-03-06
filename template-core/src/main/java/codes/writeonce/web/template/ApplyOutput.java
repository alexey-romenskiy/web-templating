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

import codes.writeonce.web.template.accessor.ArgumentAccessor;

import java.io.IOException;

public class ApplyOutput implements Output {

    private final Pool<ExecutionScope> scopePool;
    private final ArgumentAccessor[] argumentAccessors;
    private final Output output;

    public ApplyOutput(Pool<ExecutionScope> scopePool, ArgumentAccessor[] argumentAccessors, Output output) {
        this.scopePool = scopePool;
        this.argumentAccessors = argumentAccessors;
        this.output = output;
    }

    @Override
    public void write(ExecutionScope scope, Appendable out) throws IOException {
        final ExecutionScope nextScope = scopePool.claim();
        try {
            for (final ArgumentAccessor argumentAccessor : argumentAccessors) {
                argumentAccessor.init(scope, nextScope);
            }
            output.write(nextScope, out);
        } finally {
            for (final ArgumentAccessor argumentAccessor : argumentAccessors) {
                argumentAccessor.reset(scope, nextScope);
            }
            scopePool.reclaim(nextScope);
        }
    }
}
