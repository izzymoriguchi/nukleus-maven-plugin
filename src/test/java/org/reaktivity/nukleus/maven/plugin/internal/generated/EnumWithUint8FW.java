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

import org.agrona.BitUtil;
import org.agrona.DirectBuffer;
import org.agrona.MutableDirectBuffer;
import org.reaktivity.reaktor.internal.test.types.Flyweight;

public final class EnumWithUint8FW extends Flyweight
{
    private static final int FIELD_OFFSET_VALUE = 0;

    private static final int FIELD_SIZE_VALUE = BitUtil.SIZE_OF_BYTE;

    @Override
    public int limit()
    {
        return offset() + FIELD_SIZE_VALUE;
    }

    public EnumWithUint8 get()
    {
        return EnumWithUint8.valueOf(buffer().getByte(offset() + FIELD_OFFSET_VALUE) & 0xFF);
    }

    @Override
    public EnumWithUint8FW tryWrap(
        DirectBuffer buffer,
        int offset,
        int maxLimit)
    {
        if (super.tryWrap(buffer, offset, maxLimit) == null || limit() > maxLimit)
        {
            return null;
        }
        return this;
    }

    @Override
    public EnumWithUint8FW wrap(
        DirectBuffer buffer,
        int offset,
        int maxLimit)
    {
        super.wrap(buffer, offset, maxLimit);
        checkLimit(limit(), maxLimit);
        return this;
    }

    @Override
    public String toString()
    {
        return maxLimit() == offset() ? "null" : get().toString();
    }

    public static final class Builder extends Flyweight.Builder<EnumWithUint8FW>
    {
        private boolean valueSet;

        public Builder()
        {
            super(new EnumWithUint8FW());
        }

        public Builder wrap(
            MutableDirectBuffer buffer,
            int offset,
            int maxLimit)
        {
            super.wrap(buffer, offset, maxLimit);
            return this;
        }

        public Builder set(
            EnumWithUint8FW value)
        {
            int newLimit = offset() + value.sizeof();
            checkLimit(newLimit, maxLimit());
            buffer().putBytes(offset(), value.buffer(), value.offset(), value.sizeof());
            limit(newLimit);
            valueSet = true;
            return this;
        }

        public Builder set(
            EnumWithUint8 value)
        {
            MutableDirectBuffer buffer = buffer();
            int offset = offset();
            int newLimit = offset + FIELD_SIZE_VALUE;
            checkLimit(newLimit, maxLimit());
            buffer.putByte(offset, (byte) (value.value() & 0xFF));
            limit(newLimit);
            valueSet = true;
            return this;
        }

        @Override
        public EnumWithUint8FW build()
        {
            if (!valueSet)
            {
                throw new IllegalStateException("EnumWithUint8 not set");
            }
            return super.build();
        }
    }
}
