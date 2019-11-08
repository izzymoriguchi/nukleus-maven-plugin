/**
 * Copyright 2016-2019 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.nukleus.maven.plugin.internal.generated;

import org.agrona.DirectBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.reaktivity.reaktor.internal.test.types.Flyweight;

public class List0FW extends ListFW
{
    private static final DirectBuffer FIELDS_EMPTY_VALUE = new UnsafeBuffer(0L, 0);

    private static final int PHYSICAL_LENGTH_SIZE = 0;

    private static final int LOGICAL_LENGTH_SIZE = 0;

    private static final int PHYSICAL_LENGTH_OFFSET = 0;

    private static final int LOGICAL_LENGTH_OFFSET = PHYSICAL_LENGTH_OFFSET + PHYSICAL_LENGTH_SIZE;

    @Override
    public int limit()
    {
        return offset() + PHYSICAL_LENGTH_SIZE + length();
    }

    @Override
    public int length()
    {
        return 0;
    }

    @Override
    public int fieldCount()
    {
        return 0;
    }

    @Override
    public DirectBuffer fields()
    {
        return FIELDS_EMPTY_VALUE;
    }

    @Override
    public List0FW tryWrap(
        DirectBuffer buffer,
        int offset,
        int maxLimit)
    {
        if (super.tryWrap(buffer, offset, maxLimit) == null)
        {
            return null;
        }
        if (limit() > maxLimit)
        {
            return null;
        }
        return this;
    }

    @Override
    public List0FW wrap(
        DirectBuffer buffer,
        int offset,
        int maxLimit)
    {
        super.wrap(buffer, offset, maxLimit);
        checkLimit(limit(), maxLimit);
        return this;
    }

    public static final class Builder extends Flyweight.Builder<List0FW>
    {
        public Builder()
        {
            super(new List0FW());
        }

        @Override
        public Builder wrap(
            MutableDirectBuffer buffer,
            int offset,
            int maxLimit)
        {
            super.wrap(buffer, offset, maxLimit);
            checkLimit(limit(), maxLimit);
            return this;
        }

        @Override
        public List0FW build()
        {
            return super.build();
        }
    }
}
