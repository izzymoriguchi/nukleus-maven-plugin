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

import static java.nio.ByteBuffer.allocateDirect;
import static org.agrona.BitUtil.SIZE_OF_SHORT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.junit.Test;
import org.reaktivity.reaktor.internal.test.types.inner.VariantUnsignedIntWithoutExplicitTypeFW;

public class VariantUnsignedIntWithoutExplicitTypeFWTest
{
    private final MutableDirectBuffer buffer = new UnsafeBuffer(allocateDirect(100))
    {
        {
            // Make sure the code is not secretly relying upon memory being initialized to 0
            setMemory(0, capacity(), (byte) 0xab);
        }
    };

    private final MutableDirectBuffer expected = new UnsafeBuffer(allocateDirect(100))
    {
        {
            // Make sure the code is not secretly relying upon memory being initialized to 0
            setMemory(0, capacity(), (byte) 0xab);
        }
    };

    private final VariantUnsignedIntWithoutExplicitTypeFW.Builder flyweightRW =
        new VariantUnsignedIntWithoutExplicitTypeFW.Builder();
    private final VariantUnsignedIntWithoutExplicitTypeFW flyweightRO = new VariantUnsignedIntWithoutExplicitTypeFW();

    static int setAllTestValuesCaseUInt8(
        MutableDirectBuffer buffer,
        final int offset)
    {
        int pos = offset;
        buffer.putByte(pos, (byte) 0x52);
        buffer.putShort(pos += 1, (short) 200);
        return pos - offset + SIZE_OF_SHORT;
    }

    @Test
    public void shouldNotTryWrapWhenIncompleteCaseUInt8()
    {
        int size = setAllTestValuesCaseUInt8(buffer, 10);
        for (int maxLimit = 10; maxLimit < 10 + size; maxLimit++)
        {
            assertNull(flyweightRO.tryWrap(buffer,  10, maxLimit));
        }
    }

    @Test
    public void shouldNotWrapWhenIncompleteCaseUInt8()
    {
        int size = setAllTestValuesCaseUInt8(buffer, 10);
        for (int maxLimit = 10; maxLimit < 10 + size; maxLimit++)
        {
            try
            {
                flyweightRO.wrap(buffer,  10, maxLimit);
                fail("Exception not thrown");
            }
            catch (Exception e)
            {
                if (!(e instanceof IndexOutOfBoundsException))
                {
                    fail("Unexpected exception " + e);
                }
            }
        }
    }

    @Test
    public void shouldTryWrapWhenLengthSufficientCaseUInt8()
    {
        int size = setAllTestValuesCaseUInt8(buffer, 10);
        assertSame(flyweightRO, flyweightRO.tryWrap(buffer, 10, 10 + size));
    }

    @Test
    public void shouldWrapWhenLengthSufficientCaseUInt8()
    {
        int size = setAllTestValuesCaseUInt8(buffer, 10);
        assertSame(flyweightRO, flyweightRO.wrap(buffer, 10, 10 + size));
    }

    @Test
    public void shouldTryWrapAndReadAllValuesCaseUInt8() throws Exception
    {
        final int offset = 1;
        setAllTestValuesCaseUInt8(buffer, offset);
        assertNotNull(flyweightRO.tryWrap(buffer, offset, buffer.capacity()));
        assertEquals(200, flyweightRO.getAsUint8());
        assertEquals(0x52, flyweightRO.kind());
    }

    @Test
    public void shouldWrapAndReadAllValuesCaseUInt8() throws Exception
    {
        final int offset = 1;
        setAllTestValuesCaseUInt8(buffer, offset);
        flyweightRO.wrap(buffer, offset, buffer.capacity());
        assertEquals(200, flyweightRO.getAsUint8());
        assertEquals(0x52, flyweightRO.kind());
    }

    @Test
    public void shouldSetUInt32()
    {
        int limit = flyweightRW.wrap(buffer, 0, buffer.capacity())
            .setAsUint32(12345L)
            .build()
            .limit();
        flyweightRO.wrap(buffer, 0, limit);
        assertEquals(12345L, flyweightRO.getAsUint32());
        assertEquals(0x70, flyweightRO.kind());
    }

    @Test
    public void shouldSetUInt8()
    {
        int limit = flyweightRW.wrap(buffer, 0, buffer.capacity())
            .setAsUint8(200)
            .build()
            .limit();
        flyweightRO.wrap(buffer, 0, limit);
        assertEquals(200, flyweightRO.getAsUint8());
        assertEquals(0x52, flyweightRO.kind());
    }

    @Test
    public void shouldSetZero()
    {
        int limit = flyweightRW.wrap(buffer, 0, buffer.capacity())
            .setAsZero()
            .build()
            .limit();
        flyweightRO.wrap(buffer, 0, limit);
        assertEquals(0, flyweightRO.getAsZero());
        assertEquals(0x43, flyweightRO.kind());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldFailToSetUInt32WithInsufficientSpace()
    {
        flyweightRW.wrap(buffer, 10, 18)
            .setAsUint32(12345);
    }
}
