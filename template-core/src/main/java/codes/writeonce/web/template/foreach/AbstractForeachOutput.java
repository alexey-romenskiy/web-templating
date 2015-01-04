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

package codes.writeonce.web.template.foreach;

import codes.writeonce.web.template.ExecutionScope;
import codes.writeonce.web.template.Output;
import codes.writeonce.web.template.evaluator.ObjectEvaluator;

import java.io.IOException;

public abstract class AbstractForeachOutput implements Output {

    private final int fromIndex;
    private final ObjectEvaluator[] evaluators;

    public AbstractForeachOutput(int fromIndex, ObjectEvaluator[] evaluators) {
        this.fromIndex = fromIndex;
        this.evaluators = evaluators;
    }

    @Override
    public void write(ExecutionScope scope, Appendable out) throws IOException {
        Object value = scope.objects[fromIndex];
        for (final ObjectEvaluator evaluator : evaluators) {
            value = evaluator.evaluate(value);
        }
        iterate(scope, out, value);
    }

    protected abstract void iterate(ExecutionScope scope, Appendable out, Object value) throws IOException;
}
