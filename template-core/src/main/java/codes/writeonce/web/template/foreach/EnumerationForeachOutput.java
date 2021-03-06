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
import java.util.Enumeration;

public class EnumerationForeachOutput extends AbstractForeachOutput {

    private final Setter setter;
    private final Output output;

    public EnumerationForeachOutput(int fromIndex, ObjectEvaluator[] evaluators, Setter setter, Output output) {
        super(fromIndex, evaluators);
        this.setter = setter;
        this.output = output;
    }

    @Override
    protected void iterate(ExecutionScope scope, Appendable out, Object value) throws IOException {
        final Enumeration enumeration = (Enumeration) value;
        while (enumeration.hasMoreElements()) {
            final Object element = enumeration.nextElement();
            setter.set(scope, element);
            output.write(scope, out);
        }
    }
}
