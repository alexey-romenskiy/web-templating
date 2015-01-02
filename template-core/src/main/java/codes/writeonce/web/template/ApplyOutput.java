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

import codes.writeonce.web.template.accessor.Accessor;

import java.nio.ByteBuffer;

public class ApplyOutput implements Output {

    private final ExecutionContext context;
    private final Pool<ExecutionScope> scopePool;
    private final Accessor[] accessors;
    private final Output output;

    private final InitState initState = new InitState();
    private final StartedState startedState = new StartedState();

    private Output state = initState;

    public ApplyOutput(ExecutionContext context, Pool<ExecutionScope> scopePool, Accessor[] accessors, Output output) {
        this.context = context;
        this.scopePool = scopePool;
        this.accessors = accessors;
        this.output = output;
    }

    @Override
    public int write(ByteBuffer out) {
        return state.write(out);
    }

    @Override
    public boolean hasMore() {
        return state.hasMore();
    }

    @Override
    public void reset() {
        state.reset();
    }

    private class StartedState implements Output {

        @Override
        public int write(ByteBuffer out) {
            return output.write(out);
        }

        @Override
        public boolean hasMore() {
            return output.hasMore();
        }

        @Override
        public void reset() {
            output.reset();
            final ExecutionScope nextScope = context.currentScope;
            context.currentScope = nextScope.previousScope;
            scopePool.reclaim(nextScope);
            state = initState;
        }
    }

    private class InitState implements Output {

        @Override
        public int write(ByteBuffer out) {
            doInit();
            return state.write(out);
        }

        @Override
        public boolean hasMore() {
            doInit();
            return state.hasMore();
        }

        @Override
        public void reset() {
            // empty
        }

        private void doInit() {
            final ExecutionScope nextScope = scopePool.claim();
            final ExecutionScope previousScope = context.currentScope;
            nextScope.previousScope = previousScope;
            for (final Accessor accessor : accessors) {
                accessor.copy(previousScope, nextScope);
            }
            context.currentScope = nextScope;
            state = startedState;
        }
    }
}
