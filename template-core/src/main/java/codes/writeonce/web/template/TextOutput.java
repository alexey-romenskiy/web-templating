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

public class TextOutput implements Output {

    private final ByteBuffer buffer;

    public TextOutput(byte[] buffer) {
        this.buffer = ByteBuffer.wrap(buffer);
    }

    public TextOutput(ByteBuffer buffer) {
        this.buffer = buffer.slice();
    }

    @Override
    public int write(ByteBuffer out) {
        final int remaining = buffer.remaining();
        out.put(buffer);
        return remaining - buffer.remaining();
    }

    @Override
    public boolean hasMore() {
        return buffer.hasRemaining();
    }

    @Override
    public void reset() {
        buffer.clear();
    }
}
