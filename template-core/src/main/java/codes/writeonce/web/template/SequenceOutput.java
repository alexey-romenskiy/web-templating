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

public class SequenceOutput implements Output {

    private final Output[] sequence;

    private int position;

    public SequenceOutput(Output[] sequence) {
        this.sequence = sequence;
    }

    @Override
    public int write(ByteBuffer out) {
        int total = 0;
        while (position < sequence.length) {
            while (sequence[position].hasMore()) {
                final int written = sequence[position].write(out);
                if (written < 0) {
                    throw new IllegalArgumentException();
                } else if (written == 0) {
                    // output buffer overflow
                    return total;
                }
                total += written;
            }
            position++;
        }
        return total;
    }

    @Override
    public boolean hasMore() {
        return position < sequence.length;
    }

    @Override
    public void reset() {
        position = 0;
        for (final Output output : sequence) {
            output.reset();
        }
    }
}
