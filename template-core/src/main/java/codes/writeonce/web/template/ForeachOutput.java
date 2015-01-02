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

import java.nio.ByteBuffer;

public class ForeachOutput implements Output {

    private final ExecutionContext context;
    private final Output output;

    public ForeachOutput(ExecutionContext context, Output output) {
        this.context = context;
        this.output = output;
    }

    @Override
    public int write(ByteBuffer out) {
        return 0;
    }

    @Override
    public boolean hasMore() {
        return false;
    }

    @Override
    public void reset() {

    }
}
